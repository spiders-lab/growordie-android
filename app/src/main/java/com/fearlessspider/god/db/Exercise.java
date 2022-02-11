package com.fearlessspider.god.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.type.DateTime;

import java.sql.Date;

@Entity(tableName = "exercises")
public class Exercise {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    @NonNull
    @ColumnInfo(name = "comment")
    private String comment;

    @NonNull
    @ColumnInfo(name = "createdAt")
    private DateTime createdAt;

    public Exercise(@NonNull Integer id, @NonNull String comment) {
        this.id = id;
        this.comment = comment;
    }

    @NonNull
    public Integer getId() {
        return this.id;
    }

    @NonNull
    public String getComment() {
        return this.comment;
    }

    @NonNull
    public DateTime getCreatedAt() {
        return this.createdAt;
    }
}
