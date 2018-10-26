package com.creative.raj.satsangdiary.dataholders.selectedarea;

import java.util.ArrayList;

public class DataHolder {
    private int centerId;
    private String centerName;
    private ArrayList<ExpandedData> expandedData;

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public ArrayList<ExpandedData> getExpandedData() {
        return expandedData;
    }

    public void setExpandedData(ArrayList<ExpandedData> expandedData) {
        this.expandedData = expandedData;
    }
}
