package com.example.filip.internalchat.ui.adapter.users;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.filip.internalchat.R;
import com.example.filip.internalchat.model.User;

import java.util.ArrayList;

/**
 * Created by Filip on 24/02/2016.
 */

// Shows current online users in a list(by username)
public class CustomUsersAdapter extends RecyclerView.Adapter<CustomUsersAdapter.ViewHolder> implements CurrentAdapterView {
    private final ArrayList<User> mUserList = new ArrayList<>();
    private final CurrentUsersPresenterImpl presenter;

    public CustomUsersAdapter() {
        this.presenter = new CurrentUsersPresenterImpl(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User current = mUserList.get(position);
        holder.mUserTextView.setText(current.getUsername());
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    @Override
    public void addAll(ArrayList<User> users) {
        mUserList.clear();
        mUserList.addAll(users);
        notifyDataSetChanged();
    }

    @Override
    public void request() {
        presenter.request();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mUserTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mUserTextView = (TextView) itemView.findViewById(R.id.user_item_text_view);
        }
    }
}
