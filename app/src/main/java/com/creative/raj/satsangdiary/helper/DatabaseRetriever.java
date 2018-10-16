package com.creative.raj.satsangdiary.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseRetriever {

    private static SQLiteDatabase database;
    private static DatabaseHelper databaseHelper;

    public static SQLiteDatabase getDatabaseInstance(Context context) {
        if (database == null) {
            databaseHelper = new DatabaseHelper(context, "my_db", null, 1);
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }
}
