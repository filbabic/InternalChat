package com.example.filip.internalchat.ui.adapter.chat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.filip.internalchat.R;
import com.example.filip.internalchat.api.StringConstants;
import com.example.filip.internalchat.model.Message;

import java.util.ArrayList;

/**
 * Created by Filip on 27/02/2016.
 */
public class CustomMessageRecyclerAdapter extends RecyclerView.Adapter<CustomMessageRecyclerAdapter.ViewHolder> implements MessageAdapterView {
    private final ArrayList<Message> mMessageList = new ArrayList<>();
    private final String user;
    private final MessagePresenter presenter;

    public CustomMessageRecyclerAdapter(String username) {
        this.user = username;
        presenter = new MessagePresenterImpl(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message current = mMessageList.get(position);
        if (current.getAuthor().equals(user)) {
            holder.mAuthorTextView.setText(StringConstants.USER_TEXT_VIEW_CHAT);
        } else {
            holder.mAuthorTextView.setText(current.getAuthor());
        }
        holder.mMessageTextView.setText(current.getMessage());
        holder.mEmojiTextView.setText(current.getEmoji());
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    @Override
    public void addMessageToAdapter(Message message) {
        mMessageList.add(message);
        notifyDataSetChanged();
    }

    @Override
    public void requestMessagesFromFirebase() {
        presenter.requestMessagesFromFirebase();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mAuthorTextView;
        private TextView mMessageTextView;
        private TextView mEmojiTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mAuthorTextView = (TextView) itemView.findViewById(R.id.message_author_text_view);
            mMessageTextView = (TextView) itemView.findViewById(R.id.message_value_text_view);
            mEmojiTextView = (TextView) itemView.findViewById(R.id.message_emoji_text_view);
        }
    }
}