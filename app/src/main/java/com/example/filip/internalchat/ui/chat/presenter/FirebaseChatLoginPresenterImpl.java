package com.example.filip.internalchat.ui.chat.presenter;

import com.example.filip.internalchat.api.DataManagerImpl;
import com.example.filip.internalchat.api.DatabaseHelperImpl;
import com.example.filip.internalchat.api.NetworkingHelperImpl;

/**
 * Created by Filip on 23/02/2016.
 */
public class FirebaseChatLoginPresenterImpl implements FirebaseChatLoginPresenter {

    private final DataManagerImpl dataManager;

    public FirebaseChatLoginPresenterImpl() {
        this.dataManager = new DataManagerImpl(new NetworkingHelperImpl(), new DatabaseHelperImpl());
    }

    @Override
    public void removeUserFromCurrentUsers(String uid) {
        dataManager.removeUserFromCurrentUsers(uid);
    }
}
