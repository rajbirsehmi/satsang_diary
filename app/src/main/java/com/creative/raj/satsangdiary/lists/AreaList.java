package com.creative.raj.satsangdiary.lists;

import com.creative.raj.satsangdiary.dataholders.populators.Area;

import java.util.ArrayList;

public class AreaList {

    private static ArrayList<Area> listArea;

    public static ArrayList<Area> getInstance() {
        createInstance();
        return listArea;
    }

    public static void createInstance() {
        if (listArea == null) {
            listArea = new ArrayList<>();
        }
    }

    public static void addAreaToList(Area area) {
        listArea.add(area);
    }

    public static Area getAreaFromList(int position) {
        return listArea.get(position);
    }

    public static void removeAreaFromList(int position) {
        listArea.remove(position);
    }

    public static void removeAreaFromList(Area areaitem) {
        listArea.remove(areaitem);
    }

    public static int getListLength() {
        return listArea.size();
    }

    public static void removeAllAreas() {
        listArea.clear();
    }
}