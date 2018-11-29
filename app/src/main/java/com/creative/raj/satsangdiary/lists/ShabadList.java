package com.creative.raj.satsangdiary.lists;

import com.creative.raj.satsangdiary.dataholders.populators.Shabad;

import java.util.ArrayList;

public class ShabadList {
    private static ArrayList<Shabad> list;

    public static ArrayList<Shabad> getInstance() {
        createInstance();
        return list;
    }

    public static void createInstance() {
        if (list == null) {
            list = new ArrayList<>();
        }
    }

    public static void addShabadItemToList(Shabad shabad) {
        list.add(shabad);
    }

    public static Shabad getShabadItemFromList(int index) {
        return list.get(index);
    }

    public static void removeShabadItemFromList(int index) {
        list.remove(index);
    }

    public static void removeShabadItemFromList(Shabad shabad) {
        list.remove(shabad);
    }

    public static void removeAllShabad() {
        list.clear();
    }

    public static int getListLength() {
        return list.size();
    }
}
