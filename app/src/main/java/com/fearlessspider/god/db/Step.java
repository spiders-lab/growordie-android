package com.fearlessspider.god.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fearlessspider.god.utils.DateUtil;

import java.util.Date;

@Entity(tableName = "steps")
public class Step {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;

    @NonNull
    @ColumnInfo(name = "steps")
    private Integer steps;

    @NonNull
    @ColumnInfo(name = "createdAt")
    private Date createdAt;

    public Step(@NonNull Integer steps) {
        this.steps = steps;
        this.createdAt = new Date(DateUtil.getToday());
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public void setSteps(@NonNull Integer steps) {
        this.steps = steps;
    }

    @NonNull
    public Integer getId() {
        return this.id;
    }

    @NonNull
    public Integer getSteps() {
        return this.steps;
    }

    @NonNull
    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}