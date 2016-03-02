package com.example.filip.internalchat.api;

import com.example.filip.internalchat.model.Message;
import com.example.filip.internalchat.model.User;
import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.Map;

/**
 * Created by Filip on 01/03/2016.
 */
public class NetworkingHelperImpl implements NetworkingHelper {

    private final Firebase firebase;

    public NetworkingHelperImpl() {
        firebase = new Firebase(StringConstants.BASE_URL);
    }

    public void attemptToLogTheUserIn(String email, String password, final ResponseListener<User> responseListener) {
        firebase.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                String uid = authData.getUid();
                logTheUserIn(uid, responseListener);
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                responseListener.onError(firebaseError.toException());
            }
        });
    }

    public void attemptToRegisterTheUser(final String username, final String emoji, String email, String password, final ResponseListener<User> listener) {
        firebase.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                String uid = stringObjectMap.get(StringConstants.UID).toString();
                User user = new User(username, emoji, uid);
                listener.onSuccess(user);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                listener.onError(firebaseError.toException());
            }
        });
    }


    private void logTheUserIn(final String uid, final ResponseListener<User> responseListener) {
        firebase.child(StringConstants.USERS_PATH + uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                user.setUid(uid);
                responseListener.onSuccess(user);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                responseListener.onError(firebaseError.toException());
            }
        });
    }


    @Override
    public void requestNumberOfLoggedInUsers(final ResponseListener<Long> listener) {
        firebase.child(StringConstants.CURRENT_USERS_PATH).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                listener.onError(firebaseError.toException());
            }
        });
    }

    @Override
    public void requestListOfRegisteredUsers(final ResponseListener<DataSnapshot> listener) {
        firebase.child(StringConstants.USERS_PATH).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                listener.onError(firebaseError.toException());
            }
        });

    }

    @Override
    public void requestLoggedInUsers(final ResponseListener<DataSnapshot> listener) {
        firebase.child(StringConstants.CURRENT_USERS_PATH).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                listener.onError(firebaseError.toException());
            }
        });
    }

    @Override
    public void requestMessages(final ResponseListener<Message> listener) {
        Query messageQuery = firebase.child(StringConstants.MESSAGE_PATH).orderByValue().limitToLast(100);
        messageQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                listener.onSuccess(dataSnapshot.getValue(Message.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //// TODO: 01/03/2016 add message editing feature
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //// TODO: 01/03/2016 add message deletion feature
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                //ok nothing
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                listener.onError(firebaseError.toException());
            }
        });
    }

    @Override
    public void sendMessageToFirebase(String author, String message, String emoji) {
        firebase.child(StringConstants.MESSAGE_PATH).push().setValue(FirebaseObjectHelper.createMessageObject(author, message, emoji));
    }

    @Override
    public void logTheUserOut(String uid) {
        firebase.child(StringConstants.CURRENT_USERS_PATH + uid).removeValue();
    }
}