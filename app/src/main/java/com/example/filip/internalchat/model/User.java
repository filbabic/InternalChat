package com.example.filip.internalchat.model;

/**
 * Created by Filip on 23/02/2016.
 */
public class User {
    private String username;
    private String uid;
    private String emoji;

    public User() {
    }

    public User(String username, String emoji, String uid) {
        this.username = username;
        this.uid = uid;
        this.emoji = emoji;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }
}
