package com.edgon.edgongram.login.repositori;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.edgon.edgongram.login.presenter.LoginPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by gonza on 8/8/2017.
 */

public class LoginRepositoryImpl implements LoginRepository {

    private LoginPresenter presenter;

    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void signIn(String userName, String password, Activity activity, FirebaseAuth firebaseAuth) {

        firebaseAuth.signInWithEmailAndPassword(userName,password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            presenter.loginSucces();
                        }else{
                            presenter.loginError("Ocurrió un Error");
                        }
                    }
                });


        /*boolean succes = true;
        if (succes){
            presenter.loginSucces();
        }else{
            presenter.loginError("Ocurrió un error");
        }*/
    }
}
