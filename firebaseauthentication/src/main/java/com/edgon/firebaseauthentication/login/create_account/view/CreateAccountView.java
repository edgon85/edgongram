package com.edgon.firebaseauthentication.login.create_account.view;


import com.google.firebase.auth.FirebaseAuth;

public interface CreateAccountView {

    void createAccount(String email, String password);
    void goHome();
    void createAccountError(String error);
}
