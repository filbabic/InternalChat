package com.example.filip.internalchat.api;

import com.example.filip.internalchat.model.User;
import com.firebase.client.DataSnapshot;

import java.util.ArrayList;

/**
 * Created by Filip on 01/03/2016.
 */
public interface DatabaseHelper {
    void createUserInCurrentUsersDirectory(String username, String emoji, String uid, UserCreateListener listener);

    void createUserInUsersDirectory(String username, String emoji, String uid, UserCreateListener listener);

    boolean checkIfUserExistsInDatabase(String username, DataSnapshot snapshot);

    ArrayList<User> createListOfCurrentUsers(DataSnapshot snapshot);
}