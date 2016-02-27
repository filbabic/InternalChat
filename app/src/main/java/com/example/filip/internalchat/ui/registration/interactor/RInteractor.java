package com.example.filip.internalchat.ui.registration.interactor;

import java.util.Map;

/**
 * Created by Filip on 24/02/2016.
 */
public interface RInteractor {
    void receiveRegisterRequest(String username, String email, String password, String emoji);
    Map<String, Object> createUser(String username, String emoji);
}
