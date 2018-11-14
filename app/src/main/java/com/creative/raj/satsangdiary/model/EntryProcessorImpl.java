package com.creative.raj.satsangdiary.model;

import android.content.Context;
import android.util.Log;

import com.creative.raj.satsangdiary.lists.AreaList;
import com.creative.raj.satsangdiary.presenter.EntryProcessor;

public class EntryProcessorImpl {

    private Context context;
    private EntryProcessor entryProcessor;

    private int selectedAreaPosition;
    private int selectedCenterPosition;

    public EntryProcessorImpl(Context context, EntryProcessor entryProcessor) {
        this.context = context;
        this.entryProcessor = entryProcessor;
    }

    public void addEntry() {
        String selectedAreaName = entryProcessor.getSelectedAreaText();
        if (AreaList.getListLength() == 0) {
            addInitialArea(selectedAreaName);
            return;
        }

        String areaName = entryProcessor.getAreaName();
        if (selectedAreaName.equals(areaName)) {
        }
        int areaId = entryProcessor.getAreaId();
    }

    public void addInitialArea(String selectedAreaName) {

    }

    public int getSelectedAreaPosition() {
        return selectedAreaPosition;
    }

    public void setSelectedAreaPosition(int position) {
        selectedAreaPosition = position;
    }

    public int getSelectedCenterPosition() {
        return selectedCenterPosition;
    }

    public void setSelectedCenterPosition(int position) {
        this.selectedCenterPosition = position;
    }
}
