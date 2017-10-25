package com.edgon.firebaseauthentication.login.create_account.repository;


import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

public interface CreateAccountRepository {

    void createAccount(String email, String password, Activity activity);
}
