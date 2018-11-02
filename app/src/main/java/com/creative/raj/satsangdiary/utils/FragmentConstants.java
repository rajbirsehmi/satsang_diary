package com.creative.raj.satsangdiary.utils;

public class FragmentConstants {

    public static final int SHABAD_LIST = -1;
    public static final int SELECTED_AREA_ID = 0;
    public static final int OTHER_AREA_ID = 1;

    private static int currentId;

    public static void setCurrentFragmentId(int currentFragmentId) {
        currentId = currentFragmentId;
    }

    public static int getCurrentFragmentId() {
        return currentId;
    }
}
