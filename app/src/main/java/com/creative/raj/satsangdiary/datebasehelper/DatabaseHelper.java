package com.creative.raj.satsangdiary.datebasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating tables
        db.execSQL("create table if not exists area(id integer PRIMARY KEY AUTOINCREMENT, area_name varchar(20) UNIQUE)");
        db.execSQL("create table if not exists center(id integer PRIMARY KEY AUTOINCREMENT, center_name varchar(25) UNIQUE)");
        db.execSQL("CREATE TABLE if not exists shabad (shabad_id INTEGER PRIMARY KEY AUTOINCREMENT, shabad_text VARCHAR (100) UNIQUE)");
        db.execSQL("CREATE TABLE if not exists shabad_remarks (remarks_id INTEGER PRIMARY KEY AUTOINCREMENT, remarks_text VARCHAR (20))");

        // creating relation tables
        db.execSQL("create table if not exists area_center_relation(area_id INTEGER REFERENCES area (id) ON DELETE CASCADE, center_id INTEGER REFERENCES center (id) ON DELETE CASCADE)");
        db.execSQL("CREATE TABLE if not exists center_shabad_relation (relation_id INTEGER PRIMARY KEY AUTOINCREMENT, center_id   INTEGER REFERENCES center (center_id) ON DELETE CASCADE, shabad_id   INTEGER REFERENCES shabad (shabad_id) ON DELETE CASCADE, remarks_id  INTEGER REFERENCES shabad_remarks (remarks_id) ON DELETE CASCADE, date_time varchar(17))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // null and void
    }
}
