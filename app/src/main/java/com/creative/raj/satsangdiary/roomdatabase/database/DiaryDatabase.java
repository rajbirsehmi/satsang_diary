package com.creative.raj.satsangdiary.roomdatabase.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.creative.raj.satsangdiary.roomdatabase.dao.AreaCenterDao;
import com.creative.raj.satsangdiary.roomdatabase.dao.AreaDao;
import com.creative.raj.satsangdiary.roomdatabase.dao.CenterDao;
import com.creative.raj.satsangdiary.roomdatabase.dao.CentralRelationDao;
import com.creative.raj.satsangdiary.roomdatabase.dao.RemarksDao;
import com.creative.raj.satsangdiary.roomdatabase.dao.ShabadCenterRelationDao;
import com.creative.raj.satsangdiary.roomdatabase.dao.ShabadDao;
import com.creative.raj.satsangdiary.roomdatabase.entities.Area;
import com.creative.raj.satsangdiary.roomdatabase.entities.AreaCenterRelation;
import com.creative.raj.satsangdiary.roomdatabase.entities.Center;
import com.creative.raj.satsangdiary.roomdatabase.entities.CentralRelation;
import com.creative.raj.satsangdiary.roomdatabase.entities.Remarks;
import com.creative.raj.satsangdiary.roomdatabase.entities.Shabad;

@Database(entities = {Area.class,
        AreaCenterRelation.class,
        Center.class,
        CentralRelation.class,
        Remarks.class,
        Shabad.class}, version = 1)
public abstract class DiaryDatabase extends RoomDatabase {

    private static DiaryDatabase instance;

    public static void createInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, DiaryDatabase.class, "diary_database").build();
        }
    }

    public static DiaryDatabase getInstance() {
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

    public abstract AreaDao areaDao();

    public abstract AreaCenterDao areaCenterDao();

    public abstract CenterDao centerDao();

    public abstract ShabadDao shabadDao();

    public abstract ShabadCenterRelationDao shabadCenterRelationDao();

    public abstract RemarksDao remarksDao();

    public abstract CentralRelationDao centralRelationDao();
}