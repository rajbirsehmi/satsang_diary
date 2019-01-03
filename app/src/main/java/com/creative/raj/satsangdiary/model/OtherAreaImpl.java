package com.creative.raj.satsangdiary.model;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Debug;

import com.creative.raj.satsangdiary.adapter.OtherAreaAdapter;
import com.creative.raj.satsangdiary.dataholders.otherarea.DataHolder;
import com.creative.raj.satsangdiary.dataholders.otherarea.ExpandedData;
import com.creative.raj.satsangdiary.dataholders.populators.Area;
import com.creative.raj.satsangdiary.datebasehelper.QueryManager;
import com.creative.raj.satsangdiary.lists.OtherAreaList;
import com.creative.raj.satsangdiary.parser.Parser;
import com.creative.raj.satsangdiary.persistence.Cache;
import com.creative.raj.satsangdiary.presenter.OtherArea;
import com.creative.raj.satsangdiary.roomdatabase.database.DiaryDatabase;
import com.creative.raj.satsangdiary.roomdatabase.database.RoomQueryManager;

import java.util.ArrayList;
import java.util.List;

public class OtherAreaImpl {
    private Context context;
    private OtherArea otherArea;

    public OtherAreaImpl(Context context, OtherArea otherArea) {
        this.context = context;
        this.otherArea = otherArea;
    }

    public void loadOtherAreas() {
        new AsyncTask<Void, Void, DataHolder>() {
            @Override
            protected DataHolder doInBackground(Void... voids) {
                if (Debug.isDebuggerConnected())
                    Debug.waitForDebugger();
                RoomQueryManager.getOtherAreas(DiaryDatabase.getInstance(), Cache.getCurrentSelectedAreaId(context));
                List<DataHolder> dataHolders = new ArrayList<>();
                DataHolder holder;
                for (Area area : OtherAreaList.getInstance()) {
                    String areaName = area.getName();
                    int areaId = area.getId();

                    holder = new DataHolder();
                    holder.setAreaId(areaId);
                    holder.setAreaName(areaName);
                    RoomQueryManager.getAllAssociatedCenters();
                }
                return null;
            }

            @Override
            protected void onPostExecute(DataHolder dataHolder) {
                super.onPostExecute(dataHolder);
            }
        }.execute();

        Cursor cursorOtherAreaIds = QueryManager.getOtherAreaIds(context);
        int[] otherAreaIds = Parser.parseOtherAreaIds(cursorOtherAreaIds);
        List<DataHolder> listDataHolders = new ArrayList<>();
        DataHolder dataHolder;
        for (int otherAreaId : otherAreaIds) {
            dataHolder = new DataHolder();

            Cursor cursorOtherAreaName = QueryManager.getOtherAreaName(context, otherAreaId);
            String otherAreaName = Parser.parseOtherAreaName(cursorOtherAreaName);

            Cursor cursorShabadDoneInOtherAreas = QueryManager.getShabadDoneInOtherAreas(context, otherAreaId);
            ArrayList<ExpandedData> expandedData = Parser.parseShabadDoneinOtherCenters(cursorShabadDoneInOtherAreas);

            dataHolder.setAreaId(otherAreaId);
            dataHolder.setAreaName(otherAreaName);
            dataHolder.setExpandedData(expandedData);
            listDataHolders.add(dataHolder);
        }
        otherArea.attachAdapterToList(new OtherAreaAdapter(listDataHolders));
    }
}
