package com.edgon.edgongram.login.repositori;

import com.edgon.edgongram.login.presenter.LoginPresenter;

/**
 * Created by gonza on 8/8/2017.
 */

public class LoginRepositoryImpl implements LoginRepository {

    private LoginPresenter presenter;

    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void signIn(String userName, String Password) {
        boolean succes = true;
        if (succes){
            presenter.loginSucces();
        }else{
            presenter.loginError("Ocurrió un error");
        }
    }
}
