package com.example.filip.internalchat.ui.registration.presenter;

import com.example.filip.internalchat.ui.registration.interactor.UsernameInteractor;
import com.example.filip.internalchat.ui.registration.view.UsernameFragmentView;

/**
 * Created by Filip on 27/02/2016.
 */
public class UsernamePresenterImpl implements UsernamePresenter {
    private final UsernameFragmentView usernameFragmentView;
    private final UsernameInteractor interactor;

    public UsernamePresenterImpl(UsernameFragmentView view) {
        this.usernameFragmentView = view;
        this.interactor = new UsernameInteractor(this);
    }

    @Override
    public void checkIfUsernameIsTaken(String username) {
        interactor.checkIfUsernameExists(username);
    }

    @Override
    public void onUserAlreadyExists() {
        usernameFragmentView.onFailure();
    }

    @Override
    public void onUserDoesNotExist() {
        usernameFragmentView.onSuccess();
    }
}
