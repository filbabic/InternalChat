package com.example.filip.internalchat.ui.adapter.users;

import com.example.filip.internalchat.api.DataManager;
import com.example.filip.internalchat.api.DataManagerImpl;
import com.example.filip.internalchat.api.DatabaseHelperImpl;
import com.example.filip.internalchat.api.NetworkingHelperImpl;
import com.example.filip.internalchat.api.ResponseListener;
import com.example.filip.internalchat.model.User;
import com.firebase.client.DataSnapshot;

import java.util.ArrayList;

/**
 * Created by Filip on 25/02/2016.
 */
public class CurrentUsersPresenterImpl implements CurrentUsersPresenter {
    private final CurrentAdapterView adapterView;
    private final DataManager dataManager;


    public CurrentUsersPresenterImpl(CurrentAdapterView view) {
        this.adapterView = view;
        this.dataManager = new DataManagerImpl(new NetworkingHelperImpl(), new DatabaseHelperImpl());
    }

    @Override
    public void sendChildrenToAdapter(ArrayList<User> users) {
        adapterView.addAllOnlineUsersToAdapter(users);
    }

    @Override
    public void requestCurrentUsersFromFirebase() {
        dataManager.requestListOfCurrentlyActiveUsers(new ResponseListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot callback) {
                sendChildrenToAdapter(dataManager.createListOfCurrentlyLoggedInUsers(callback));
            }

            @Override
            public void onError(Throwable t) {
                //handle error
            }
        });
    }
}
