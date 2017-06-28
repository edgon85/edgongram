package com.edgon.edgongram.login.presenters;

import com.edgon.edgongram.login.interator.LoginInteractor;
import com.edgon.edgongram.login.interator.LoginInteractorImpl;
import com.edgon.edgongram.login.view.LoginView;

/**
 * Created by gonza on 6/28/2017.
 */

public class LoginPresnterImpl implements LoginPresenter {

    private LoginView loginView;
    private LoginInteractor interactor;

    public LoginPresnterImpl(LoginView loginView) {
        this.loginView = loginView;
        interactor = new LoginInteractorImpl(this);
    }

    @Override
    public void signIn(String username, String password) {
        loginView.disableInputs();
        loginView.showProgressBar();
        interactor.signIn(username,password);
    }

    @Override
    public void loginSucces() {
        loginView.goHome();
        loginView.hideProgressBar();
    }

    @Override
    public void loginError(String error) {
        loginView.enableInputs();
        loginView.hideProgressBar();
        loginView.loginError(error);
    }
}
