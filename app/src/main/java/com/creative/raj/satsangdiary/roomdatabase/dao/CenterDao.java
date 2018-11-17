package com.creative.raj.satsangdiary.roomdatabase.dao;

import com.creative.raj.satsangdiary.roomdatabase.entities.Center;
import com.creative.raj.satsangdiary.roomdatabase.entities.ShabadDoneInCenter;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface CenterDao {

    @Query("SELECT center_id, center_name " +
            "FROM center")
    List<Center> getAllCenters();

    @Query("SELECT center_id, center_name " +
            "FROM center " +
            "WHERE center_id = :centerId")
    Center getCenterById(int centerId);


}
