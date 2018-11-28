package com.creative.raj.satsangdiary.roomdatabase.dao;

import com.creative.raj.satsangdiary.roomdatabase.entities.Shabad;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ShabadDao {

    @Query("SELECT shabad_id, shabad_text " +
            "FROM shabad")
    List<Shabad> getAllShabad();

    @Query("SELECT shabad_id, shabad_text " +
            "FROM shabad " +
            "WHERE shabad_text = :selectedShabadText")
    Shabad getShabadText(String selectedShabadText);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insertNewShabad(Shabad shabad);
}
