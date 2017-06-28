package com.edgon.edgongram.login.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.edgon.edgongram.R;
import com.edgon.edgongram.login.presenters.LoginPresenter;
import com.edgon.edgongram.login.presenters.LoginPresnterImpl;
import com.edgon.edgongram.view.ContainerActivity;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private Button btnLogin;
    private ImageView imgLogo;
    private Uri url;

    private TextInputEditText username, password;
    private ProgressBar progressBar;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.btn_login);
        imgLogo = (ImageView) findViewById(R.id.logo);

        username = (TextInputEditText) findViewById(R.id.username);
        password = (TextInputEditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_login);
        hideProgressBar();
        presenter = new LoginPresnterImpl(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Intent intent = new Intent(LoginActivity.this, ContainerActivity.class);
             //   startActivity(intent);
                if (username.equals("")){

                }
                presenter.signIn(username.getText().toString(),password.getText().toString());
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

    }


    @Override
    public void enableInputs() {
        username.setEnabled(true);
        password.setEnabled(true);
        btnLogin.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        username.setEnabled(false);
        password.setEnabled(false);
        btnLogin.setEnabled(false);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void loginError(String error) {
        Toast.makeText(this,getString(R.string.login_error) + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goCreateAcount() {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }

    @Override
    public void goHome() {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }
}
