package com.creative.raj.satsangdiary.model;

import android.content.Context;
import android.os.AsyncTask;

import com.creative.raj.satsangdiary.persistence.Cache;
import com.creative.raj.satsangdiary.presenter.RemarksInjection;
import com.creative.raj.satsangdiary.roomdatabase.database.DiaryDatabase;
import com.creative.raj.satsangdiary.roomdatabase.database.RoomQueryManager;

public class RemarksInjectionImpl {

    private Context context;
    private RemarksInjection remarksInjection;

    public RemarksInjectionImpl(Context context, RemarksInjection remarksInjection) {
        this.context = context;
        this.remarksInjection = remarksInjection;
    }

    public void injectRemarks() {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                if (Cache.getRemarksInjectionStatus(context)) {
                    return 1;
                }
                RoomQueryManager.injectRemarks(DiaryDatabase.getInstance());
                return 0;
            }

            @Override
            protected void onPostExecute(Integer status) {
                super.onPostExecute(status);
                if (status == 0)
                    Cache.setRemarksInjectionStatus(context, true);
                remarksInjection.callbackInjectionSuccess();
            }
        }.execute();
    }
}