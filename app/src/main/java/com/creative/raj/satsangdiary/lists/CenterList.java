package com.creative.raj.satsangdiary.lists;

import com.creative.raj.satsangdiary.dataholders.populators.Center;

import java.util.ArrayList;

public class CenterList {

    private static ArrayList<Center> listCenter;

    public static ArrayList<Center> getInstance() {
        createInstance();
        return listCenter;
    }

    public static void createInstance() {
        if (listCenter == null) {
            listCenter = new ArrayList<>();
        }
    }


    public static void addCenterItemToList(Center center) {
        listCenter.add(center);
    }

    public static Center getCenterItemFromList(int position) {
        return listCenter.get(position);
    }

    public static void removeCenterItemFromList(int position) {
        listCenter.remove(position);
    }

    public static void removeCenterItemFromList(Center center) {
        listCenter.remove(center);
    }

    public static void removeAllCenters() {
        listCenter.clear();
    }

    public static int getListLength() {
        return listCenter.size();
    }
}