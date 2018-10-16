package com.creative.raj.satsangdiary.persistence;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

public class Cache {

    public static int getCurrentSelectedAreaId(@org.jetbrains.annotations.NotNull Context context) {
        return context.getSharedPreferences("my_perf", Context.MODE_PRIVATE).getInt("default_area", -1);
    }

    public static void setCurrentSelectedAreaId(int selectedAreaId, @NotNull Context context) {
        context.getSharedPreferences("my_perf", Context.MODE_PRIVATE).edit().putInt("default_area", selectedAreaId).commit();
    }
}