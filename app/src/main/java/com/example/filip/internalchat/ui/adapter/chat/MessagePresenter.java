package com.example.filip.internalchat.ui.adapter.chat;

import com.example.filip.internalchat.model.Message;

/**
 * Created by Filip on 25/02/2016.
 */
public interface MessagePresenter {
    void sendMessageToAdapter(Message message);
    void requestMessages();
}
