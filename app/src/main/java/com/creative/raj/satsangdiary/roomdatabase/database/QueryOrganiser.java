package com.creative.raj.satsangdiary.roomdatabase.database;

import com.creative.raj.satsangdiary.parser.RoomParser;
import com.creative.raj.satsangdiary.roomdatabase.entities.Area;
import com.creative.raj.satsangdiary.roomdatabase.entities.Center;
import com.creative.raj.satsangdiary.roomdatabase.entities.Remarks;
import com.creative.raj.satsangdiary.roomdatabase.entities.Shabad;
import com.creative.raj.satsangdiary.roomdatabase.entities.ShabadDoneInCenter;

import java.util.List;

public class QueryOrganiser {
    public static void getAllAreas(DiaryDatabase database) {
        List<Area> allAreas = database.areaDao().getAllAreas();
        RoomParser.parseAllAreaList(allAreas);
    }

    public static void getOtherAreas(DiaryDatabase database, int currentAreaId) {
        List<Area> otherAreas = database.areaDao().getOtherAreas(currentAreaId);
        RoomParser.parseOtherAreaList(otherAreas);
    }

    public static void getAllAssociatedCenters(DiaryDatabase database, int areaId) {
        List<Center> centersByArea = database.areaCenterDao().getCentersByArea(areaId);
        RoomParser.parseAllAssociatedCenters(centersByArea);
    }

    public static void getAllAssociatedCenters(DiaryDatabase database, String areaName) {
        Area area = database.areaDao().getSelectedArea(areaName);
        List<Center> centersByArea = database.areaCenterDao().getCentersByArea(area.getAreaId());
        RoomParser.parseAllAssociatedCenters(centersByArea);
    }

    public static void getAllShabads(DiaryDatabase database) {
        List<Shabad> allShabad = database.shabadDao().getAllShabad();
        RoomParser.parseAllShabad(allShabad);
    }

    public static List<ShabadDoneInCenter> getShabadDoneInOtherCenter(DiaryDatabase database, int areaId) {
        List<ShabadDoneInCenter> shabadDoneInCenters = database.shabadCenterRelationDao().getShabadDoneInOtherCenter(areaId);
        return shabadDoneInCenters;
    }

    public static List<ShabadDoneInCenter> getShabadDoneInCenter(DiaryDatabase database, int centerId) {
        List<ShabadDoneInCenter> shabadDoneInCenters = database.shabadCenterRelationDao().getShabadDoneInCenter(centerId);
        return shabadDoneInCenters;
    }

    public static void injectRemarks(DiaryDatabase database) {
        Remarks remarkFirstSewa = new Remarks();
        remarkFirstSewa.setRemarksText("1st Sewa");
        long res1 = database.remarksDao().insertRemarks(remarkFirstSewa);

        Remarks remarkSecondSewa = new Remarks();
        remarkSecondSewa.setRemarksText("2nd Sewa");
        long res2 = database.remarksDao().insertRemarks(remarkSecondSewa);

        Remarks remarkSpecialSewa = new Remarks();
        remarkSpecialSewa.setRemarksText("Special Sewa");
        long res3 = database.remarksDao().insertRemarks(remarkSpecialSewa);

        Remarks remarkNone = new Remarks();
        remarkNone.setRemarksText("");
        long res4 = database.remarksDao().insertRemarks(remarkNone);
    }

    public static Area getSelectedArea(DiaryDatabase database, int selectedAreaId) {
        return database.areaDao().getSelectedArea(selectedAreaId);
    }

    public static int updateExistingShabad(DiaryDatabase database, Shabad modifiedShabad) {
        return database.shabadDao().updateShabad(modifiedShabad);
    }

    public static int removeShabad(DiaryDatabase database, Shabad shabadToRemove) {
        return database.shabadDao().removeShabad(shabadToRemove);
    }
}
