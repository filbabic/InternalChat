package com.example.filip.internalchat.ui.main.presenter;

/**
 * Created by Filip on 24/02/2016.
 */
public interface MainPresenter {
    void receiveRequestForNumberOfLoggedInUsers();
    String createNumberOfOnlineUsersString(long numberOfUsers);
    void sendNumberOfChildren(long number);
}
