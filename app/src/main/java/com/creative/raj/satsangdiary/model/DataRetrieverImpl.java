package com.creative.raj.satsangdiary.model;

import android.content.Context;

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
        RoomQueryManager.getAllAreas(DiaryDatabase.getInstance(context));
        dataRetriever.setAreaAdapter(new AutoCompleteAllAreaAdapter(context, R.layout.template_autocomplete_list_item, AreaList.getInstance()));
    }

    public void getAllAssociatedCenters(int areaId) {
//        Cursor cursorCenters = QueryManager.getCenters(context, areaId);
//        Parser.parseAssociatedCenters(cursorCenters);
        RoomQueryManager.getAllAssociatedCenters(DiaryDatabase.getInstance(context), areaId);
        dataRetriever.setCenterAdapter(new AutoCompleteAssociatedCenterAdapter(context, R.layout.template_autocomplete_list_item, CenterList.getInstance()));
    }

    public void getAllShabads() {
//        Cursor cursorAllShabads = QueryManager.getAllShabads(context);
//        List<Shabad> listShabad = Parser.parseAllShabads(cursorAllShabads);
        RoomQueryManager.getAllShabads(DiaryDatabase.getInstance(context));
        dataRetriever.setShabadAdapter(new AutoCompleteShabadAdapter(context, R.layout.template_autocomplete_list_item, ShabadList.getInstance()));
    }
}
