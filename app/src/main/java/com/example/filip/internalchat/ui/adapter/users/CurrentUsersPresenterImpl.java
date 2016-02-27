package com.example.filip.internalchat.ui.adapter.users;

import com.example.filip.internalchat.model.User;

import java.util.ArrayList;

/**
 * Created by Filip on 25/02/2016.
 */
public class CurrentUsersPresenterImpl implements CurrentUsersPresenter {
    private final CurrentAdapterView adapterView;
    private final CurrentUsersInteractor interactor;


    public CurrentUsersPresenterImpl(CurrentAdapterView view) {
        this.adapterView = view;
        this.interactor = new CurrentUsersInteractor(this);
    }

    @Override
    public void getChildren(ArrayList<User> users) {
        adapterView.addAll(users);
    }

    @Override
    public void request() {
        interactor.request();
    }

}
