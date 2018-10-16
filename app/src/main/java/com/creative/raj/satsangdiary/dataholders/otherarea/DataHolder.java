package com.creative.raj.satsangdiary.dataholders.otherarea;

import java.util.ArrayList;

public class DataHolder {
    private String areaName;
    private ArrayList<ExpandedData> expandedData;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public ArrayList<ExpandedData> getExpandedData() {
        return expandedData;
    }

    public void setExpandedData(ArrayList<ExpandedData> expandedData) {
        this.expandedData = expandedData;
    }
}
