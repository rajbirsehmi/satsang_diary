package com.creative.raj.satsangdiary.model;

import com.creative.raj.satsangdiary.persistence.Area;
import com.creative.raj.satsangdiary.presenter.SelectedArea;

public class SelectedAreaImpl {

    private SelectedArea selectedArea;

    public SelectedAreaImpl(SelectedArea selectedArea) {
        this.selectedArea = selectedArea;
    }

    public void loadSelectedAreaData() {
        int currentSelectedAreaId = Area.getCurrentSelectedAreaId();
        String currentSelectedAreaName = Area.getCurrentSelectedAreaName(currentSelectedAreaId);

    }
}
