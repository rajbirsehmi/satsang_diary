package com.creative.raj.satsangdiary.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.creative.raj.satsangdiary.utils.NumberCodes;
import com.creative.raj.satsangdiary.utils.StringConstants;

public class ModificationNotifierService extends Service {
    public ModificationNotifierService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int result = intent.getIntExtra("result", -1);
        String message = intent.getStringExtra("message");
        Intent intentNotify = null;
        if (result == NumberCodes.NOTIFY_EDIT_SUCCESS) {
            intentNotify = new Intent(StringConstants.INTENT_FILTER_NOTIFY_EDIT_SHABAD_SUCCESS);
        }
        if (result == NumberCodes.NOTIFY_EDIT_FAILED) {
            intentNotify = new Intent(StringConstants.INTENT_FILTER_NOTIFY_EDIT_SHABAD_FAILED);
            intentNotify.putExtra("message", message);
        }
        if (result == NumberCodes.NOTIFY_REMOVE_SUCCESS) {
            intentNotify = new Intent(StringConstants.INTENT_FILTER_NOTIFY_REMOVE_SHABAD_SUCCESS);
        }
        if (result == NumberCodes.NOTIFY_REMOVE_FAILED) {
            intentNotify = new Intent(StringConstants.INTENT_FILTER_NOTIFY_REMOVE_SHABAD_FAILED);
            intentNotify.putExtra("message", message);
        }
        sendBroadcast(intentNotify);
        return START_NOT_STICKY;
    }
}
