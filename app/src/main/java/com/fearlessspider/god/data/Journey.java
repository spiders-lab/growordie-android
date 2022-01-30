package com.fearlessspider.god.data;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;

public class Journey implements Serializable {
    public String uid, name, user_uid;
    @ServerTimestamp
    public Date createdAt;

    public Journey() {
    }

    public Journey(String uid, String name, String user_uid) {
        this.uid = uid;
        this.name = name;
        this.user_uid = user_uid;
    }

    @Exclude
    public String getUid() {
        return uid;
    }

    @Exclude
    public String getName() {
        return name;
    }

    @Exclude
    public String getUserUid() {
        return user_uid;
    }
}
