package com.edgon.firebaseauthentication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CHOOSER_IMAGES = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button btnUpload;
    private Button btnDownload;
    private ImageView imgImage;

    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storageReference = FirebaseStorage.getInstance().getReference();

        btnUpload = (Button) findViewById(R.id.btn_upload);
        btnDownload = (Button) findViewById(R.id.btn_download);
        imgImage = (ImageView) findViewById(R.id.img_image);

        btnUpload.setOnClickListener(this);
        btnDownload.setOnClickListener(this);
        imgImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_upload:
                //Toast.makeText(this, "Upload", Toast.LENGTH_SHORT).show();
                upLoadImage();
                break;
            case R.id.btn_download:
                //Toast.makeText(this, "Download", Toast.LENGTH_SHORT).show();
                downLoadImage();
                break;

            case R.id.img_image:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select image"), CHOOSER_IMAGES);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSER_IMAGES) {
            Uri uriImage = data.getData();
            if (uriImage != null) {
                imgImage.setImageURI(uriImage);
            }
        }
    }

    void upLoadImage() {
        StorageReference imageRef = storageReference.child("image.jpg");
        imgImage.setDrawingCacheEnabled(true);
        imgImage.buildDrawingCache();

        Bitmap bitmap = imgImage.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

        byte[] imageByte = baos.toByteArray();

        UploadTask uploadTask = imageRef.putBytes(imageByte);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG,"Ocurrió un error en la subida");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(MainActivity.this, "Subida con Éxito", Toast.LENGTH_SHORT).show();
                String downloadUri = taskSnapshot.getDownloadUrl().getPath();
                Log.w(TAG,"Image URL: "+downloadUri);
            }
        });
    }

    void downLoadImage(){
        final File file;

        try {
            file = File.createTempFile("JPEG_20171016_12-22-31_302222952","jpg");
            storageReference.child("postImages/JPEG_20171016_12-22-31_302222952.jpg").getFile(file)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            imgImage.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w(TAG,"Ocurrio un error al mostrar la imagen");
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            Log.e(TAG,"Ocurrio un error en la descarga de imagenes");
            e.printStackTrace();
        }
    }
}
