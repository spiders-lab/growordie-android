package com.fearlessspider.god.data;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    public String uid, username;
    @ServerTimestamp
    public Date createdAt;

    public User() {}

    public User(String uid, String username) {
        this.uid = uid;
        this.username = username;
    }

    @Exclude
    public String getUid() {
        return uid;
    }

    @Exclude
    public String getUsername() {
        return username;
    }
}
