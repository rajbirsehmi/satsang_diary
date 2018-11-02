package com.creative.raj.satsangdiary.model;

import android.content.Context;
import android.database.Cursor;

import com.creative.raj.satsangdiary.adapter.SelectedAreaAdapter;
import com.creative.raj.satsangdiary.dataholders.selectedarea.DataHolder;
import com.creative.raj.satsangdiary.dataholders.selectedarea.ExpandedData;
import com.creative.raj.satsangdiary.datebasehelper.QueryManager;
import com.creative.raj.satsangdiary.parser.Parser;
import com.creative.raj.satsangdiary.persistence.Cache;
import com.creative.raj.satsangdiary.presenter.SelectedArea;

import java.util.ArrayList;
import java.util.List;

public class SelectedAreaImpl {

    private SelectedArea selectedArea;
    private Context context;
    private List<DataHolder> arrDataHolder;

    public SelectedAreaImpl(SelectedArea selectedArea, Context context) {
        this.context = context;
        this.selectedArea = selectedArea;
        arrDataHolder = new ArrayList<>();
    }

    public void loadSelectedAreaData() {
        int currentSelectedAreaId = Cache.getCurrentSelectedAreaId(context);
        if (currentSelectedAreaId == -1) {
            selectedArea.notifyNoSelectedAreaDefined("No Selected Area Defined...");
            return;
        }
        Cursor centerIds = QueryManager.getCenterIds(context, currentSelectedAreaId);
        String[] arrCenterIds = Parser.parseCenterIds(centerIds);

        for (String centerId : arrCenterIds) {
            DataHolder dataHolder = new DataHolder();
            int _centerId = Integer.parseInt(centerId);
            Cursor cursorCenterName = QueryManager.getCenterName(context, _centerId);
            String centerName = Parser.parseCenterName(cursorCenterName, _centerId);

            dataHolder.setCenterName(centerName);
            dataHolder.setCenterId(_centerId);

            Cursor cursorMoreDetail = QueryManager.getShabadDoneInCenter(context, _centerId);
            ArrayList<ExpandedData> arrExpandedData = Parser.parseShabadAndCenterData(cursorMoreDetail);
            dataHolder.setExpandedData(arrExpandedData);
            arrDataHolder.add(dataHolder);
        }

        selectedArea.attachAdapterToList(new SelectedAreaAdapter(context, arrDataHolder));
    }
}