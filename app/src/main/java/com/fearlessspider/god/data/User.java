package com.fearlessspider.god.data;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    public String id, username;
    @ServerTimestamp
    public Date createdAt;

    public User() {
    }

    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }

    @Exclude
    public String getId() {
        return id;
    }

    @Exclude
    public String getUsername() {
        return username;
    }
}
