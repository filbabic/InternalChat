package com.example.filip.internalchat.ui.registration.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.filip.internalchat.R;
import com.example.filip.internalchat.ui.registration.presenter.UsernamePresenterImpl;

/**
 * Created by Filip on 27/02/2016.
 */
public class ChooseUsernameFragment extends Fragment implements View.OnClickListener, UsernameFragmentView {
    private EditText mUsernameEditText;
    private Button mContinueButton;
    private UsernamePresenterImpl presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_first, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createUI(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.presenter = new UsernamePresenterImpl(this);
    }

    private void createUI(View view) {
        mUsernameEditText = (EditText) view.findViewById(R.id.reg_username_edit_text);
        mContinueButton = (Button) view.findViewById(R.id.continue_button);
        mContinueButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        presenter.checkIfUsernameIsTaken(mUsernameEditText.getText().toString());
    }

    @Override
    public void onSuccess() {
        Bundle data = new Bundle();
        data.putString("username", mUsernameEditText.getText().toString());
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.register_activity_frame_layout, ChooseAnEmojiFragment.newInstance(data), "emoji").addToBackStack("emoji").commit();
    }

    @Override
    public void onFailure() {
        Toast.makeText(getActivity(), R.string.username_taken, Toast.LENGTH_SHORT).show();
    }
}
