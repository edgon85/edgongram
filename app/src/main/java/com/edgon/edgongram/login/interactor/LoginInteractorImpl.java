package com.edgon.edgongram.login.interactor;

import com.edgon.edgongram.login.presenter.LoginPresenter;
import com.edgon.edgongram.login.repositori.LoginRepository;
import com.edgon.edgongram.login.repositori.LoginRepositoryImpl;


public class LoginInteractorImpl implements LoginInteractor{


    private LoginPresenter presenter;
    private LoginRepository repository;

    public LoginInteractorImpl(LoginPresenter presenter) {
        this.presenter = presenter;
        repository = new LoginRepositoryImpl(presenter);
    }

    @Override
    public void sigIn(String username, String password) {
        repository.signIn(username,password);
    }
}
