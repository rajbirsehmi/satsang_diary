package com.creative.raj.satsangdiary.model;

import android.content.Context;
import android.os.AsyncTask;

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
//        Cursor cursorAllArea = QueryManager.getAllAreas(context);
//        Parser.parseAllAreas(cursorAllArea);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                RoomQueryManager.getAllAreas(DiaryDatabase.getInstance(context));
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dataRetriever.setAreaAdapter(new AutoCompleteAllAreaAdapter(context, R.layout.template_autocomplete_list_item, AreaList.getInstance()));
            }
        }.execute();
    }

    public void getAllAssociatedCenters(int areaId) {
//        Cursor cursorCenters = QueryManager.getCenters(context, areaId);
//        Parser.parseAssociatedCenters(cursorCenters);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                RoomQueryManager.getAllAssociatedCenters(DiaryDatabase.getInstance(context), areaId);
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
//        Cursor cursorAllShabads = QueryManager.getAllShabads(context);
//        List<Shabad> listShabad = Parser.parseAllShabads(cursorAllShabads);
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                RoomQueryManager.getAllShabads(DiaryDatabase.getInstance(context));
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
