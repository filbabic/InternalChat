package com.example.filip.internalchat.ui.login.interactor;

import java.util.Map;

/**
 * Created by Filip on 24/02/2016.
 */
public interface LInteractor {
    void attemptToLogIn(String email, String password);

    Map<String, Object> createUser(String user, String emoji);

}
