package com.creative.raj.satsangdiary.parser;

import android.database.Cursor;

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
}
