package com.edgon.edgongram.login.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.edgon.edgongram.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccountActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private Button btnJoinUs;
    private TextInputEditText edtEmail;
    private TextInputEditText edtPassword;
    private TextInputEditText edtConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        showToolBar(getResources().getString(R.string.toolbar_title_createaccount),true);

        btnJoinUs = (Button) findViewById(R.id.btn_join_us_crate_acount);
        edtEmail = (TextInputEditText) findViewById(R.id.edt_email_create_acount);
        edtPassword = (TextInputEditText) findViewById(R.id.edt_password_create_acount);
        edtConfirmPassword = (TextInputEditText) findViewById(R.id.edt_confirm_password_create_acount);


        firebaseAuth = FirebaseAuth.getInstance();  //obtiene la informacion de firebase

        authStateListener = new FirebaseAuth.AuthStateListener() {  //listener pendiente en los cambios de sesion
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //esta pendiente si el usuario cambio
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseAuth != null){
                    Log.e("MyLog","Usuario logeado " + firebaseUser.getEmail());
                }else{
                    Log.e("MyLog","Usuario no logeado");
                }
            }
        };

        btnJoinUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });

    }

    private void createAccount() {
        String user = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        firebaseAuth.createUserWithEmailAndPassword(user,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(CreateAccountActivity.this, "Cuenta Creada Exitosamente", Toast.LENGTH_SHORT).show();
                            Log.e("MyLog","Cuenta Creada Exitosamente");
                        }else{
                            Toast.makeText(CreateAccountActivity.this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
                            Log.e("MyLog","Ocurrió un error");
                        }
                    }
                });
    }

    public void showToolBar(String title, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
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
