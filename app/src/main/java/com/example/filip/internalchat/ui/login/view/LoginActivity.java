package com.example.filip.internalchat.ui.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.filip.internalchat.R;
import com.example.filip.internalchat.ui.login.presenter.FirebaseLoginPresenter;
import com.example.filip.internalchat.ui.login.presenter.FirebaseLoginPresenterImpl;
import com.example.filip.internalchat.ui.chat.view.ChatScreenActivity;
import com.firebase.client.Firebase;

/**
 * Created by Filip on 23/02/2016.
 */
public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {
    private ProgressBar mProgressBar;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private FirebaseLoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_login);
        createUI();
    }

    @Override
    public void logTheUserIn(String username, String uid, String emoji) {
        Intent i = new Intent(this, ChatScreenActivity.class);
        i.putExtra("uid", uid); //uid not key
        i.putExtra("username", username);
        i.putExtra("emoji", emoji);
        startActivity(i);
    }

    @Override
    public void onFailure() {
        Toast.makeText(LoginActivity.this, R.string.login_request_failure, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void spinProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void createUI() {
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mEmailEditText = (EditText) findViewById(R.id.login_email_edit_text);
        mPasswordEditText = (EditText) findViewById(R.id.login_password_edit_text);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(this);
        presenter = new FirebaseLoginPresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        presenter.receiveUserLoginRequest(mEmailEditText.getText().toString(), mPasswordEditText.getText().toString());
    }
}
