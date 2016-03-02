package com.example.filip.internalchat.ui.login.presenter;


/**
 * Created by Filip on 23/02/2016.
 */
public interface FirebaseLoginPresenter {
    void receiveUserLoginRequest(String email, String password);

    void onFailedLogin();

    void onSuccessfulLogin(String user, String uid, String emoji);
}
