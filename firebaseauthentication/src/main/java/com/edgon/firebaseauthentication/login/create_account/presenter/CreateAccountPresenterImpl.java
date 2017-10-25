package com.edgon.firebaseauthentication.login.create_account.presenter;


import android.app.Activity;

import com.edgon.firebaseauthentication.login.create_account.interactor.CreateAccountInteractor;
import com.edgon.firebaseauthentication.login.create_account.interactor.CreateAccountInteractorImpl;
import com.edgon.firebaseauthentication.login.create_account.view.CreateAccountView;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountPresenterImpl implements CreateAccountPresenter {

    private CreateAccountInteractor interactor;
    private CreateAccountView view;

    public CreateAccountPresenterImpl(CreateAccountView view) {
        this.view = view;
        interactor = new CreateAccountInteractorImpl(this);

    }

    @Override
    public void createAcount(String email, String password, Activity activity) {
        interactor.createAccount(email, password, activity);
    }

    @Override
    public void createAccountSuccess() {
        view.goHome();
    }

    @Override
    public void creteAccountError(String error) {
        view.createAccountError(error);
    }
}
