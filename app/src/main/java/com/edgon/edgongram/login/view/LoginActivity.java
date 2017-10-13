package com.edgon.edgongram.login.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.edgon.edgongram.R;
import com.edgon.edgongram.login.presenter.LoginPresenter;
import com.edgon.edgongram.login.presenter.LoginPresenterImpl;
import com.edgon.edgongram.view.ContainerActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText userNameLogin;
    private EditText passwordLogin;
    private Button btnLogin;
    private ProgressBar progressBarLogin;
    private Uri url;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       // firebaseAuthActivity(); // para verificar si el usuario esta logeado o no


        userNameLogin = (EditText) findViewById(R.id.username);
        passwordLogin = (EditText) findViewById(R.id.edt_password_create_acount);
        btnLogin = (Button) findViewById(R.id.btn_login);
        progressBarLogin = (ProgressBar) findViewById(R.id.progeressbar_login);
        hideProgressBar();

        presenter = new LoginPresenterImpl(this);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = userNameLogin.getText().toString();
                String pass = passwordLogin.getText().toString();
                signIn(user, pass);

            }
        });

    }

    private void firebaseAuthActivity() {
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                if (firebaseAuth != null){
                    Log.e("MyLog","Usuario logeado " + firebaseUser.getEmail());
                }else{
                    Log.e("MyLog","Usuario no logeado");
                }
            }
        };
    }

    private void signIn(String user, String pass) {
        presenter.signIn(user,pass,this,firebaseAuth);
    }

    @Override
    public void goCreateAcount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void goHome() {
        Intent intent = new Intent(LoginActivity.this, ContainerActivity.class);
        startActivity(intent);
    }

    @Override
    public void goFacebook() {
        url.parse("https://www.facebook.com/?sk=h_chr");
        Intent intent = new Intent(Intent.ACTION_VIEW,url);
        startActivity(intent);
    }

    @Override
    public void enableInputs() {
        userNameLogin.setEnabled(true);
        passwordLogin.setEnabled(true);
        btnLogin.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        userNameLogin.setEnabled(false);
        passwordLogin.setEnabled(false);
        btnLogin.setEnabled(false);
    }

    @Override
    public void showProgressBar() {
        progressBarLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBarLogin.setVisibility(View.GONE);
    }

    @Override
    public void loginError(String error) {
        Toast.makeText(this, getString(R.string.login_error) + error, Toast.LENGTH_SHORT).show();
    }

    public void goCreateAcoount(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
       // firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}

/*

btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ContainerActivity.class);
                startActivity(intent);
            }
        });

        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url.parse("https://www.facebook.com/?sk=h_chr");
                Intent intent = new Intent(Intent.ACTION_VIEW,url);
                startActivity(intent);

            }
        });

 public void goCreateAcoount(View view){
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }


     */