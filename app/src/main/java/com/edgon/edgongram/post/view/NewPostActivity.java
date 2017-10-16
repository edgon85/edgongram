package com.edgon.edgongram.post.view;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.edgon.edgongram.R;
import com.edgon.edgongram.aplication.EdgongramAplication;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class NewPostActivity extends AppCompatActivity {

    private ImageView imgPhoto;
    private Button btnCreatePost;

    private String photoPath;
    private EdgongramAplication app;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        app = (EdgongramAplication) getApplicationContext();
        storageReference = app.storageReference();

        imgPhoto = (ImageView) findViewById(R.id.img_photo);
        btnCreatePost  = (Button) findViewById(R.id.btn_create_post);

        if (getIntent().getExtras() != null){
            photoPath = getIntent().getExtras().getString("PHOTO_PATH_TEMP");
            showPhoto();
        }

        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPhoto();
            }
        });
    }

    private void uploadPhoto() {
        imgPhoto.setDrawingCacheEnabled(true);
        imgPhoto.buildDrawingCache();

        Bitmap bitmap = imgPhoto.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] photoByte = baos.toByteArray();
        String photoName = photoPath.substring(photoPath.lastIndexOf("/")+1, photoPath.length());

        StorageReference photoReference = storageReference.child("postImages/"+photoName);
        UploadTask uploadTask = photoReference.putBytes(photoByte);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("MyLog","Error al subir foto " + e.toString());
                e.printStackTrace();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri uriPhoto = taskSnapshot.getDownloadUrl();
                String photoUrl = uriPhoto.toString();
                Log.w("MyLog","URL Photo --> "+photoUrl );
                finish();
            }
        });
    }


    private void showPhoto(){
        Glide.with(this)
                .load(photoPath)
                .into(imgPhoto);
    }
}
