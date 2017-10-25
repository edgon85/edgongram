package com.edgon.firebaseauthentication.login.login.repository;

import android.app.Activity;

public interface LoginRepository {
    void signIn(String email, String password, Activity activity);
}
