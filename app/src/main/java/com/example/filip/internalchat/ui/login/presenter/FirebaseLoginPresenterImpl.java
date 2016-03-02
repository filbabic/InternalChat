package com.example.filip.internalchat.ui.login.presenter;

import com.example.filip.internalchat.api.DataManagerImpl;
import com.example.filip.internalchat.api.DatabaseHelperImpl;
import com.example.filip.internalchat.api.NetworkingHelperImpl;
import com.example.filip.internalchat.api.ResponseListener;
import com.example.filip.internalchat.api.UserCreateListener;
import com.example.filip.internalchat.model.User;
import com.example.filip.internalchat.ui.login.view.LoginView;

/**
 * Created by Filip on 23/02/2016.
 */
public class FirebaseLoginPresenterImpl implements FirebaseLoginPresenter {
    private final LoginView loginView;
    private final DataManagerImpl dataManager;

    public FirebaseLoginPresenterImpl(LoginView view) {
        this.loginView = view;
        this.dataManager = new DataManagerImpl(new NetworkingHelperImpl(), new DatabaseHelperImpl());
    }

    @Override
    public void receiveUserLoginRequest(String email, String password) {
        loginView.spinProgressBar();
        dataManager.authenticateUserForLoggingIn(email, password, new ResponseListener<User>() {
            @Override
            public void onSuccess(final User callback) {
                dataManager.createUserAfterLoggingIn(callback.getUsername(), callback.getEmoji(), callback.getUid(), new UserCreateListener() {
                    @Override
                    public void onSuccess() {
                        onSuccessfulLogin(callback.getUsername(), callback.getUid(), callback.getEmoji());
                    }

                    @Override
                    public void onError() {
                        onFailedLogin();
                    }
                });
            }

            @Override
            public void onError(Throwable t) {
                //handle error
            }
        });
    }

    @Override
    public void onFailedLogin() {
        loginView.stopProgressBar();
        loginView.onFailure();
    }

    @Override
    public void onSuccessfulLogin(String user, String uid, String emoji) {
        loginView.stopProgressBar();
        loginView.logTheUserIn(user, uid, emoji);
    }
}
