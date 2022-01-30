package com.fearlessspider.god.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "god";
    private static final String USERS_TABLE_NAME = "users";
    private static final String JOURNEYS_TABLE_NAME = "journeys";
    private static final String TRACKS_TABLE_NAME = "tracks";
    private static final String EXERCISE_TABLE_NAME = "exercises";

    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + USERS_TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, created_at DATETIME DEFAULT CURRENT_TIMESTAMP)");
        sqLiteDatabase.execSQL("CREATE TABLE " + JOURNEYS_TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, created_at DATETIME DEFAULT CURRENT_TIMESTAMP, user_id INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TRACKS_TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, created_at DATETIME DEFAULT CURRENT_TIMESTAMP, journey_id INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE " + EXERCISE_TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, comment TEXT, created_at DATETIME DEFAULT CURRENT_TIMESTAMP, track_id INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + JOURNEYS_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TRACKS_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EXERCISE_TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}
