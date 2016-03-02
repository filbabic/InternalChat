package com.example.filip.internalchat.api;


import com.example.filip.internalchat.model.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import java.util.ArrayList;

/**
 * Created by Filip on 01/03/2016.
 */
public class DatabaseHelperImpl implements DatabaseHelper {
    private final Firebase firebase;

    public DatabaseHelperImpl() {
        this.firebase = new Firebase(StringConstants.BASE_URL);
    }

    @Override
    public void createUserInCurrentUsersDirectory(String username, String emoji, String uid, UserCreateListener listener) {
        firebase.child(StringConstants.CURRENT_USERS_PATH + uid).setValue(FirebaseObjectHelper.createUserObject(username, emoji));
        listener.onSuccess();
    }

    @Override
    public void createUserInUsersDirectory(String username, String emoji, String uid, UserCreateListener listener) {
        firebase.child(StringConstants.USERS_PATH + uid).setValue(FirebaseObjectHelper.createUserObject(username, emoji));
        listener.onSuccess();
    }

    @Override
    public boolean checkIfUserExistsInDatabase(String username, DataSnapshot snapshot) {
        for (DataSnapshot x : snapshot.getChildren()) {
            if (x.getValue(User.class).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<User> createListOfCurrentUsers(DataSnapshot snapshot) {
        ArrayList<User> users = new ArrayList<>();
        for (DataSnapshot x : snapshot.getChildren()) {
            users.add(x.getValue(User.class));
        }
        return users;
    }
}