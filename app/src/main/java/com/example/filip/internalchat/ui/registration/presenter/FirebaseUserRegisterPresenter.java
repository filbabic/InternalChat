package com.example.filip.internalchat.ui.registration.presenter;

/**
 * Created by Filip on 23/02/2016.
 */
public interface FirebaseUserRegisterPresenter {
    void receiveRegisterRequest(String username, String email, String password, String emoji);

    void userFailedToRegister();

    void userSuccessfullyRegistered();
}
