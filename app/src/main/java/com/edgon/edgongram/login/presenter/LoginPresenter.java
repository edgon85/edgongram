package com.edgon.edgongram.login.presenter;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by gonza on 8/8/2017.
 */

public interface LoginPresenter {
    void signIn(String userName, String password, Activity activity, FirebaseAuth firebaseAuth); // se va a comunicar con el interactor
    void loginSucces();
    void loginError(String error);
}
