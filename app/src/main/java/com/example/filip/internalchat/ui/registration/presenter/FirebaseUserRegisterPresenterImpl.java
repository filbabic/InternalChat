package com.example.filip.internalchat.ui.registration.presenter;

import com.example.filip.internalchat.ui.registration.interactor.RegisterInteractor;
import com.example.filip.internalchat.ui.registration.view.RegisterView;

/**
 * Created by Filip on 23/02/2016.
 */
public class FirebaseUserRegisterPresenterImpl implements FirebaseUserRegisterPresenter {
    private final RegisterView registerView;
    private final RegisterInteractor interactor;

    public FirebaseUserRegisterPresenterImpl(RegisterView view) {
        this.registerView = view;
        this.interactor = new RegisterInteractor(this);
    }


    @Override
    public void receiveRegisterRequest(String username, String email, String password, String emoji) {
        interactor.receiveRegisterRequest(username, email, password, emoji);
        registerView.spinProgressBar();
    }

    @Override
    public void onFailure() {
        registerView.onFailure();
        registerView.stopProgressBar();
    }

    @Override
    public void onSuccess() {
        registerView.onSuccess();
        registerView.stopProgressBar();
    }
}
