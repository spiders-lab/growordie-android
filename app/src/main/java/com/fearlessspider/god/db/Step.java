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
    @ColumnInfo(name = "startSteps")
    private Integer startSteps;

    @NonNull
    @ColumnInfo(name = "endSteps")
    private Integer endSteps;

    @NonNull
    @ColumnInfo(name = "createdAt")
    private Date createdAt;

    public Step(@NonNull Integer startSteps, @NonNull Integer endSteps) {
        this.startSteps = startSteps;
        this.endSteps = endSteps;
        this.createdAt = new Date(DateUtil.getToday());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NonNull
    public Integer getStartSteps() {
        return startSteps;
    }

    public void setStartSteps(@NonNull Integer startSteps) {
        this.startSteps = startSteps;
    }

    @NonNull
    public Integer getEndSteps() {
        return endSteps;
    }

    public void setEndSteps(@NonNull Integer endSteps) {
        this.endSteps = endSteps;
    }

    @NonNull
    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getSteps() {
        return this.endSteps - this.startSteps;
    }

}