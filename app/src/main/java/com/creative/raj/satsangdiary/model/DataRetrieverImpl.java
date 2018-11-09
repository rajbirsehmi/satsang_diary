package com.creative.raj.satsangdiary.model;

import android.content.Context;
import android.database.Cursor;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.adapter.AutoCompleteAllAreaAdapter;
import com.creative.raj.satsangdiary.adapter.AutoCompleteAssociatedCenterAdapter;
import com.creative.raj.satsangdiary.adapter.AutoCompleteShabadAdapter;
import com.creative.raj.satsangdiary.dataholders.populators.Area;
import com.creative.raj.satsangdiary.dataholders.populators.Center;
import com.creative.raj.satsangdiary.dataholders.populators.Shabad;
import com.creative.raj.satsangdiary.datebasehelper.QueryManager;
import com.creative.raj.satsangdiary.parser.Parser;
import com.creative.raj.satsangdiary.presenter.DataRetriever;

import java.util.List;

public class DataRetrieverImpl {

    private Context context;
    private DataRetriever dataRetriever;

    public DataRetrieverImpl(Context context, DataRetriever dataRetriever) {
        this.context = context;
        this.dataRetriever = dataRetriever;
    }

    public void getAllAreas() {
        Cursor cursorAllArea = QueryManager.getAllAreas(context);
        List<Area> listArea = Parser.parseAllAreas(cursorAllArea);
        dataRetriever.setAreaAdapter(new AutoCompleteAllAreaAdapter(context, R.layout.template_autocomplete_list_item, listArea));
    }

    public void getAllAssociatedCenters(int areaId) {
        Cursor cursorCenters = QueryManager.getCenters(context, areaId);
        List<Center> listCenters = Parser.parseAssociatedCenters(cursorCenters);
        dataRetriever.setCenterAdapter(new AutoCompleteAssociatedCenterAdapter(context, R.layout.template_autocomplete_list_item, listCenters));
    }

    public void getAllShabads() {
        Cursor cursorAllShabads = QueryManager.getAllShabads(context);
        List<Shabad> listShabad = Parser.parseAllShabads(cursorAllShabads);
        dataRetriever.setShabadAdapter(new AutoCompleteShabadAdapter(context, R.layout.template_autocomplete_list_item, listShabad));
    }
}
