package com.edgon.edgongram.login.view;

/**
 * Created by gonza on 6/27/2017.
 */

public interface LoginView {

    void enableInputs();
    void disableInputs();

    void showProgressBar();
    void hideProgressBar();

    void loginError(String error);

    void goCreateAcount();
    void goHome();
}
