package com.creative.raj.satsangdiary.roomdatabase.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "area_center_relation",
        foreignKeys = {
                @ForeignKey(
                        entity = Area.class,
                        parentColumns = "area_id",
                        childColumns = "area_center_relation_area_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(
                        entity = Center.class,
                        parentColumns = "center_id",
                        childColumns = "area_center_relation_center_id",
                        onDelete = ForeignKey.CASCADE
                )})
public class AreaCenterRelation {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "relation_id")
    @NonNull
    private int relationId;

    @ColumnInfo(name = "area_center_relation_area_id")
    private int areaId;

    @ColumnInfo(name = "area_center_relation_center_id")
    private int centerId;

    @NonNull
    public int getRelationId() {
        return relationId;
    }

    public void setRelationId(@NonNull int relationId) {
        this.relationId = relationId;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }
}
