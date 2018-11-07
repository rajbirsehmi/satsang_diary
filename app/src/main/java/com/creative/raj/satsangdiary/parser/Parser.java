package com.creative.raj.satsangdiary.parser;

import android.database.Cursor;

import com.creative.raj.satsangdiary.dataholders.selectedarea.ExpandedData;
import com.creative.raj.satsangdiary.dataholders.shabad.DataHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static List<DataHolder> parseShabadList(Cursor cursor) {
        List<DataHolder> list = new ArrayList<>();
        DataHolder holder;
        while (cursor.moveToNext()) {
            holder = new DataHolder();
            holder.setShabadId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("shabad_id"))));
            holder.setShabad(cursor.getString(cursor.getColumnIndex("shabad_text")));
            list.add(holder);
        }
        cursor.close();
        return list;
    }

    public static String parseDateTime(long timeInMillis) {
        return new SimpleDateFormat("DDD - dd MMM, yyyy").format(new Date(timeInMillis));
    }

    public static int[] parseOtherAreaIds(Cursor cursor) {
        int[] ids = new int[cursor.getCount()];
        int index = 0;
        while (cursor.moveToNext()) {
            ids[index] = Integer.parseInt(cursor.getString(cursor.getColumnIndex("area_id")));
            index++;
        }
        return ids;
    }

    public static String parseOtherAreaName(Cursor cursorOtherAreaName) {
        if (!cursorOtherAreaName.moveToNext()) {
            return null;
        }
        return cursorOtherAreaName.getString(cursorOtherAreaName.getColumnIndex("area_name"));
    }

    public static int[] parseOtherCenterIds(Cursor cursorOtherCenterIds) {
        int[] ids = new int[cursorOtherCenterIds.getCount()];
        int index = 0;
        while (cursorOtherCenterIds.moveToNext()) {
            ids[index] = Integer.parseInt(cursorOtherCenterIds.getString(cursorOtherCenterIds.getColumnIndex("center_id")));
            index++;
        }
        return ids;
    }

    public static ArrayList<com.creative.raj.satsangdiary.dataholders.otherarea.ExpandedData> parseShabadDoneinOtherCenters(Cursor cursorShabadDoneInOtherCenters) {
        ArrayList<com.creative.raj.satsangdiary.dataholders.otherarea.ExpandedData> list = new ArrayList<>();
        while (cursorShabadDoneInOtherCenters.moveToNext()) {
            com.creative.raj.satsangdiary.dataholders.otherarea.ExpandedData expandedData = new com.creative.raj.satsangdiary.dataholders.otherarea.ExpandedData();
            expandedData.setRelationId(Integer.parseInt(cursorShabadDoneInOtherCenters.getString(cursorShabadDoneInOtherCenters.getColumnIndex("relation_id"))));
            expandedData.setDatetime(Long.parseLong(cursorShabadDoneInOtherCenters.getString(cursorShabadDoneInOtherCenters.getColumnIndex("date_time"))));
            expandedData.setCenterName(cursorShabadDoneInOtherCenters.getString(cursorShabadDoneInOtherCenters.getColumnIndex("center_name")));
            expandedData.setRemarks(cursorShabadDoneInOtherCenters.getString(cursorShabadDoneInOtherCenters.getColumnIndex("remarks")));
            expandedData.setShabad(cursorShabadDoneInOtherCenters.getString(cursorShabadDoneInOtherCenters.getColumnIndex("shabad")));
            list.add(expandedData);
        }
        return list;
    }
}
