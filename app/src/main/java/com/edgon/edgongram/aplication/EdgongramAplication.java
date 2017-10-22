package com.edgon.edgongram.aplication;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class EdgongramAplication extends Application{

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseStorage firebaseStorage;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseCrash.log("Inicializando variables EdgongramAplication");
        firebaseStorage = FirebaseStorage.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                if (firebaseUser != null) {
                    //Log.e("MyLog", "Usuario logeado " + firebaseUser.getEmail());
                    FirebaseCrash.logcat(Log.WARN,"EdgongramAplication","Usuario logeado " + firebaseUser.getEmail());
                } else {
                    //Log.e("MyLog", "Usuario no logeado");
                    FirebaseCrash.logcat(Log.WARN,"EdgongramAplication","Usuario no logeado");
                }
            }
        };


    }


    public StorageReference storageReference(){
        return firebaseStorage.getReference();
    }
}
