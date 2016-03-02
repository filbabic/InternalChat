package com.example.filip.internalchat.api;

import com.example.filip.internalchat.model.Message;
import com.example.filip.internalchat.model.User;
import com.firebase.client.DataSnapshot;

import java.util.ArrayList;

/**
 * Created by Filip on 01/03/2016.
 */
public interface DataManager {

    void createUserAfterRegistration(String username, String emoji, String uid, UserCreateListener listener);

    void authenticateUserForRegistration(String username, String emoji, String email, String password, ResponseListener<User> listener);

    void authenticateUserForLoggingIn(String email, String password, ResponseListener<User> listener);

    void createUserAfterLoggingIn(String username, String emoji, String uid, UserCreateListener listener);

    void requestListOfRegisteredUsers(ResponseListener<DataSnapshot> listener);

    void requestListOfCurrentlyActiveUsers(ResponseListener<DataSnapshot> listener);

    void requestNumberOfLoggedInUsers(ResponseListener<Long> listener);

    boolean checkIfUserExistsInDatabase(String username, DataSnapshot snapshot);

    void requestMessages(ResponseListener<Message> listener);

    void sendMessage(String author, String message, String emoji);

    void removeUserFromCurrentUsers(String uid);

    ArrayList<User> createListOfCurrentlyLoggedInUsers(DataSnapshot snapshot);
}
