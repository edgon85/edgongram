package com.edgon.firebaseauthentication.login.login.presenter;


import android.app.Activity;

import com.edgon.firebaseauthentication.login.login.interactor.LoginInteractor;
import com.edgon.firebaseauthentication.login.login.interactor.LoginInteractorImpl;
import com.edgon.firebaseauthentication.login.login.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter{


    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        interactor = new LoginInteractorImpl(this);
    }

    @Override
    public void signIn(String email, String password, Activity activity) {
        interactor.signin(email, password, activity);
    }

    @Override
    public void loginSuccess() {
        view.goHome();
    }

    @Override
    public void loginError(String error) {
        view.loginError(error);
    }
}
