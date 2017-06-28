package com.edgon.edgongram.login.presenters;

/**
 * Created by gonza on 6/28/2017.
 */

public interface LoginPresenter {
    void signIn(String username, String Password); //interactor
    void loginSucces();
    void loginError(String error);
}
