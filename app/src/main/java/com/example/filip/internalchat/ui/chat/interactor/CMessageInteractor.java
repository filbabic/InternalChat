package com.example.filip.internalchat.ui.chat.interactor;

import java.util.Map;

/**
 * Created by Filip on 25/02/2016.
 */
public interface CMessageInteractor {
    void pushMessageToFirebase(String author, String message, String emoji);

    Map<String, Object> createMessage(String message, String author, String emoji);
}
