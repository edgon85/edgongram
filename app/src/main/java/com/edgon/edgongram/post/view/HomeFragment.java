package com.edgon.edgongram.post.view;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edgon.edgongram.R;
import com.edgon.edgongram.adapter.PictureAdapterRecyclerView;
import com.edgon.edgongram.model.Pictures;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HomeFragment extends Fragment {

    private static final int REQUEST_CAMERA = 1;
    private FloatingActionButton fabCamera;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout   for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        showToolBar(getResources().getString(R.string.tab_home),false,view);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.pictureRecycler);
        fabCamera = (FloatingActionButton) view.findViewById(R.id.fab_camera);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView =
                new PictureAdapterRecyclerView(buildPictures(),R.layout.cardview_picture,getActivity());

        recyclerView.setAdapter(pictureAdapterRecyclerView);

        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });


        return view;
    }

    private void takePicture() {
        Intent intentTakePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intentTakePicture.resolveActivity(getActivity().getPackageManager()) != null){

            File photoFile = null;
            try {
                photoFile = createImageFile();

            }catch (Exception e){
                e.printStackTrace();
            }

            startActivityForResult(intentTakePicture,REQUEST_CAMERA);
        }
    }

    private File createImageFile() {
        String timeStamp = new  SimpleDateFormat("yyyyMMdd_HH-mm-ss").format(new Date());
        return null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA && requestCode == getActivity().RESULT_OK){
            Log.d("HomeFragment","CAMERA OK!!");
        }
    }

    public void showToolBar(String title, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toobar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public ArrayList<Pictures> buildPictures(){
        ArrayList<Pictures> pictures = new ArrayList<>();
        pictures.add(new Pictures("http://2.bp.blogspot.com/-ifUY_PGK_LM/VX-RH_1eeDI/AAAAAAAABaE/5XI2uKnSK1Q/s300-c/DragonBallSuper.png",
                "Marco Papa",
                "10 dias",
                "10 Me gusta"));

        pictures.add(new Pictures("http://imagenes.gratis/wp-content/uploads/2016/06/imagenes-de-dragon-ball-z-super-gt-k-3.jpg",
                "Goku son",
                "2 dias",
                "95 Me gusta"));

        pictures.add(new Pictures("http://www.espagnegestion.fr/vegeta/wp-content/uploads/2015/09/Transcendence-300x300.jpg",
                "Vegeta San",
                "1 dia",
                "45 Me gusta"));
        return pictures;
    }

}
