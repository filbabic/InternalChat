package com.example.filip.internalchat.ui.adapter.chat;

import com.example.filip.internalchat.model.Message;

/**
 * Created by Filip on 25/02/2016.
 */
public class MessagePresenterImpl implements MessagePresenter {
    private final MessageAdapterView adapterView;
    private final MessageInteractor interactor;

    public MessagePresenterImpl(MessageAdapterView view) {
        this.adapterView = view;
        this.interactor = new MessageInteractor(this);
    }

    @Override
    public void sendMessageToAdapter(Message message) {
        adapterView.addItem(message);
    }

    @Override
    public void requestMessages() {
        interactor.request();
    }
}
