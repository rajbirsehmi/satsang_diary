package com.creative.raj.satsangdiary.parser;

import android.database.Cursor;

import com.creative.raj.satsangdiary.dataholders.selectedarea.ExpandedData;
import com.creative.raj.satsangdiary.datebasehelper.QueryManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Parser {
    public static String[][] parseCenterIdAndName(Cursor cursor) {
        String[][] arrCenterIdAndName = new String[cursor.getCount()][cursor.getColumnCount()];
        int rowIndex = 0;
        while (cursor.moveToNext()) {
            String centerId = String.valueOf(cursor.getInt(cursor.getColumnIndex("center_id")));
            String centerName = cursor.getString(cursor.getColumnIndex("center_name"));
            arrCenterIdAndName[rowIndex][0] = centerId;
            arrCenterIdAndName[rowIndex][1] = centerName;
            rowIndex++;
        }
        cursor.close();
        return arrCenterIdAndName;
    }

    public static String[] parseCenterIds(Cursor cursor) {
        String[] arrCenterId = new String[cursor.getCount()];
        int rowIndex = 0;
        while (cursor.moveToNext()) {
            String centerId = String.valueOf(cursor.getInt(cursor.getColumnIndex("center_id")));
            String centerName = cursor.getString(cursor.getColumnIndex("center_name"));
            arrCenterId[rowIndex] = centerId;
            rowIndex++;
        }
        cursor.close();
        return arrCenterId;
    }

    public static String parseCenterName(Cursor cursorCenterName, int _centerId) {
        if (!cursorCenterName.moveToNext()) {
            return null;
        }
        String centerName = cursorCenterName.getString(cursorCenterName.getColumnIndex("center_name"));
        cursorCenterName.close();
        return centerName;
    }

    public static ArrayList<ExpandedData> parseShabadAndCenterData(Cursor cursor) {
        ArrayList<ExpandedData> arrTemp = new ArrayList<>();
        ExpandedData expandedData;
        while (cursor.moveToNext()) {
            expandedData = new ExpandedData();
            int relationId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("relation_id")));
            String shabad = cursor.getString(cursor.getColumnIndex("shabad_text"));
            String remarks = cursor.getString(cursor.getColumnIndex("remarks_text"));
            long dateTime = Long.parseLong(cursor.getString(cursor.getColumnIndex("date_time")));
            expandedData.setDateTime(dateTime);
            expandedData.setRelationId(relationId);
            expandedData.setRemarks(remarks);
            expandedData.setShabad(shabad);
            arrTemp.add(expandedData);
        }
        cursor.close();
        return arrTemp;
    }

    public static String parseDateTime(long timeInMillis) {
        return new SimpleDateFormat("DDD - dd MMM, yyyy").format(new Date(timeInMillis));
    }
}
