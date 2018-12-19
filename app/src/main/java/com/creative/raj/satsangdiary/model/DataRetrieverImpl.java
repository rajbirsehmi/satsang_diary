package com.creative.raj.satsangdiary.model;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Debug;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.adapter.AutoCompleteAllAreaAdapter;
import com.creative.raj.satsangdiary.adapter.AutoCompleteAssociatedCenterAdapter;
import com.creative.raj.satsangdiary.adapter.AutoCompleteShabadAdapter;
import com.creative.raj.satsangdiary.lists.AreaList;
import com.creative.raj.satsangdiary.lists.CenterList;
import com.creative.raj.satsangdiary.lists.ShabadList;
import com.creative.raj.satsangdiary.presenter.DataRetriever;
import com.creative.raj.satsangdiary.roomdatabase.database.DiaryDatabase;
import com.creative.raj.satsangdiary.roomdatabase.database.RoomQueryManager;

public class DataRetrieverImpl {

    private Context context;
    private DataRetriever dataRetriever;

    public DataRetrieverImpl(Context context, DataRetriever dataRetriever) {
        this.context = context;
        this.dataRetriever = dataRetriever;
    }

    public void getAllAreas() {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                if(android.os.Debug.isDebuggerConnected())
                    android.os.Debug.waitForDebugger();
                RoomQueryManager.getAllAreas(DiaryDatabase.getInstance());
                return true;
            }

            @Override
            protected void onPostExecute(Boolean flag) {
                super.onPostExecute(flag);
                dataRetriever.setAreaAdapter(new AutoCompleteAllAreaAdapter(context, R.layout.template_autocomplete_list_item, AreaList.getInstance()));
            }
        }.execute();
    }

    public void getAllAssociatedCenters(int areaId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                if(android.os.Debug.isDebuggerConnected())
                    android.os.Debug.waitForDebugger();
                RoomQueryManager.getAllAssociatedCenters(DiaryDatabase.getInstance(), areaId);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dataRetriever.setCenterAdapter(new AutoCompleteAssociatedCenterAdapter(context, R.layout.template_autocomplete_list_item, CenterList.getInstance()));
            }
        }.execute();
    }

    public void getAllShabads() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                if(android.os.Debug.isDebuggerConnected())
                    android.os.Debug.waitForDebugger();
                RoomQueryManager.getAllShabads(DiaryDatabase.getInstance());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dataRetriever.setShabadAdapter(new AutoCompleteShabadAdapter(context, R.layout.template_autocomplete_list_item, ShabadList.getInstance()));
            }
        }.execute();
    }
}
