package com.creative.raj.satsangdiary.dataholders.otherarea;

import java.util.ArrayList;

public class DataHolder {
    private int areaId;
    private String areaName;
    private ArrayList<ExpandedData> expandedData;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

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
