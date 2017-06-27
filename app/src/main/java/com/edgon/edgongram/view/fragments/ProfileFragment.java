package com.edgon.edgongram.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edgon.edgongram.R;
import com.edgon.edgongram.adapter.PictureAdapterRecyclerView;
import com.edgon.edgongram.model.Pictures;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        showToolBar("",false,view);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.pictureprofileRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView =
                new PictureAdapterRecyclerView(buildPictures(),R.layout.cardview_picture,getActivity());

        recyclerView.setAdapter(pictureAdapterRecyclerView);

        return view;
    }

    public void showToolBar(String title, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toobarProfile);
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
