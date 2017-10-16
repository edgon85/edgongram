package com.edgon.edgongram.login.interactor;

import android.app.Activity;

import com.edgon.edgongram.login.presenter.LoginPresenter;
import com.edgon.edgongram.login.repositori.LoginRepository;
import com.edgon.edgongram.login.repositori.LoginRepositoryImpl;
import com.google.firebase.auth.FirebaseAuth;


public class LoginInteractorImpl implements LoginInteractor{


    private LoginPresenter presenter;
    private LoginRepository repository;

    public LoginInteractorImpl(LoginPresenter presenter) {
        this.presenter = presenter;
        repository = new LoginRepositoryImpl(presenter);
    }

    @Override
    public void sigIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth) {
        repository.signIn(username,password, activity, firebaseAuth);
    }
}
