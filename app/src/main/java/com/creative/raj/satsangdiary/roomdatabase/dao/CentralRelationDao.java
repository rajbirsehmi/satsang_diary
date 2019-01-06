package com.creative.raj.satsangdiary.roomdatabase.dao;

import com.creative.raj.satsangdiary.roomdatabase.entities.CentralRelation;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.FAIL;

@Dao
public interface CentralRelationDao {

    @Insert(onConflict = FAIL)
    long insertNewCentralRelation(CentralRelation centralRelation);

    @Query("SELECT relation_id, central_relation_center_id, central_relation_shabad_id, central_relation_remarks_id, date_time " +
            "FROM central_relation " +
            "WHERE " +
            "central_relation_center_id = :centerId " +
            "AND " +
            "central_relation_shabad_id = :shabadId " +
            "AND " +
            "date_time = :dateTime")
    CentralRelation getCentralRelation(int centerId, int shabadId, String dateTime);
}
