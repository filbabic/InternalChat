package com.example.filip.internalchat.ui.registration.interactor;

import com.example.filip.internalchat.model.User;
import com.example.filip.internalchat.ui.registration.presenter.UsernamePresenter;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Filip on 27/02/2016.
 */
public class UsernameInteractor implements UInteractor {
    private final UsernamePresenter presenter;

    public UsernameInteractor(UsernamePresenter pre) {
        this.presenter = pre;
    }

    @Override
    public void checkIfUsernameExists(final String username) {
        Firebase userRef = new Firebase("https://<your-firebase>/Users/");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean exists = false;
                for (DataSnapshot x : dataSnapshot.getChildren()) {
                    User user = x.getValue(User.class);
                    if (user.getUsername().equals(username)) {
                        exists = true;
                        presenter.onUserAlreadyExists();
                    }
                    if (exists) break;
                }
                if (!exists) presenter.onUserDoesNotExist();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
}
