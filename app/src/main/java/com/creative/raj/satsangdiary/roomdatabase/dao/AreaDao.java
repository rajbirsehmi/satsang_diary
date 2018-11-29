package com.creative.raj.satsangdiary.roomdatabase.dao;

import com.creative.raj.satsangdiary.roomdatabase.entities.Area;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AreaDao {

    @Query("SELECT area_id, area_name " +
            "FROM area " +
            "WHERE area_id = :areaId")
    Area getSelectedArea(int areaId);

    @Query("SELECT area_id, area_name " +
            "FROM area " +
            "WHERE area_name = :areaName")
    Area getSelectedArea(String areaName);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insertNewArea(Area area);

    @Update(onConflict = OnConflictStrategy.FAIL)
    void updateExistingArea(Area area);

    @Query("SELECT area_id, area_name " +
            "FROM area")
    List<Area> getAllAreas();

    @Query("SELECT area_id, area_name " +
            "FROM area " +
            "WHERE area_id != :currentAreaId")
    List<Area> getOtherAreas(int currentAreaId);
}