package com.example.filip.internalchat.api;

import com.example.filip.internalchat.model.Message;
import com.example.filip.internalchat.model.User;
import com.firebase.client.DataSnapshot;

/**
 * Created by Filip on 01/03/2016.
 */
public interface NetworkingHelper {
    void attemptToLogTheUserIn(String email, String password, final ResponseListener<User> responseListener);

    void attemptToRegisterTheUser(final String username, final String emoji, String email, String password, final ResponseListener<User> responseListener);

    void requestNumberOfLoggedInUsers(ResponseListener<Long> listener);

    void requestListOfRegisteredUsers(ResponseListener<DataSnapshot> listener);

    void requestLoggedInUsers(ResponseListener<DataSnapshot> listener);

    void requestMessages(ResponseListener<Message> listener);

    void sendMessageToFirebase(String author, String message, String emoji);

    void logTheUserOut(String uid);
}