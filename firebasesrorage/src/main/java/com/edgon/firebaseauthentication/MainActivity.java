package com.edgon.firebaseauthentication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int CHOOSER_IMAGES = 1;
    private Button btnUpload;
    private Button btnDownload;
    private ImageView imgImage;

    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUpload = (Button) findViewById(R.id.btn_upload);
        btnDownload = (Button) findViewById(R.id.btn_download);
        imgImage = (ImageView) findViewById(R.id.img_image);

        btnUpload.setOnClickListener(this);
        btnDownload.setOnClickListener(this);
        imgImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_upload:
                Toast.makeText(this, "Upload", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_download:
                Toast.makeText(this, "Download", Toast.LENGTH_SHORT).show();
                break;

            case R.id.img_image:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select image"),CHOOSER_IMAGES);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSER_IMAGES){
            Uri uriImage = data.getData();
            if (uriImage != null){
                imgImage.setImageURI(uriImage);
            }
        }
    }

    void updateImage(){
        StorageReference imageRef = storageReference.child("image.png");
         imgImage.setDrawingCacheEnabled(true);
        imgImage.buildDrawingCache();

        Bitmap bitmap = imgImage.getDrawingCache();
        //ByteArrayOutputStream baos =
    }
}
