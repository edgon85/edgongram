package com.edgon.edgongram.adapter;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edgon.edgongram.R;
import com.edgon.edgongram.model.Pictures;
import com.edgon.edgongram.view.PictureDetailActivity;

import java.util.ArrayList;

public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder>{

    private ArrayList<Pictures> picture;
    private int resouce;
    private Activity activity;

    public PictureAdapterRecyclerView(ArrayList<Pictures> picture, int resouce, Activity activity) {
        this.picture = picture;
        this.resouce = resouce;
        this.activity = activity;
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resouce,parent,false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {

        Pictures pictures = picture.get(position);

        holder.usernameCard.setText(pictures.getUserName());
        holder.timeCard.setText(pictures.getTime());
        holder.likeNumberCard.setText(pictures.getLike_number());

        Glide.with(activity)
                .load(pictures.getPicture())
                .centerCrop()
                .into(holder.pictureCard);

        holder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PictureDetailActivity.class);

                //Crear una transicion al mimendo de cambiar actividad
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Explode explode = new Explode();
                    explode.setDuration(1000);
                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, v, activity.getString(R.string.transitionname_picture)).toBundle());
                }else {
                    activity.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return picture.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder{
        private ImageView pictureCard;
        private TextView usernameCard;
        private TextView timeCard;
        private TextView likeNumberCard;


        public PictureViewHolder(View itemView) {
            super(itemView);

            pictureCard    = (ImageView) itemView.findViewById(R.id.image_card);
            usernameCard = (TextView) itemView.findViewById(R.id.usernameCard);
            timeCard       = (TextView) itemView.findViewById(R.id.timeCard);
            likeNumberCard = (TextView) itemView.findViewById(R.id.likeNumberCard);

        }
    }
}
