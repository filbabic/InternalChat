package com.example.filip.internalchat.ui.login.interactor;

import com.example.filip.internalchat.model.User;
import com.example.filip.internalchat.ui.login.presenter.FirebaseLoginPresenter;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Filip on 24/02/2016.
 */
//// TODO: 26/02/2016 add emoji to users and implement them into messages
public class LoginInteractor implements LInteractor {
    private Firebase userRef = new Firebase("https://<your-firebase>/Users/");
    private final FirebaseLoginPresenter presenter;

    public LoginInteractor(FirebaseLoginPresenter pre) {
        this.presenter = pre;
    }

    @Override
    public void attemptToLogIn(String email, String password) {
        userRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(final AuthData authData) {
                userRef = new Firebase("https://<your-firebase>/Users/" + authData.getUid());
                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        Firebase loggedUser = new Firebase("https://<your-firebase>/currentUsers/" + authData.getUid()); //redundant?
                        loggedUser.setValue(createUser(user.getUsername(), user.getEmoji()));
                        presenter.onSuccess(user.getUsername(), authData.getUid(), user.getEmoji());
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                presenter.onFailure();
            }
        });

    }

    @Override
    public Map<String, Object> createUser(String user, String emoji) {
        Map<String, Object> userToCreate = new HashMap<>();
        userToCreate.put("username", user);
        userToCreate.put("emoji", emoji);
        return userToCreate;
    }
}
