package com.example.filip.internalchat.api;


import com.example.filip.internalchat.model.Message;
import com.example.filip.internalchat.model.User;
import com.firebase.client.DataSnapshot;

import java.util.ArrayList;

/**
 * Created by Filip on 01/03/2016.
 */
public class DataManagerImpl implements DataManager {

    private final NetworkingHelper networkingHelper;

    private final DatabaseHelper databaseHelper;

    public DataManagerImpl(NetworkingHelper networkingHelper, DatabaseHelper databaseHelper) {
        this.networkingHelper = networkingHelper;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public void authenticateUserForRegistration(String username, String emoji, String email, String password, ResponseListener<User> listener) {
        networkingHelper.attemptToRegisterTheUser(username, emoji, email, password, listener);
    }

    @Override
    public void createUserAfterRegistration(String username, String emoji, String uid, UserCreateListener listener) {
        databaseHelper.createUserInUsersDirectory(username, emoji, uid, listener);
    }

    @Override
    public void authenticateUserForLoggingIn(String email, String password, ResponseListener<User> listener) {
        networkingHelper.attemptToLogTheUserIn(email, password, listener);
    }

    @Override
    public void createUserAfterLoggingIn(String username, String emoji, String uid, UserCreateListener listener) {
        databaseHelper.createUserInCurrentUsersDirectory(username, emoji, uid, listener);
    }

    @Override
    public void requestListOfRegisteredUsers(ResponseListener<DataSnapshot> listener) {
        networkingHelper.requestListOfRegisteredUsers(listener);
    }

    @Override
    public void requestListOfCurrentlyActiveUsers(ResponseListener<DataSnapshot> listener) {
        networkingHelper.requestLoggedInUsers(listener);
    }

    @Override
    public void requestNumberOfLoggedInUsers(ResponseListener<Long> listener) {
        networkingHelper.requestNumberOfLoggedInUsers(listener);
    }

    @Override
    public boolean checkIfUserExistsInDatabase(String username, DataSnapshot snapshot) {
        return (databaseHelper.checkIfUserExistsInDatabase(username, snapshot));
    }

    @Override
    public void requestMessages(ResponseListener<Message> listener) {
        networkingHelper.requestMessages(listener);
    }

    @Override
    public void sendMessage(String author, String message, String emoji) {
        networkingHelper.sendMessageToFirebase(author, message, emoji);
    }

    @Override
    public void removeUserFromCurrentUsers(String uid) {
        networkingHelper.logTheUserOut(uid);
    }

    @Override
    public ArrayList<User> createListOfCurrentlyLoggedInUsers(DataSnapshot snapshot) {
        return databaseHelper.createListOfCurrentUsers(snapshot);
    }
}