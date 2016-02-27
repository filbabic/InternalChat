package com.example.filip.internalchat.ui.main.interactor;

import com.example.filip.internalchat.ui.main.presenter.MainPresenter;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Filip on 24/02/2016.
 */
public class MainInteractor implements MInteractor {
    private final Firebase mainRef = new Firebase("https://<your-firebase>/currentUsers");
    private final MainPresenter presenter;

    public MainInteractor(MainPresenter pre) {
        this.presenter = pre;
    }

    @Override
    public void receiveRequest() {
        mainRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                presenter.sendNumberOfChildren(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
}
