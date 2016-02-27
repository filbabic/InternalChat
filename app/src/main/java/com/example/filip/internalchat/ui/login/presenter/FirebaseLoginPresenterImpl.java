package com.example.filip.internalchat.ui.login.presenter;

import com.example.filip.internalchat.ui.login.interactor.LoginInteractor;
import com.example.filip.internalchat.ui.login.view.LoginView;

/**
 * Created by Filip on 23/02/2016.
 */
public class FirebaseLoginPresenterImpl implements FirebaseLoginPresenter {
    private final LoginView loginView;
    private final LoginInteractor interactor;

    public FirebaseLoginPresenterImpl(LoginView view) {
        this.loginView = view;
        interactor = new LoginInteractor(this);
    }

    @Override
    public void receiveUserLogin(String email, String password) {
        loginView.spinProgressBar();
        interactor.attemptToLogIn(email, password);
    }

    @Override
    public void onFailure() {
        loginView.stopProgressBar();
        loginView.onFailure();
    }

    @Override
    public void onSuccess(String user, String uid, String emoji) {
        loginView.stopProgressBar();
        loginView.logTheUserIn(user, uid, emoji);
    }
}
