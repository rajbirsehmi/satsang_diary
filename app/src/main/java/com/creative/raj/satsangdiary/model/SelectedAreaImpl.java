package com.creative.raj.satsangdiary.model;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Debug;

import com.creative.raj.satsangdiary.adapter.SelectedAreaAdapter;
import com.creative.raj.satsangdiary.dataholders.populators.Center;
import com.creative.raj.satsangdiary.dataholders.selectedarea.DataHolder;
import com.creative.raj.satsangdiary.dataholders.selectedarea.ExpandedData;
import com.creative.raj.satsangdiary.lists.CenterList;
import com.creative.raj.satsangdiary.persistence.Cache;
import com.creative.raj.satsangdiary.presenter.SelectedArea;
import com.creative.raj.satsangdiary.roomdatabase.database.DiaryDatabase;
import com.creative.raj.satsangdiary.roomdatabase.database.QueryOrganiser;
import com.creative.raj.satsangdiary.roomdatabase.entities.Area;
import com.creative.raj.satsangdiary.roomdatabase.entities.ShabadDoneInCenter;

import java.util.ArrayList;
import java.util.List;

public class SelectedAreaImpl {

    private SelectedArea selectedArea;
    private Context context;

    public SelectedAreaImpl(Context context, SelectedArea selectedArea) {
        this.selectedArea = selectedArea;
        this.context = context;
    }

    public void loadSelectedAreaData() {
        int currentSelectedAreaId = Cache.getCurrentSelectedAreaId(context);
        if (currentSelectedAreaId == -1) {
            selectedArea.notifyNoSelectedAreaDefined("No Selected Area Defined...");
            return;
        }
        new AsyncTask<Void, Void, List<DataHolder>>() {
            @Override
            protected List<DataHolder> doInBackground(Void... voids) {
                if (Debug.isDebuggerConnected())
                    Debug.waitForDebugger();
                Area selectedArea = QueryOrganiser.getSelectedArea(DiaryDatabase.getInstance(), Cache.getCurrentSelectedAreaId(context));
                int areaId = selectedArea.getAreaId();
                String areaName = selectedArea.getAreaName();

                QueryOrganiser.getAllAssociatedCenters(DiaryDatabase.getInstance(), areaId);

                List<DataHolder> dataHolders = new ArrayList<>();

                for (Center center : CenterList.getInstance()) {
                    DataHolder holder = new DataHolder();
                    ArrayList<ExpandedData> expandedData = new ArrayList<>();
                    holder.setCenterId(center.getId());
                    holder.setCenterName(center.getName());

                    List<ShabadDoneInCenter> shabadDoneInCenter = QueryOrganiser.getShabadDoneInCenter(DiaryDatabase.getInstance(), holder.getCenterId());
                    for (ShabadDoneInCenter _shabadDoneInCenter : shabadDoneInCenter) {
                        ExpandedData data = new ExpandedData();
                        data.setRelationId(_shabadDoneInCenter.getCentralRelation().getRelationId());
                        data.setShabad(_shabadDoneInCenter.getShabad().getShabadText());
                        data.setRemarks(_shabadDoneInCenter.getRemarks().getRemarksText());
                        data.setDateTime(_shabadDoneInCenter.getCentralRelation().getDateTime());
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
                selectedArea.attachAdapterToList(new SelectedAreaAdapter(dataHolders));
            }
        }.execute();

    }
}