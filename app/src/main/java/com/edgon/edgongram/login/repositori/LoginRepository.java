package com.edgon.edgongram.login.repositori;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by gonza on 8/8/2017.
 */

public interface LoginRepository {
    void signIn(String userName, String password, Activity activity, FirebaseAuth firebaseAuth);
}
