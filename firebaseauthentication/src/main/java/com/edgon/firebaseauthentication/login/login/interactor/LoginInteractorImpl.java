package com.edgon.firebaseauthentication.login.login.interactor;


import android.app.Activity;

import com.edgon.firebaseauthentication.login.login.presenter.LoginPresenter;
import com.edgon.firebaseauthentication.login.login.repository.LoginRepository;
import com.edgon.firebaseauthentication.login.login.repository.LoginRepositoryImpl;

public class LoginInteractorImpl implements LoginInteractor{

    private LoginPresenter presenter;
    private LoginRepository repository;

    public LoginInteractorImpl(LoginPresenter presenter) {
        this.presenter = presenter;
        repository = new LoginRepositoryImpl(presenter);
    }


    @Override
    public void signin(String email, String password, Activity activity) {
        repository.signIn(email, password, activity);
    }
}
