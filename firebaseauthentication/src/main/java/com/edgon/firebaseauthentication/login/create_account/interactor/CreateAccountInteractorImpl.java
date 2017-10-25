package com.edgon.firebaseauthentication.login.create_account.interactor;

import android.app.Activity;

import com.edgon.firebaseauthentication.login.create_account.presenter.CreateAccountPresenter;
import com.edgon.firebaseauthentication.login.create_account.repository.CreateAccountRepository;
import com.edgon.firebaseauthentication.login.create_account.repository.CreateAccountRepositoryImpl;
import com.google.firebase.auth.FirebaseAuth;


public class CreateAccountInteractorImpl implements CreateAccountInteractor {

    private CreateAccountPresenter presenter;
    private CreateAccountRepository repository;

    public CreateAccountInteractorImpl(CreateAccountPresenter presenter) {
        this.presenter = presenter;
        repository = new CreateAccountRepositoryImpl(presenter);

    }

    @Override
    public void createAccount(String email, String password, Activity activity) {
        repository.createAccount(email, password, activity);
    }
}
