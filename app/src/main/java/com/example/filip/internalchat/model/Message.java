package com.example.filip.internalchat.model;

import java.io.Serializable;

/**
 * Created by Filip on 23/02/2016.
 */
public class Message implements Serializable {
    private String author;
    private String message;
    private String emoji;

    public Message() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }
}
