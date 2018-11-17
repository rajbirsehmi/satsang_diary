package com.creative.raj.satsangdiary.datebasehelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.creative.raj.satsangdiary.persistence.Cache;

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
                "       area_center_" +
                "relation ON area_center_relation.area_id = area.area_id" +
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

    public static Cursor getOtherAreaIds(Context context) {
        int currentAreaId = Cache.getCurrentSelectedAreaId(context);
        SQLiteDatabase database = new DatabaseRetriever(context).getDatabase();
        Cursor cursor = database.rawQuery("SELECT area_id FROM area WHERE area.area_id != " + currentAreaId, null);
        return cursor;
    }

    public static Cursor getAllShabads(Context context) {
        SQLiteDatabase database = new DatabaseRetriever(context).getDatabase();
        Cursor cursor = database.rawQuery("SELECT " +
                "       shabad_id, shabad_text " +
                "  FROM shabad", null);
        return cursor;
    }

    public static Cursor getOtherCenterIds(Context context, int areaId) {
        SQLiteDatabase database = new DatabaseRetriever(context).getDatabase();
        Cursor cursor = database.rawQuery("SELECT center.center_id" +
                "  FROM area_center_relation" +
                "       INNER JOIN" +
                "       center ON center.center_id = area_center_relation.center_id" +
                " WHERE area_center_relation.area_id = " + areaId, null);
        return cursor;
    }

    public static Cursor getOtherAreaName(Context context, int otherAreaId) {
        SQLiteDatabase database = new DatabaseRetriever(context).getDatabase();
        Cursor cursor = database.rawQuery("SELECT area_name FROM area WHERE area_id = " + otherAreaId, null);
        return cursor;
    }

    public static Cursor getShabadDoneInOtherAreas(Context context, int currentAreaId) {
        SQLiteDatabase database = new DatabaseRetriever(context).getDatabase();
        Cursor cursor = database.rawQuery("SELECT center.center_id," +
                "       center.center_name," +
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
                " WHERE area.area_id = " + currentAreaId, null);
        return cursor;
    }

    public static Cursor getAllAreas(Context context) {
        SQLiteDatabase database = new DatabaseRetriever(context).getDatabase();
        Cursor cursor = database.rawQuery("SELECT *" +
                "  FROM area" +
                " ORDER BY area_name ASC", null);
        return cursor;
    }

    public static Cursor getCenters(Context context, int areaId) {
        SQLiteDatabase database = new DatabaseRetriever(context).getDatabase();
        Cursor cursor = database.rawQuery("SELECT center.center_id," +
                "       center_name" +
                "  FROM area_center_relation" +
                "       INNER JOIN" +
                "       center ON area_center_relation.center_id = center.center_id" +
                "       INNER JOIN" +
                "       area ON area_center_relation.area_id = area.area_id" +
                " WHERE area.area_id = " + areaId, null);
        return cursor;
    }

    public static void addNewArea(Context context, String areaName) {
        SQLiteDatabase database = new DatabaseRetriever(context).getDatabase();
        database.rawQuery("INSERT INTO area (area_name) values ('" + areaName + "');", null);
    }
}