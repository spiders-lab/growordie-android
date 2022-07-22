package com.fearlessspider.god.data;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;

public class Step implements Serializable {
    public String id;
    public Integer steps;
    @ServerTimestamp
    public Date createdAt;

    public Step() {
    }

    public Step(String id, Integer steps) {
        this.id = id;
        this.steps = steps;
    }

    @Exclude
    public String getId() {
        return id;
    }

    @Exclude
    public Integer getSteps() {
        return steps;
    }
}
