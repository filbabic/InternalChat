package com.example.filip.internalchat.ui.adapter.users;

import com.example.filip.internalchat.model.User;

import java.util.ArrayList;

/**
 * Created by Filip on 25/02/2016.
 */
public interface CurrentUsersPresenter {
    void sendChildrenToAdapter(ArrayList<User> users);
    void requestCurrentUsersFromFirebase();
}
