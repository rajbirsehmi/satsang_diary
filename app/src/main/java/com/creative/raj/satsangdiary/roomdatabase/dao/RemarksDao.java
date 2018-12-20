package com.creative.raj.satsangdiary.roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.creative.raj.satsangdiary.roomdatabase.entities.Remarks;

@Dao
public interface RemarksDao {
    @Insert
    long insertRemarks(Remarks remarks);
}
