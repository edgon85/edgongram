package com.edgon.edgongram.login.repositori;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.edgon.edgongram.login.presenter.LoginPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginRepositoryImpl implements LoginRepository {



    private LoginPresenter presenter;

    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void signIn(String userName, String password, final Activity activity, FirebaseAuth firebaseAuth) {
        //pruebaLogin();
        firebaseAuth.signInWithEmailAndPassword(userName, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            FirebaseUser user = task.getResult().getUser();
                            SharedPreferences sharedPref =
                                    activity.getSharedPreferences("USER", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor =
                                    sharedPref.edit();

                            editor.putString("email", user.getEmail());
                            editor.commit();

                            presenter.loginSucces();
                        }else{
                            presenter.loginError("Ocurrió un error");
                        }
                    }
                });

    }

    private void pruebaLogin() {
        boolean succes = true;
        if (succes){
            presenter.loginSucces();
        }else{
            presenter.loginError("Ocurrió un error");
        }
    }


}
