package com.edgon.edgongram.login.presenter;

/**
 * Created by gonza on 8/8/2017.
 */

public interface LoginPresenter {
    void signIn(String userName,String password); // se va a comunicar con el interactor
    void loginSucces();
    void loginError(String error);
}
