package com.creative.raj.satsangdiary.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class UpdateViewService extends Service {
    public UpdateViewService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("update_service", "onStartCommand: HEllo world");
        updateSelectedArea();
        updateOtherArea();
        updateShabadList();
        return START_NOT_STICKY;
    }

    private void updateSelectedArea() {
        Intent intentUpdateSelectedAreaViews = new Intent("update_selected_area");
        sendBroadcast(intentUpdateSelectedAreaViews);
    }

    private void updateOtherArea() {
        Intent intentUpdateOtherAreaViews = new Intent("update_other_area");
        sendBroadcast(intentUpdateOtherAreaViews);
    }

    private void updateShabadList() {
        Intent intentUpdateShabadList = new Intent("update_shabad_list");
        sendBroadcast(intentUpdateShabadList);
    }
}
