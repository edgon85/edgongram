package com.edgon.firebaseauthentication.login.login.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.edgon.firebaseauthentication.R;
import com.edgon.firebaseauthentication.activitys.HomeActivity;
import com.edgon.firebaseauthentication.login.create_account.view.CreateAccountActivity;
import com.edgon.firebaseauthentication.login.login.presenter.LoginPresenter;
import com.edgon.firebaseauthentication.login.login.presenter.LoginPresenterImpl;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity
        implements LoginView, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

    private static final String TAG = "LoginActivity";
    private static final int SIGN_IN_GOOGLE_CODE = 1;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    GoogleApiClient googleApiClient;

    private TextInputEditText edtUser;
    private TextInputEditText edtPassword;
    private TextView btnCreateAcount;
    private Button btnLogin;
    private SignInButton btnLoginGoogle;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = (TextInputEditText) findViewById(R.id.username_login);
        edtPassword = (TextInputEditText) findViewById(R.id.password_login);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnCreateAcount = (TextView) findViewById(R.id.txt_go_create_account);
        btnLoginGoogle = (SignInButton) findViewById(R.id.btn_login_google);

        initialize();
        presenter = new LoginPresenterImpl(this);

        btnLogin.setOnClickListener(this);
        btnCreateAcount.setOnClickListener(this);
        btnLoginGoogle.setOnClickListener(this);

    }

    private void initialize() {
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null){
                    Log.w(TAG,"onAuthStateChanged - signed_in " + firebaseUser.getUid());
                    Log.w(TAG,"onAuthStateChanged - signed_in " + firebaseUser.getEmail());
                    goHome();

                }else {
                    Log.w(TAG,"onAuthStateChanged - signed_out");
                }
            }
        };

        //Initialization google account
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }


    private void login(String email, String password){
        presenter.signIn(email, password, this);
    }

    private void loginGoogle(GoogleSignInResult googleSignInResult){

         if (googleSignInResult.isSuccess()){
             AuthCredential authCredential = GoogleAuthProvider
                     .getCredential(googleSignInResult.getSignInAccount().getIdToken(),null);

             firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Google Authentication success", Toast.LENGTH_SHORT).show();
                        goHome();
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Google Authentication unsuccess", Toast.LENGTH_SHORT).show();
                    }
                 }
             });
        }else{
             Toast.makeText(this, "Google Sign In Unsuccess", Toast.LENGTH_SHORT).show();
         }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_create_account:
                goCreateAcount();
                break;

            case R.id.btn_login:
                String email = edtUser.getText().toString();
                String password = edtPassword.getText().toString();

                Log.w(TAG, email + " " + password);
                login(email,password);
                break;

            case R.id.btn_login_google:
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN_GOOGLE_CODE);
                break;

        }
    }

    @Override
    public void goHome() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    public void goCreateAcount() {
        Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginError(String error) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_GOOGLE_CODE){
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi
                    .getSignInResultFromIntent(data);

            loginGoogle(googleSignInResult);
        }

    }
}
