package com.creative.raj.satsangdiary.lists;

import com.creative.raj.satsangdiary.dataholders.populators.Center;

import java.util.ArrayList;

public class ExtendedCenterList {
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


    public static void addCenterToList(Center center) {
        listCenter.add(center);
    }

    public static Center getCenterFromList(int position) {
        return listCenter.get(position);
    }

    public static void removeCenterFromList(int position) {
        listCenter.remove(position);
    }

    public static void removeCenterFromList(Center center) {
        listCenter.remove(center);
    }

    public static void removeAllCenters() {
        listCenter.clear();
    }

    public static int getListLength() {
        return listCenter.size();
    }
}
