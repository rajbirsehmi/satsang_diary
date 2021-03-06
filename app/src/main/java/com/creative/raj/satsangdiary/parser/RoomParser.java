package com.creative.raj.satsangdiary.parser;

import com.creative.raj.satsangdiary.lists.AreaList;
import com.creative.raj.satsangdiary.lists.CenterList;
import com.creative.raj.satsangdiary.lists.ExtendedCenterList;
import com.creative.raj.satsangdiary.lists.OtherAreaList;
import com.creative.raj.satsangdiary.lists.ShabadList;
import com.creative.raj.satsangdiary.roomdatabase.entities.Area;
import com.creative.raj.satsangdiary.roomdatabase.entities.Center;
import com.creative.raj.satsangdiary.roomdatabase.entities.Shabad;

import java.util.List;

public class RoomParser {

    public static void parseAllAreaList(List<Area> allAreas) {
        AreaList.createInstance();
        if (AreaList.getListLength() != 0)
            AreaList.removeAllAreas();
        com.creative.raj.satsangdiary.dataholders.populators.Area area;
        for (Area areaFromList : allAreas) {
            area = new com.creative.raj.satsangdiary.dataholders.populators.Area();
            area.setId(areaFromList.getAreaId());
            area.setName(areaFromList.getAreaName());
            AreaList.addAreaToList(area);
        }
    }

    public static void parseOtherAreaList(List<Area> otherAreas) {
        OtherAreaList.createInstance();
        if (OtherAreaList.getListLength() != 0)
            OtherAreaList.removeAllAreas();
        com.creative.raj.satsangdiary.dataholders.populators.Area area;
        for (Area areaFromList : otherAreas) {
            area = new com.creative.raj.satsangdiary.dataholders.populators.Area();
            area.setId(areaFromList.getAreaId());
            area.setName(areaFromList.getAreaName());
            OtherAreaList.addOtherAreaToList(area);
        }
    }

    public static void parseAllAssociatedCenters(List<Center> centersByArea) {
        CenterList.createInstance();
        if (CenterList.getListLength() != 0)
            CenterList.removeAllCenters();
        com.creative.raj.satsangdiary.dataholders.populators.Center center;
        for (Center centerFromList : centersByArea) {
            center = new com.creative.raj.satsangdiary.dataholders.populators.Center();
            center.setName(centerFromList.getCenterName());
            center.setId(centerFromList.getCenterId());
            CenterList.addCenterItemToList(center);
        }
    }

    public static void parseExtendedCentersData(List<Center> centersByArea) {
        ExtendedCenterList.createInstance();
        if (ExtendedCenterList.getListLength() != 0)
            ExtendedCenterList.removeAllCenters();
        com.creative.raj.satsangdiary.dataholders.populators.Center center;
        for (Center centerFromList : centersByArea) {
            center = new com.creative.raj.satsangdiary.dataholders.populators.Center();
            center.setName(centerFromList.getCenterName());
            center.setId(centerFromList.getCenterId());
            ExtendedCenterList.addCenterToList(center);
        }
    }

    public static void parseAllShabad(List<Shabad> allShabad) {
        ShabadList.createInstance();
        if (ShabadList.getListLength() != 0)
            ShabadList.removeAllShabad();
        com.creative.raj.satsangdiary.dataholders.populators.Shabad shabad;
        for (Shabad shabadFromList : allShabad) {
            shabad = new com.creative.raj.satsangdiary.dataholders.populators.Shabad();
            shabad.setId(shabadFromList.getShabadId());
            shabad.setText(shabadFromList.getShabadText());
            ShabadList.addShabadItemToList(shabad);
        }
    }
}
