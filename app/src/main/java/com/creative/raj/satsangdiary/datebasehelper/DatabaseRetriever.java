package com.creative.raj.satsangdiary.datebasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseRetriever {

    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    public DatabaseRetriever(Context context) {
        databaseHelper = new DatabaseHelper(context, "my_db", null, 1);
        database = databaseHelper.getWritableDatabase();
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }
}
