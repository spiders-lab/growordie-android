package com.fearlessspider.god.data;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;

public class Track implements Serializable {
    public String id, name, journey_id;
    @ServerTimestamp
    public Date createdAt;

    public Track() {
    }

    public Track(String id, String name, String journey_id) {
        this.id = id;
        this.name = name;
        this.journey_id = journey_id;
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
    public String getJourneyId() {
        return journey_id;
    }
}
