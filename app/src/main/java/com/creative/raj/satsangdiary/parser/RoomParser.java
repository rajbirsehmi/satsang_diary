package com.creative.raj.satsangdiary.parser;

import com.creative.raj.satsangdiary.lists.AreaList;
import com.creative.raj.satsangdiary.lists.CenterList;
import com.creative.raj.satsangdiary.lists.ShabadList;
import com.creative.raj.satsangdiary.roomdatabase.entities.Area;
import com.creative.raj.satsangdiary.roomdatabase.entities.Center;
import com.creative.raj.satsangdiary.roomdatabase.entities.Shabad;

import java.util.List;

public class RoomParser {
    public static void parseAllAreaList(List<Area> allAreas) {
        AreaList.createInstance();
        com.creative.raj.satsangdiary.dataholders.populators.Area area;
        for (Area areaFromList: allAreas) {
            area = new com.creative.raj.satsangdiary.dataholders.populators.Area();
            area.setId(areaFromList.getAreaId());
            area.setName(areaFromList.getAreaName());
            AreaList.addAreaItemToList(area);
        }
    }

    public static void parseAllAssociatedCenters(List<Center> centersByArea) {
        CenterList.createInstance();
        com.creative.raj.satsangdiary.dataholders.populators.Center center;
        for (Center centerFromList : centersByArea) {
            center = new com.creative.raj.satsangdiary.dataholders.populators.Center();
            center.setName(centerFromList.getCenterName());
            center.setId(centerFromList.getCenterId());
            CenterList.addCenterItemToList(center);
        }
    }

    public static void parseAllShabad(List<Shabad> allShabad) {
        ShabadList.createInstance();
        com.creative.raj.satsangdiary.dataholders.populators.Shabad shabad;
        for (Shabad shabadFromList: allShabad) {
            shabad = new com.creative.raj.satsangdiary.dataholders.populators.Shabad();
            shabad.setId(shabadFromList.getShabadId());
            shabad.setText(shabadFromList.getShabadText());
            ShabadList.addShabadItemToList(shabad);
        }
    }
}
