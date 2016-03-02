package com.example.filip.internalchat.ui.chat.presenter;

import com.example.filip.internalchat.api.DataManagerImpl;
import com.example.filip.internalchat.api.DatabaseHelperImpl;
import com.example.filip.internalchat.api.NetworkingHelperImpl;

/**
 * Created by Filip on 25/02/2016.
 */
public class FirebaseChatMessagePresenterImpl implements FirebaseChatMessagePresenter {
    private final DataManagerImpl dataManager;

    public FirebaseChatMessagePresenterImpl() {
        this.dataManager = new DataManagerImpl(new NetworkingHelperImpl(), new DatabaseHelperImpl());
    }

    @Override
    public void sendMessage(String author, String message, String emoji) {
        dataManager.sendMessage(author, message, emoji);
    }
}
