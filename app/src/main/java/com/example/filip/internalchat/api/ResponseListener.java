package com.example.filip.internalchat.api;

/**
 * Created by Filip on 01/03/2016.
 */
public interface ResponseListener<T> {
    void onSuccess(T callback);

    void onError(Throwable t);
}
