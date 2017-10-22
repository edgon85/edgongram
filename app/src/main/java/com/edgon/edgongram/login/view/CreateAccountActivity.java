package com.edgon.edgongram.login.view;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.crash.FirebaseCrash;

import org.w3c.dom.Text;

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
        showToolBar(getResources().getString(R.string.toolbar_title_createaccount), true);

        btnJoinUs = (Button) findViewById(R.id.joinUs);
        edtEmail = (TextInputEditText) findViewById(R.id.email);
        edtPassword = (TextInputEditText) findViewById(R.id.password);
        edtConfirmPassword = (TextInputEditText) findViewById(R.id.confirmPassword);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseListener();

        btnJoinUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAcount();
            }
        });
    }

    private void createAcount() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String confirmPass = edtConfirmPassword.getText().toString();

        if (password.equals(confirmPass)) {

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(CreateAccountActivity.this, "Cuenta Creada",
                                        Toast.LENGTH_SHORT).show();
                                Log.e("MyLog","Cuenta creada ");
                            } else {
                                Toast.makeText(CreateAccountActivity.this, "Ocurrio un error al crear la cuenta",
                                        Toast.LENGTH_SHORT).show();
                                Log.e("MyLog","Ocurrio un error al creat la cuenta --> " + task.getException().getMessage());
                            }
                        }
                    });
        } else {
            Toast.makeText(CreateAccountActivity.this, "Contraseñas no conciden", Toast.LENGTH_SHORT).show();
        }

    }

    private void firebaseListener() {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    //Log.e("MyLog", "Usuario logeado" + firebaseUser.getEmail());
                    FirebaseCrash.logcat(Log.WARN,"CreatrAcountActivity", "Usuario logeado" + firebaseUser.getEmail());
                } else {
                    //Log.e("MyLog", "Usuario no logeado");
                    FirebaseCrash.logcat(Log.WARN,"CreatrAcountActivity", "Usuario no logeado");
                }
            }
        };
    }

    public void showToolBar(String title, boolean upButton) {
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
