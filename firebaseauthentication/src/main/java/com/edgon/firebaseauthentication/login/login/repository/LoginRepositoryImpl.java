package com.edgon.firebaseauthentication.login.login.repository;


import android.app.Activity;
import android.support.annotation.NonNull;

import com.edgon.firebaseauthentication.login.login.presenter.LoginPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginRepositoryImpl implements LoginRepository{

    private LoginPresenter presenter;
    private FirebaseAuth firebaseAuth;

    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void signIn(String email, String password, Activity activity) {

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            presenter.loginSuccess();
                        }else{
                            presenter.loginError("Ocurrió un error");
                        }
                    }
                });
    }
}
