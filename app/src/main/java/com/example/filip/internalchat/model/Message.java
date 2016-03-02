package com.example.filip.internalchat.model;

/**
 * Created by Filip on 23/02/2016.
 */
public class Message {
    private String author;
    private String message;
    private String emoji;

    public Message() {
    }

    public Message(String author, String message, String emoji) {
        this.author = author;
        this.message = message;
        this.emoji = emoji;
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
