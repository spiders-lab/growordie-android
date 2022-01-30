package com.fearlessspider.god.data;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;

public class Journey implements Serializable {
    public String id, name, user_id;
    @ServerTimestamp
    public Date createdAt;

    public Journey() {
    }

    public Journey(String id, String name, String user_id) {
        this.id = id;
        this.name = name;
        this.user_id = user_id;
    }

    @Exclude
    public String getId() {
        return id;
    }

    @Exclude
    public String getName() {
        return name;
    }

    @Exclude
    public String getUserId() {
        return user_id;
    }
}
