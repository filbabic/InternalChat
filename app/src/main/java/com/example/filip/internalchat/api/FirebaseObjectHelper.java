package com.example.filip.internalchat.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Filip on 01/03/2016.
 */
public class FirebaseObjectHelper {
    private final static String EMOJI = "emoji";
    private final static String USERNAME = "username";

    private final static String MESSAGE = "message";
    private final static String AUTHOR = "author";

    public static Map<String, Object> createUserObject(String user, String emoji) {
        Map<String, Object> userToCreate = new HashMap<>();
        userToCreate.put(USERNAME, user);
        userToCreate.put(EMOJI, emoji);
        return userToCreate;
    }

    public static Map<String, Object> createMessageObject(String author, String message, String emoji) {
        Map<String, Object> messageToCreate = new HashMap<>();
        messageToCreate.put(AUTHOR, author);
        messageToCreate.put(MESSAGE, message);
        messageToCreate.put(EMOJI, emoji);
        return messageToCreate;
    }
}
