package com.example.filip.internalchat.ui.registration.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.filip.internalchat.R;
import com.example.filip.internalchat.ui.adapter.registration.EmojiRecyclerAdapter;
import com.example.filip.internalchat.ui.adapter.registration.OnEmojiClickListener;

/**
 * Created by Filip on 26/02/2016.
 */
public class ChooseAnEmojiFragment extends Fragment implements OnEmojiClickListener {
    private RecyclerView mEmojiList;
    private EmojiRecyclerAdapter adapter;

    public static ChooseAnEmojiFragment newInstance(Bundle data) {
        ChooseAnEmojiFragment f = new ChooseAnEmojiFragment();
        f.setArguments(data);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_second, container, false);
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

    private void fillUI() {
        adapter = new EmojiRecyclerAdapter(this);
        mEmojiList.setAdapter(adapter);
    }

    private void createUI(View view) {
        mEmojiList = (RecyclerView) view.findViewById(R.id.emoji_recycler_view);
        mEmojiList.setLayoutManager(new GridLayoutManager(getActivity(), 5));
    }

    @Override
    public void onEmojiClick(String emoji) {
        showDialog(emoji);
    }

    private void showDialog(final String emoji) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("You chose: " + emoji + " , continue?");
        builder.setPositiveButton("Yeah!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                continueToEmail(emoji);
            }
        });
        builder.setNegativeButton("Go back!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void continueToEmail(String emoji) {
        Bundle data = this.getArguments();
        data.putString("emoji", emoji);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.register_activity_frame_layout, ChooseEmailFragment.newInstance(data), "email").addToBackStack("email").commit();
    }
}