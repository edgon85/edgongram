package com.edgon.edgongram.login.presenter;

import com.edgon.edgongram.login.interactor.LoginInteractor;
import com.edgon.edgongram.login.interactor.LoginInteractorImpl;
import com.edgon.edgongram.login.view.LoginView;

/**
 * Created by gonza on 8/8/2017.
 */

public class LoginPresenterImpl implements LoginPresenter{

    LoginView loginView;
    LoginInteractor interactor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        interactor = new LoginInteractorImpl(this);
    }

    @Override
    public void signIn(String userName, String password) {
        loginView.disableInputs();
        loginView.showProgressBar();

        interactor.sigIn(userName,password);
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
