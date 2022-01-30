package com.fearlessspider.god.data;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;

public class Exercise implements Serializable {
    public String id, comment, track_id;
    @ServerTimestamp
    public Date createdAt;

    public Exercise() {
    }

    public Exercise(String id, String comment, String track_id) {
        this.id = id;
        this.comment = comment;
        this.track_id = track_id;
    }

    @Exclude
    public String getId() {
        return id;
    }

    @Exclude
    public String getComment() {
        return comment;
    }

    @Exclude
    public String getTrackId() {
        return track_id;
    }
}
