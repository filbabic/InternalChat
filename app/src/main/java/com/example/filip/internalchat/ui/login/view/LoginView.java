package com.example.filip.internalchat.ui.login.view;

/**
 * Created by Filip on 23/02/2016.
 */
public interface LoginView {
    void logTheUserIn(String username, String uid, String emoji);

    void onFailure();

    void spinProgressBar();

    void stopProgressBar();
}
