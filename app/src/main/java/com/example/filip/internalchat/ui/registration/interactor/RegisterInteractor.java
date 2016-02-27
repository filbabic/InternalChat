package com.example.filip.internalchat.ui.registration.interactor;

import com.example.filip.internalchat.ui.registration.presenter.FirebaseUserRegisterPresenter;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Filip on 24/02/2016.
 */
public class RegisterInteractor implements RInteractor {
    private Firebase userRef = new Firebase("https://<your-firebase>/Users/");
    private final FirebaseUserRegisterPresenter presenter;

    public RegisterInteractor(FirebaseUserRegisterPresenter pre) {
        this.presenter = pre;
    }

    @Override
    public void receiveRegisterRequest(final String username, String email, String password, final String emoji) {
        userRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                String uid = stringObjectMap.get("uid").toString();
                userRef = new Firebase("https://<your-firebase>/Users/" + uid);
                userRef.setValue(createUser(username, emoji));
                presenter.onSuccess();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                presenter.onFailure();
            }
        });
    }

    @Override
    public Map<String, Object> createUser(String username, String emoji) {
        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
        user.put("emoji", emoji);
        return user;
    }
}
