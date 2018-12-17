package com.creative.raj.satsangdiary.roomdatabase.dao;

import com.creative.raj.satsangdiary.roomdatabase.entities.Center;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CenterDao {

    @Query("SELECT center_id, center_name " +
            "FROM center")
    List<Center> getAllCenters();

    @Query("SELECT center_id, center_name " +
            "FROM center " +
            "WHERE center_id = :centerId")
    Center getSelectedCenter(int centerId);

    @Query("SELECT center_id, center_name " +
            "FROM center " +
            "WHERE center_name = :centerName")
    Center getSelectedCenter(String centerName);

    @Query("SELECT center.center_id," +
            "       center_name" +
            "  FROM area_center_relation" +
            "       INNER JOIN" +
            "       center ON area_center_relation.area_center_relation_area_id = center.center_id" +
            "       INNER JOIN" +
            "       area ON area_center_relation.area_center_relation_area_id = area.area_id" +
            " WHERE area.area_id = :areaId")
    List<Center> getCentersByArea(int areaId);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insertNewCenter(Center center);

    @Update(onConflict = OnConflictStrategy.FAIL)
    void updateExistingCenter(Center center);
}