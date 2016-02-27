package com.example.filip.internalchat.ui.adapter.registration;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.filip.internalchat.R;

/**
 * Created by Filip on 26/02/2016.
 */
//shows a list of emoji to choose from -- used as an avatar
public class EmojiRecyclerAdapter extends RecyclerView.Adapter<EmojiRecyclerAdapter.ViewHolder> {
    private final OnEmojiClickListener listener;
    private final String[] mEmoji = {"\uD83D\uDE00", "\uD83D\uDE08", "\uD83D\uDC7D", "\uD83D\uDCA9", "\uD83D\uDC7B"};

    public EmojiRecyclerAdapter(OnEmojiClickListener l) {
        this.listener = l;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.emoji_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String emoji = mEmoji[position];
        holder.mEmojiTextView.setText(emoji);
    }

    @Override
    public int getItemCount() {
        return mEmoji.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mEmojiTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mEmojiTextView = (TextView) itemView.findViewById(R.id.emoji_text_view);
            mEmojiTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onEmojiClick(mEmojiTextView.getText().toString());
        }
    }
}
