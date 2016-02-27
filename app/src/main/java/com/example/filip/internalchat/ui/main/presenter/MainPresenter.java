package com.example.filip.internalchat.ui.main.presenter;

/**
 * Created by Filip on 24/02/2016.
 */
public interface MainPresenter {
    void receiveRequest();
    String getNumberOfUsers(long numberOfUsers);
    void sendNumberOfChildren(long number);
}
