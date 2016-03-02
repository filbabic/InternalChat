package com.example.filip.internalchat.ui.main.presenter;

import com.example.filip.internalchat.api.DataManagerImpl;
import com.example.filip.internalchat.api.DatabaseHelperImpl;
import com.example.filip.internalchat.api.NetworkingHelperImpl;
import com.example.filip.internalchat.api.ResponseListener;
import com.example.filip.internalchat.ui.main.view.MainView;

/**
 * Created by Filip on 24/02/2016.
 */
public class MainActivityPresenterImpl implements MainPresenter {
    private final MainView mainView;
    private final DataManagerImpl dataManager;

    public MainActivityPresenterImpl(MainView view) {
        this.mainView = view;
        this.dataManager = new DataManagerImpl(new NetworkingHelperImpl(), new DatabaseHelperImpl());
    }

    @Override
    public void receiveRequestForNumberOfLoggedInUsers() {
        dataManager.requestNumberOfLoggedInUsers(new ResponseListener<Long>() {
            @Override
            public void onSuccess(Long callback) {
                sendNumberOfChildren(callback);
            }

            @Override
            public void onError(Throwable t) {
                //handle error
            }
        });
    }

    @Override
    public String createNumberOfOnlineUsersString(long numberOfUsers) {
        return "Online users: " + String.valueOf(numberOfUsers);
    }

    @Override
    public void sendNumberOfChildren(long number) {
        mainView.setNumberOfUsersTextView(createNumberOfOnlineUsersString(number));
    }
}
