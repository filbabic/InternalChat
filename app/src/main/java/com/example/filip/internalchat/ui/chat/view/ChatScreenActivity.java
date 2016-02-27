package com.example.filip.internalchat.ui.chat.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.filip.internalchat.R;
import com.example.filip.internalchat.ui.chat.presenter.FirebaseChatLoginPresenterImpl;
import com.firebase.client.Firebase;

/**
 * Created by Filip on 23/02/2016.
 */
public class ChatScreenActivity extends AppCompatActivity {
    private FirebaseChatLoginPresenterImpl presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_service);
        Firebase.setAndroidContext(this);
        Toast.makeText(ChatScreenActivity.this, "Welcome, " + getIntent().getStringExtra("username") + "!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.chat_activity_frame_layout, new ChatFragment(), "chat");
        transaction.commit();
        presenter = new FirebaseChatLoginPresenterImpl();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_chat, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() == 0) {
            manager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.chat_activity_frame_layout, new ListOfUsersFragment(), "list").addToBackStack("list").commit();
        } else if (manager.getBackStackEntryCount() != 0) {
            manager.popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.removeUserFromCurrentUsers(getIntent().getStringExtra("uid"));
    }
}
