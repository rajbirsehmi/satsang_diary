package com.creative.raj.satsangdiary.roomdatabase.dao;

import com.creative.raj.satsangdiary.roomdatabase.entities.AreaCenterRelation;
import com.creative.raj.satsangdiary.roomdatabase.entities.Center;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AreaCenterDao {
    @Query("SELECT center.center_id, center.center_name " +
            "FROM area_center_relation " +
            "INNER JOIN center " +
            "ON area_center_relation.center_id = center.center_id " +
            "INNER JOIN area " +
            "ON area_center_relation.area_id = area.area_id " +
            "WHERE area.area_id = :areaId")
    List<Center> getCentersByArea(int areaId);

    @Insert
    long addNewAreaCenterRelation(AreaCenterRelation areaCenterRelation);
}
