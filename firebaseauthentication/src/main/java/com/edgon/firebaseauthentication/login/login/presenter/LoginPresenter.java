package com.edgon.firebaseauthentication.login.login.presenter;


import android.app.Activity;

public interface LoginPresenter {

    void signIn(String email, String password, Activity activity);
    void loginSuccess();
    void loginError(String error);
}
