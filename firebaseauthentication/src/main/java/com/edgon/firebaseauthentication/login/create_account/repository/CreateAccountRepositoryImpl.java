package com.edgon.firebaseauthentication.login.create_account.repository;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.edgon.firebaseauthentication.login.create_account.interactor.CreateAccountInteractor;
import com.edgon.firebaseauthentication.login.create_account.presenter.CreateAccountPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountRepositoryImpl implements CreateAccountRepository{

    private static final String TAG = CreateAccountRepository.class.getName();
    private CreateAccountPresenter presenter;

    FirebaseAuth firebaseAuth;

    public CreateAccountRepositoryImpl(CreateAccountPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void createAccount(String email, String password, final Activity activity) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            presenter.createAccountSuccess();
                            Log.e(TAG,"Cuenta creada");
                        }else{
                            presenter.creteAccountError("Ocurrió un error");
                            Log.e(TAG,"Error al crear cuenta");
                            activity.finish();
                        }
                    }
                });
    }

    private void crearCuenta() {
        boolean succes = false;
        if (succes){
            presenter.createAccountSuccess();
        }else{
            presenter.creteAccountError("Ocurrió un error");
        }
    }
}
