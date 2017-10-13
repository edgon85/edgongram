package com.edgon.edgongram.login.repository;


import com.edgon.edgongram.login.presenter.LoginPresenter;
import com.edgon.edgongram.login.repositori.LoginRepository;

/**
 * Created by gonza on 6/28/2017.
 */

public class LoginRepositoryImpl implements LoginRepository{
    LoginPresenter presenter;

    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void signIn(String username, String Password) {
        boolean succes = true;
        if (succes){
            presenter.loginSucces();
        }else{
            presenter.loginError("Ocurrió un Error");
        }
    }
}
