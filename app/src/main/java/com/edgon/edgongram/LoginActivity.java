package com.edgon.edgongram;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.edgon.edgongram.view.ContainerActivity;
import com.edgon.edgongram.view.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private ImageView imgLogo;
    private Uri url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.btn_login);
        imgLogo = (ImageView) findViewById(R.id.logo);

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


    }

    public void goCreateAcoount(View view){
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }


}
