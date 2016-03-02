package com.example.filip.internalchat.ui.registration.presenter;

import com.example.filip.internalchat.api.DataManagerImpl;
import com.example.filip.internalchat.api.DatabaseHelperImpl;
import com.example.filip.internalchat.api.StringConstants;
import com.example.filip.internalchat.api.NetworkingHelperImpl;
import com.example.filip.internalchat.api.ResponseListener;
import com.example.filip.internalchat.ui.registration.view.UsernameFragmentView;
import com.firebase.client.DataSnapshot;

/**
 * Created by Filip on 27/02/2016.
 */
public class UsernamePresenterImpl implements UsernamePresenter {
    private final UsernameFragmentView usernameFragmentView;
    private final DataManagerImpl dataManager;

    public UsernamePresenterImpl(UsernameFragmentView view) {
        this.usernameFragmentView = view;
        this.dataManager = new DataManagerImpl(new NetworkingHelperImpl(), new DatabaseHelperImpl());
    }

    @Override
    public void checkIfUsernameIsTaken(final String username) {
        dataManager.requestListOfRegisteredUsers(new ResponseListener<DataSnapshot>() { //requests all the Users registered, if successful checks if the chosen username is already taken
            @Override
            public void onSuccess(DataSnapshot callback) {
                if (!dataManager.checkIfUserExistsInDatabase(username, callback)) {
                    onUserDoesNotExist(); //success, proceed with registration
                } else {
                    onUserAlreadyExists(StringConstants.USERNAME_ALREADY_EXISTS); //toast
                }
            }

            @Override
            public void onError(Throwable t) {
                //handle error
            }
        });
    }

    @Override
    public void onUserAlreadyExists(String message) {
        usernameFragmentView.onFailure(message);
    }

    @Override
    public void onUserDoesNotExist() {
        usernameFragmentView.onSuccess();
    }
}
