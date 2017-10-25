package com.edgon.firebaseauthentication.aplication;


import android.app.Application;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAplication extends Application{

    private static final String TAG = FirebaseAplication.class.getSimpleName();
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;

    @Override
    public void onCreate() {
        super.onCreate();
//        currentUser = firebaseAuth.getCurrentUser();
//        Log.w(TAG, currentUser.getEmail());
    }
}
