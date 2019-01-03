package com.creative.raj.satsangdiary.roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.creative.raj.satsangdiary.roomdatabase.entities.CentralRelation;

import static androidx.room.OnConflictStrategy.FAIL;

@Dao
public interface CentralRelationDao {

    @Insert(onConflict = FAIL)
    long insertNewCentralRelation(CentralRelation centralRelation);

}
