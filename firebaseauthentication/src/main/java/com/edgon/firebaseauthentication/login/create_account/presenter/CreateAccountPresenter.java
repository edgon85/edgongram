package com.edgon.firebaseauthentication.login.create_account.presenter;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Edgon on 23/10/17.
 */

public interface CreateAccountPresenter {

    void createAcount(String email, String password,Activity activity);
    void createAccountSuccess();
    void creteAccountError(String error);
}
