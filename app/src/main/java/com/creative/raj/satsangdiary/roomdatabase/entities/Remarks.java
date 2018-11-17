package com.creative.raj.satsangdiary.roomdatabase.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "remarks")
public class Remarks {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "remarks_id")
    @NonNull
    private int remarksId;

    @ColumnInfo(name = "remarks_text")
    @NonNull
    private String remarksText;

    @NonNull
    public int getRemarksId() {
        return remarksId;
    }

    public void setRemarksId(@NonNull int remarksId) {
        this.remarksId = remarksId;
    }

    @NonNull
    public String getRemarksText() {
        return remarksText;
    }

    public void setRemarksText(@NonNull String remarksText) {
        this.remarksText = remarksText;
    }
}
