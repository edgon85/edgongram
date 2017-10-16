package com.edgon.edgongram.login.interactor;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by gonza on 8/8/2017.
 */

public interface LoginInteractor {
    void sigIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth);
}
