package com.edgon.edgongram.login.interator;

import com.edgon.edgongram.login.presenters.LoginPresenter;
import com.edgon.edgongram.login.repository.LoginRepositoryImpl;
import com.edgon.edgongram.login.repository.LoginRespository;

/**
 * Created by gonza on 6/28/2017.
 */

public class LoginInteractorImpl implements LoginInteractor{

    private LoginPresenter loginPresenter;
    private LoginRespository respository;

    public LoginInteractorImpl(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
        respository = new LoginRepositoryImpl(loginPresenter);
    }

    @Override
    public void signIn(String username, String password) {
        respository.signIn(username,password);
    }
}
