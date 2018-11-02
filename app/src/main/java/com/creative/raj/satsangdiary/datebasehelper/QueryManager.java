package com.creative.raj.satsangdiary.datebasehelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QueryManager {

    // this will return the Center IDs from the specified area ID.
    public static Cursor getCenterIds(Context context, int areaId) {
        SQLiteDatabase database = new DatabaseRetriever(context).getDatabase();
        Cursor cursor = database.rawQuery(
                "SELECT center.center_id" +
                        "  FROM area_center_relation" +
                        "       INNER JOIN" +
                        "       center ON area_center_relation.center_id = center.center_id" +
                        "       INNER JOIN" +
                        "       area ON area_center_relation.area_id = area.area_id" +
                        " WHERE area.area_id = " + areaId, null);
        return cursor;
    }

    public static Cursor getCenterName(Context context, int centerId) {
        SQLiteDatabase database = new DatabaseRetriever(context).getDatabase();
        Cursor cursor = database.rawQuery(
                "SELECT center_name" +
                        " FROM center" +
                        " WHERE center_id = " + centerId, null);
        return cursor;
    }

    public static Cursor getShabadDoneInCenter(Context context, int centerId) {
        SQLiteDatabase database = new DatabaseRetriever(context).getDatabase();
        Cursor cursor = database.rawQuery("SELECT " +
                "       center_shabad_relation.relation_id," +
                "       shabad_text," +
                "       remarks_text," +
                "       date_time" +
                "  FROM area" +
                "       INNER JOIN" +
                "       area_center_relation ON area_center_relation.area_id = area.area_id" +
                "       INNER JOIN" +
                "       center ON center.center_id = area_center_relation.center_id" +
                "       INNER JOIN" +
                "       center_shabad_relation ON center_shabad_relation.center_id = center.center_id" +
                "       INNER JOIN" +
                "       shabad ON shabad.shabad_id = center_shabad_relation.shabad_id" +
                "       INNER JOIN" +
                "       shabad_remarks ON shabad_remarks.remarks_id = center_shabad_relation.remarks_id" +
                " WHERE center.center_id = " + centerId, null);
        return cursor;
    }

    public static Cursor getOtherAreaIds(Context context, int currentAreaId) {
        SQLiteDatabase database = new DatabaseRetriever(context).getDatabase();
        Cursor cursor = database.rawQuery(
                "SELECT center.center_id" +
                        "  FROM area_center_relation" +
                        "       INNER JOIN" +
                        "       center ON area_center_relation.center_id = center.center_id" +
                        "       INNER JOIN" +
                        "       area ON area_center_relation.area_id = area.area_id" +
                        " WHERE area.area_id != " + currentAreaId, null);
        return cursor;
    }

    public static Cursor getAllShabads(Context context) {
        SQLiteDatabase database = new DatabaseRetriever(context).getDatabase();
        Cursor cursor = database.rawQuery("SELECT " +
                "       shabad_id, shabad_text " +
                "  FROM shabad", null);
        return cursor;
    }
}