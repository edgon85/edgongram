package com.edgon.firebaseauthentication.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edgon.firebaseauthentication.R;
import com.edgon.firebaseauthentication.login.login.view.LoginActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG = HomeActivity.class.getSimpleName();
    FloatingActionButton fab;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    private TextView txtUserId;
    private TextView txtUserEmail;
    private ImageView imgPhoto;

    GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referecia);
        showToolbar("Home", false);
        inicialize();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        txtUserId = (TextView) findViewById(R.id.txt_usuario_id);
        txtUserEmail = (TextView) findViewById(R.id.txt_usuario_email);
        imgPhoto = (ImageView) findViewById(R.id.img_photo);

        buttonFloating();
    }


    private void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    private void logOunt() {
        firebaseAuth.signOut();

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()){
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    finish();
                }else{
                    Toast.makeText(HomeActivity.this, "Error in Goole sign out", Toast.LENGTH_SHORT).show();
                }
            }
        });


        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }


    private void buttonFloating() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logOunt();

                Snackbar.make(view, "Cerrar Sesión", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void inicialize() {
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    txtUserId.setText(firebaseUser.getUid());
                    txtUserEmail.setText(firebaseUser.getEmail());

                    Picasso.with(HomeActivity.this)
                            .load(firebaseUser.getPhotoUrl())
                            .into(imgPhoto);

                } else {
                    Log.w(TAG, "onAuthStateChanged - signed_out");
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
}
