package com.creative.raj.satsangdiary.datebasehelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QueryManager {

    // this will return the Center ID and Center Name from the specified area ID.
    public static Cursor getCenters(Context context, int areaId) {
        SQLiteDatabase database = new DatabaseRetriever(context).getDatabase();
        Cursor cursor = database.rawQuery("SELECT center.center_id," +
                "       center.center_name" +
                "  FROM area_center_relation" +
                "       INNER JOIN" +
                "       center ON area_center_relation.center_id = center.center_id" +
                "       INNER JOIN" +
                "       area ON area_center_relation.area_id = area.area_id" +
                " WHERE area.area_id = " + areaId, null);
        database.close();
        return cursor;
    }
}