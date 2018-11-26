package com.creative.raj.satsangdiary.roomdatabase.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "central_relation",
        foreignKeys = {
                @ForeignKey(entity = Center.class,
                        parentColumns = "center_id",
                        childColumns = "central_relation_center_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Shabad.class,
                        parentColumns = "shabad_id",
                        childColumns = "central_relation_shabad_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Remarks.class,
                        parentColumns = "remarks_id",
                        childColumns = "central_relation_remarks_id",
                        onDelete = ForeignKey.CASCADE)
        })
public class CentralRelation {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "relation_id")
    @NonNull
    private int relationId;

    @ColumnInfo(name = "central_relation_center_id")
    @NonNull
    private int centerId;

    @ColumnInfo(name = "central_relation_shabad_id")
    @NonNull
    private int shabadId;

    @ColumnInfo(name = "central_relation_remarks_id")
    @NonNull
    private int remarksId;

    @ColumnInfo(name = "date_time")
    @NonNull
    private String dateTime;

    @NonNull
    public int getRelationId() {
        return relationId;
    }

    public void setRelationId(@NonNull int relationId) {
        this.relationId = relationId;
    }

    @NonNull
    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(@NonNull int centerId) {
        this.centerId = centerId;
    }

    @NonNull
    public int getShabadId() {
        return shabadId;
    }

    public void setShabadId(@NonNull int shabadId) {
        this.shabadId = shabadId;
    }

    @NonNull
    public int getRemarksId() {
        return remarksId;
    }

    public void setRemarksId(@NonNull int remarksId) {
        this.remarksId = remarksId;
    }

    @NonNull
    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(@NonNull String dateTime) {
        this.dateTime = dateTime;
    }
}
