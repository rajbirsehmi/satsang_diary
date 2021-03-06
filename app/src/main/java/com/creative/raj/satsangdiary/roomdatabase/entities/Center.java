package com.creative.raj.satsangdiary.roomdatabase.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "center",
        indices = {@Index(value = "center_name", unique = true)})
public class Center {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "center_id")
    @NonNull
    private int centerId;

    @ColumnInfo(name = "center_name")
    @NonNull
    private String centerName;

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
}
