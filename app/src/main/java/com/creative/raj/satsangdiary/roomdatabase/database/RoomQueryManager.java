package com.creative.raj.satsangdiary.roomdatabase.database;

import com.creative.raj.satsangdiary.parser.RoomParser;
import com.creative.raj.satsangdiary.roomdatabase.entities.Area;
import com.creative.raj.satsangdiary.roomdatabase.entities.Center;
import com.creative.raj.satsangdiary.roomdatabase.entities.Shabad;

import java.util.List;

public class RoomQueryManager {
    public static void getAllAreas(DiaryDatabase database) {
        List<Area> allAreas = database.areaDao().getAllAreas();
        RoomParser.parseAllAreaList(allAreas);
    }

    public static void getAllAssociatedCenters(DiaryDatabase database, int areaId) {
        List<Center> centersByArea = database.centerDao().getCentersByArea(areaId);
        RoomParser.parseAllAssociatedCenters(centersByArea);
    }

    public static void getAllShabads(DiaryDatabase database) {
        List<Shabad> allShabad = database.shabadDao().getAllShabad();
        RoomParser.parseAllShabad(allShabad);
    }

//    public static void getCentersOfSelectedArea(DiaryDatabase database, int currentSelectedAreaId) {
//        List<Center> centersByArea = database.centerDao().getCentersByArea(currentSelectedAreaId);
//        RoomParser.parseAllAssociatedCenters(centersByArea);
//    }
}
