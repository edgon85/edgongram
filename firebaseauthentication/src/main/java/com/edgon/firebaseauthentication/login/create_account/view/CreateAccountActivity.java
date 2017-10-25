package com.edgon.firebaseauthentication.login.create_account.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edgon.firebaseauthentication.R;
import com.edgon.firebaseauthentication.activitys.HomeActivity;
import com.edgon.firebaseauthentication.login.create_account.presenter.CreateAccountPresenter;
import com.edgon.firebaseauthentication.login.create_account.presenter.CreateAccountPresenterImpl;

public class CreateAccountActivity extends AppCompatActivity implements CreateAccountView {

    private static final String TAG = "CreateAccountActivity";
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnCreateAcount;

    private CreateAccountPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creatr_account);
        showToolbar("Crear cuenta", true);

        edtEmail = (EditText) findViewById(R.id.edt_email_create_account);
        edtPassword = (EditText) findViewById(R.id.edt_password_create_account);
        btnCreateAcount = (Button) findViewById(R.id.btn_create_account);

        presenter = new CreateAccountPresenterImpl(this);

        btnCreateAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                Log.e(TAG, email + " " + password);

                createAccount(email, password);
            }
        });
    }

    private void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }


    @Override
    public void createAccount(String email, String password) {
        presenter.createAcount(email, password, this);
    }

    @Override
    public void goHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void createAccountError(String error) {
        Toast.makeText(this, " " + error, Toast.LENGTH_SHORT).show();
    }

}
