package com.edgon.edgongram.post.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.edgon.edgongram.R;

public class NewPostActivity extends AppCompatActivity {

    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        imgPhoto = (ImageView) findViewById(R.id.img_photo);

        if (getIntent().getExtras() != null){
            String photoPath = getIntent().getExtras().getString("PHOTO_PATH_TEMP");
            Glide.with(this)
                    .load(photoPath)
                    .into(imgPhoto);
        }

    }
}
