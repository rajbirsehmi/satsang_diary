package com.creative.raj.satsangdiary.roomdatabase.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "shabad",
        indices = {@Index(value = "shabad_text", unique = true)})
public class Shabad {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "shabad_id")
    @NonNull
    private int shabadId;

    @ColumnInfo(name = "shabad_text")
    @NonNull
    private String shabadText;

    @NonNull
    public int getShabadId() {
        return shabadId;
    }

    public void setShabadId(@NonNull int shabadId) {
        this.shabadId = shabadId;
    }

    @NonNull
    public String getShabadText() {
        return shabadText;
    }

    public void setShabadText(@NonNull String shabadText) {
        this.shabadText = shabadText;
    }
}
