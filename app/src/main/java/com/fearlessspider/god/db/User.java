package com.fearlessspider.god.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * A basic class representing an entity that is a row in a one-column database table.
 *
 * @ Entity - You must annotate the class as an entity and supply a table name if not class name.
 * @ PrimaryKey - You must identify the primary key.
 * @ ColumnInfo - You must supply the column name if it is different from the variable name.
 *
 * See the documentation for the full rich set of annotations.
 * https://developer.android.com/topic/libraries/architecture/room.html
 */

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "steps_goal", defaultValue = "10000")
    private Integer stepsGoal;

    public User(@NonNull String username) {
        this.username = username;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public Integer getId() {
        return this.id;
    }

    @NonNull
    public String getUsername() {
        return this.username;
    }

    @NonNull
    public Integer getStepsGoal() {
        return stepsGoal;
    }

    @NonNull
    public void setStepsGoal(Integer stepsGoal) {
        this.stepsGoal = stepsGoal;
    }
}