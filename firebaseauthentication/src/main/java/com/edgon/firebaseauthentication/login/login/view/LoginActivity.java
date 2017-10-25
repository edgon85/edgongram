package com.edgon.firebaseauthentication.login.login.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.edgon.firebaseauthentication.R;
import com.edgon.firebaseauthentication.activitys.HomeActivity;
import com.edgon.firebaseauthentication.login.create_account.view.CreateAccountActivity;
import com.edgon.firebaseauthentication.login.login.presenter.LoginPresenter;
import com.edgon.firebaseauthentication.login.login.presenter.LoginPresenterImpl;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private static final String TAG = "LoginActivity";
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    private TextInputEditText edtUser;
    private TextInputEditText edtPassword;
    private Button btnLogin;
    private TextView btnCreateAcount;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = (TextInputEditText) findViewById(R.id.username_login);
        edtPassword = (TextInputEditText) findViewById(R.id.password_login);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnCreateAcount = (TextView) findViewById(R.id.txt_go_create_account);

        inicialize();
        presenter = new LoginPresenterImpl(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edtUser.getText().toString();
                String password = edtPassword.getText().toString();

                Log.w(TAG, email + " " + password);
                login(email,password);
            }
        });

        btnCreateAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goCreateAcount();
            }
        });

    }

    private void inicialize() {
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
    }


    private void login(String email, String password){
        presenter.signIn(email, password, this);
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
}
