package com.example.filip.internalchat.ui.registration.presenter;

import com.example.filip.internalchat.api.DataManagerImpl;
import com.example.filip.internalchat.api.DatabaseHelperImpl;
import com.example.filip.internalchat.api.NetworkingHelperImpl;
import com.example.filip.internalchat.api.ResponseListener;
import com.example.filip.internalchat.api.UserCreateListener;
import com.example.filip.internalchat.model.User;
import com.example.filip.internalchat.ui.registration.view.RegisterView;

/**
 * Created by Filip on 23/02/2016.
 */
public class FirebaseUserRegisterPresenterImpl implements FirebaseUserRegisterPresenter {
    private final RegisterView registerView;
    private final DataManagerImpl dataManager;

    public FirebaseUserRegisterPresenterImpl(RegisterView view) {
        this.registerView = view;
        this.dataManager = new DataManagerImpl(new NetworkingHelperImpl(), new DatabaseHelperImpl());
    }

    @Override
    public void receiveRegisterRequest(final String username, String email, String password, final String emoji) {
        registerView.showProgressBar();
        dataManager.authenticateUserForRegistration(username, emoji, email, password, new ResponseListener<User>() {
            @Override
            public void onSuccess(User callback) { //registration passed, proceed to creation of Directory in /Users
                dataManager.createUserAfterRegistration(username, emoji, callback.getUid(), new UserCreateListener() {
                    @Override
                    public void onSuccess() {
                        userSuccessfullyRegistered();
                    }

                    @Override
                    public void onError() {
                        userFailedToRegister();
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
    public void userFailedToRegister() {
        registerView.onFailure();
        registerView.hideProgressBar();
    }

    @Override
    public void userSuccessfullyRegistered() {
        registerView.onSuccess();
        registerView.hideProgressBar();
    }
}
