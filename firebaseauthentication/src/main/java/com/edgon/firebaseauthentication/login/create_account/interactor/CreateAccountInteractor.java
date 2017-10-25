package com.edgon.firebaseauthentication.login.create_account.interactor;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Edgon on 23/10/17.
 */

public interface CreateAccountInteractor {
    void createAccount(String email, String password, Activity activity);
}
