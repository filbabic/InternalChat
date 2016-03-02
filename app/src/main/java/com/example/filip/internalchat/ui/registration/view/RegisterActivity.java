package com.example.filip.internalchat.ui.registration.view;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.filip.internalchat.R;
import com.example.filip.internalchat.api.StringConstants;
import com.firebase.client.Firebase;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Firebase.setAndroidContext(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        addStartFragment();
    }

    private void addStartFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(R.id.register_activity_frame_layout, new ChooseUsernameFragment(), StringConstants.USERNAME_BUNDLE_KEY)
                .commit();
    }
}