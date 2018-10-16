package com.creative.raj.satsangdiary.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.creative.raj.satsangdiary.helper.DatabaseRetriever;
import com.creative.raj.satsangdiary.persistence.Cache;
import com.creative.raj.satsangdiary.presenter.SelectedArea;

public class SelectedAreaImpl {

    private SelectedArea selectedArea;
    private Context context;
    private SQLiteDatabase database;

    public SelectedAreaImpl(SelectedArea selectedArea, Context context) {
        this.context = context;
        this.selectedArea = selectedArea;
        database = DatabaseRetriever.getDatabaseInstance(context);
    }

    public void loadSelectedAreaData() {
        int currentSelectedAreaId = Cache.getCurrentSelectedAreaId(context);
        if (currentSelectedAreaId == -1) {
            selectedArea.notifyNoSelectedAreaDefined("No Selected Area Defined...");
            return;
        }
        // write query here
    }
}
