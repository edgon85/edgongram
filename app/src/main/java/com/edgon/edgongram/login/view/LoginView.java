package com.edgon.edgongram.login.view;

/**
 * Created by gonza on 8/8/2017.
 */

public interface LoginView {
    void goCreateAcount();
    void goHome();
    void goFacebook();

    void enableInputs();
    void disableInputs();

    void showProgressBar();
    void hideProgressBar();

    void loginError(String error);
}
