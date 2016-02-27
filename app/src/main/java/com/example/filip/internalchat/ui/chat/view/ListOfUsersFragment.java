package com.example.filip.internalchat.ui.chat.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.filip.internalchat.R;
import com.example.filip.internalchat.ui.adapter.users.CustomUsersAdapter;

/**
 * Created by Filip on 25/02/2016.
 */
public class ListOfUsersFragment extends Fragment {
    private RecyclerView mListOfOnlineUsers;
    private CustomUsersAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_online_users_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createUI(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        fillUI();
    }

    private void createUI(View view) {
        mListOfOnlineUsers = (RecyclerView) view.findViewById(R.id.online_user_recycler_view);
        mListOfOnlineUsers.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListOfOnlineUsers.setHasFixedSize(true);
        mListOfOnlineUsers.setItemAnimator(new DefaultItemAnimator());
    }

    private void fillUI() {
        adapter = new CustomUsersAdapter();
        adapter.request();
        mListOfOnlineUsers.setAdapter(adapter);
    }
}
