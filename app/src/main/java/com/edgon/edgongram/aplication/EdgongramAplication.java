package com.edgon.edgongram.aplication;

import android.app.Application;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class EdgongramAplication extends Application{

    FirebaseStorage firebaseStorage;

    @Override
    public void onCreate() {
        super.onCreate();

        firebaseStorage = FirebaseStorage.getInstance();
    }


    public StorageReference storageReference(){
        return firebaseStorage.getReference();
    }
}
