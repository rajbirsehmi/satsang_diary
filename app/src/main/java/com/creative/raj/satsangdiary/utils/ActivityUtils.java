package com.creative.raj.satsangdiary.utils;

import android.content.Context;
import android.content.Intent;

public class ActivityUtils {

    public static final String ACTION_URL_SETTING_ACTIVITY = "com.creative.raj.satsangdiary.settingsactivity";

    public static void startActivity(Context context, Intent intentActivity) {
        intentActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intentActivity);
    }
}
