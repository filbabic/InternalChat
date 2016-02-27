package com.example.filip.internalchat.ui.main.presenter;

import com.example.filip.internalchat.ui.main.interactor.MainInteractor;
import com.example.filip.internalchat.ui.main.view.MainView;

/**
 * Created by Filip on 24/02/2016.
 */
public class MainActivityPresenterImpl implements MainPresenter {
    private final MainView mainView;
    private final MainInteractor interactor;

    public MainActivityPresenterImpl(MainView view) {
        this.mainView = view;
        interactor = new MainInteractor(this);
    }


    @Override
    public void receiveRequest() {
        interactor.receiveRequest();
    }

    @Override
    public String getNumberOfUsers(long numberOfUsers) {
        return "Online users: " + String.valueOf(numberOfUsers);
    }

    @Override
    public void sendNumberOfChildren(long number) {
        mainView.setNumberOfUsersTextView(getNumberOfUsers(number));
    }
}
