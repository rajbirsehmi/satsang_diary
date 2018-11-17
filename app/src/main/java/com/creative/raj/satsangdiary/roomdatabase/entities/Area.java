package com.creative.raj.satsangdiary.roomdatabase.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "area")
public class Area {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "area_id")
    @NonNull
    private int areaId;

    @ColumnInfo(name = "area_name")
    @NonNull
    private String areaName;

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
}
