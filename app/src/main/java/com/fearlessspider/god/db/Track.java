package com.fearlessspider.god.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tracks")
public class Track {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    public Track(@NonNull Integer id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    @NonNull
    public Integer getId() {
        return this.id;
    }

    @NonNull
    public String getName() {
        return this.name;
    }
}
