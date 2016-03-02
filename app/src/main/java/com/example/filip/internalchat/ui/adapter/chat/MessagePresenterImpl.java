package com.example.filip.internalchat.ui.adapter.chat;

import com.example.filip.internalchat.api.DataManagerImpl;
import com.example.filip.internalchat.api.DatabaseHelperImpl;
import com.example.filip.internalchat.api.NetworkingHelperImpl;
import com.example.filip.internalchat.api.ResponseListener;
import com.example.filip.internalchat.model.Message;

/**
 * Created by Filip on 25/02/2016.
 */
public class MessagePresenterImpl implements MessagePresenter {
    private final MessageAdapterView adapterView;
    private final DataManagerImpl dataManager;

    public MessagePresenterImpl(MessageAdapterView view) {
        this.adapterView = view;
        this.dataManager = new DataManagerImpl(new NetworkingHelperImpl(), new DatabaseHelperImpl());
    }

    @Override
    public void requestMessagesFromFirebase() {
        dataManager.requestMessages(new ResponseListener<Message>() {
            @Override
            public void onSuccess(Message callback) {
                adapterView.addMessageToAdapter(callback);
            }

            @Override
            public void onError(Throwable t) {
                //handle error
            }
        });
    }
}
