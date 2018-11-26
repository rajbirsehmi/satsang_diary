package com.creative.raj.satsangdiary.roomdatabase.entities;

import androidx.room.Embedded;

public class ShabadDoneInCenter {
    @Embedded
    private CentralRelation centralRelation;

    @Embedded
    private Shabad shabad;

    @Embedded
    private Center center;

    @Embedded
    private Area area;

    @Embedded
    private Remarks remarks;

    public CentralRelation getCentralRelation() {
        return centralRelation;
    }

    public void setCentralRelation(CentralRelation centralRelation) {
        this.centralRelation = centralRelation;
    }

    public Shabad getShabad() {
        return shabad;
    }

    public void setShabad(Shabad shabad) {
        this.shabad = shabad;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Remarks getRemarks() {
        return remarks;
    }

    public void setRemarks(Remarks remarks) {
        this.remarks = remarks;
    }
}