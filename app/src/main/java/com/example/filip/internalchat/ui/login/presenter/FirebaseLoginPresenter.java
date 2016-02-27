package com.example.filip.internalchat.ui.login.presenter;



/**
 * Created by Filip on 23/02/2016.
 */
public interface FirebaseLoginPresenter {
    void receiveUserLogin(String email, String password);
    void onFailure();
    void onSuccess(String user, String uid, String emoji);
}
