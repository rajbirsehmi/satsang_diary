package com.creative.raj.satsangdiary.model;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Debug;

import com.creative.raj.satsangdiary.adapter.AllShabadAdapter;
import com.creative.raj.satsangdiary.lists.ShabadList;
import com.creative.raj.satsangdiary.presenter.AllShabad;
import com.creative.raj.satsangdiary.roomdatabase.database.DiaryDatabase;
import com.creative.raj.satsangdiary.roomdatabase.database.QueryOrganiser;

public class AllShabadImpl {
    private AllShabad allShabad;
    private Context context;

    public AllShabadImpl(AllShabad allShabad, Context context) {
        this.allShabad = allShabad;
        this.context = context;
    }

    public void loadAllShabad() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                if (Debug.isDebuggerConnected())
                    Debug.waitForDebugger();
                QueryOrganiser.getAllShabads(DiaryDatabase.getInstance());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (ShabadList.getListLength() == 0) {
                    allShabad.notifyNoShabadFound("No Shabad Found...");
                    return;
                }
                allShabad.attachAdapterToView(new AllShabadAdapter(ShabadList.getInstance()));
            }
        }.execute();
    }
}
