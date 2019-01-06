package com.creative.raj.satsangdiary.model;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Debug;

import com.creative.raj.satsangdiary.adapter.OtherAreaAdapter;
import com.creative.raj.satsangdiary.dataholders.otherarea.DataHolder;
import com.creative.raj.satsangdiary.dataholders.otherarea.ExpandedData;
import com.creative.raj.satsangdiary.dataholders.populators.Area;
import com.creative.raj.satsangdiary.lists.OtherAreaList;
import com.creative.raj.satsangdiary.persistence.Cache;
import com.creative.raj.satsangdiary.presenter.OtherArea;
import com.creative.raj.satsangdiary.roomdatabase.database.DiaryDatabase;
import com.creative.raj.satsangdiary.roomdatabase.database.RoomQueryManager;
import com.creative.raj.satsangdiary.roomdatabase.entities.ShabadDoneInCenter;

import java.util.ArrayList;
import java.util.List;

public class OtherAreaImpl {
    private OtherArea otherArea;
    private Context context;

    public OtherAreaImpl(Context context, OtherArea otherArea) {
        this.otherArea = otherArea;
        this.context = context;
    }

    public void loadOtherAreas() {
        new AsyncTask<Void, Void, List<DataHolder>>() {
            @Override
            protected List<DataHolder> doInBackground(Void... voids) {
                if (Debug.isDebuggerConnected())
                    Debug.waitForDebugger();
                RoomQueryManager.getOtherAreas(DiaryDatabase.getInstance(), Cache.getCurrentSelectedAreaId(context));

                List<DataHolder> dataHolders = new ArrayList<>();

                for (Area area : OtherAreaList.getInstance()) {
                    DataHolder holder = new DataHolder();
                    String areaName = area.getName();
                    int areaId = area.getId();

                    holder.setAreaId(areaId);
                    holder.setAreaName(areaName);
                    ArrayList<ExpandedData> expandedData = new ArrayList<>();

                    for (ShabadDoneInCenter shabadDoneInCenter : RoomQueryManager.getShabadDoneInOtherCenter(DiaryDatabase.getInstance(), areaId)) {
                        ExpandedData data = new ExpandedData();
                        data.setCenterName(shabadDoneInCenter.getCenter().getCenterName());
                        data.setDatetime(shabadDoneInCenter.getCentralRelation().getDateTime());
                        data.setRelationId(shabadDoneInCenter.getCentralRelation().getRelationId());
                        data.setRemarks(shabadDoneInCenter.getRemarks().getRemarksText());
                        data.setShabad(shabadDoneInCenter.getShabad().getShabadText());
                        expandedData.add(data);
                    }
                    holder.setExpandedData(expandedData);
                    dataHolders.add(holder);
                }
                return dataHolders;
            }

            @Override
            protected void onPostExecute(List<DataHolder> dataHolders) {
                super.onPostExecute(dataHolders);
                otherArea.attachAdapterToList(new OtherAreaAdapter(dataHolders));
            }
        }.execute();
    }
}
