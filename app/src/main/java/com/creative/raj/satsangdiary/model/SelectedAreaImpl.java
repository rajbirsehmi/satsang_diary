package com.creative.raj.satsangdiary.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.creative.raj.satsangdiary.dataholders.selectedarea.DataHolder;
import com.creative.raj.satsangdiary.datebasehelper.QueryManager;
import com.creative.raj.satsangdiary.parser.Parser;
import com.creative.raj.satsangdiary.persistence.Cache;
import com.creative.raj.satsangdiary.presenter.SelectedArea;

import java.util.ArrayList;

public class SelectedAreaImpl {

    private SelectedArea selectedArea;
    private Context context;
    private SQLiteDatabase database;
    private ArrayList<DataHolder> arrDataHolder;
    private DataHolder dataHolder;

    public SelectedAreaImpl(SelectedArea selectedArea, Context context) {
        this.context = context;
        this.selectedArea = selectedArea;
        arrDataHolder = new ArrayList<>();
        dataHolder = new DataHolder();
    }

    public void loadSelectedAreaData() {
        int currentSelectedAreaId = Cache.getCurrentSelectedAreaId(context);
        if (currentSelectedAreaId == -1) {
            selectedArea.notifyNoSelectedAreaDefined("No Selected Area Defined...");
            return;
        }
        Cursor centerIdAndName = QueryManager.getCenters(context, currentSelectedAreaId);
        String[][] arrCenterIdAndName = Parser.parseCenterIdAndName(centerIdAndName);

    }
}