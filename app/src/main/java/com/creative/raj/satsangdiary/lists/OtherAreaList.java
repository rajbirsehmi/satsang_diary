package com.creative.raj.satsangdiary.lists;

import com.creative.raj.satsangdiary.dataholders.populators.Area;

import java.util.ArrayList;

public class OtherAreaList {
    private static ArrayList<Area> listOtherArea;

    public static ArrayList<Area> getInstance() {
        createInstance();
        return listOtherArea;
    }

    public static void createInstance() {
        if (listOtherArea == null) {
            listOtherArea = new ArrayList<>();
        }
    }

    public static void addOtherAreaToList(Area area) {
        listOtherArea.add(area);
    }

    public static Area getOtherAreaFromList(int position) {
        return listOtherArea.get(position);
    }

    public static void removeOtherAreaFromList(int position) {
        listOtherArea.remove(position);
    }

    public static void removeOtherAreaFromList(Area areaitem) {
        listOtherArea.remove(areaitem);
    }

    public static int getListLength() {
        return listOtherArea.size();
    }

    public static void removeAllAreas() {
        listOtherArea.clear();
    }
}
