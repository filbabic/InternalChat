package com.example.filip.internalchat.ui.chat.interactor;

import com.firebase.client.Firebase;

/**
 * Created by Filip on 24/02/2016.
 */
//When the chat activity's onDestroy is called, "logs the User out"
public class ChatLoginInteractor implements CLoginInteractor {
    @Override
    public void logTheUserOut(String uid) {
        Firebase userRef = new Firebase("https://<your-firebase>/currentUsers/" + uid);
        userRef.removeValue();
    }
}
