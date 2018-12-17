package com.creative.raj.satsangdiary.roomdatabase.dao;

import com.creative.raj.satsangdiary.roomdatabase.entities.ShabadDoneInCenter;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface ShabadCenterRelationDao {

    @Query("SELECT central_relation.relation_id, shabad_text, remarks_text, date_time " +
            "FROM area " +
            "INNER JOIN area_center_relation " +
            "ON area_center_relation.area_center_relation_area_id = area.area_id " +
            "INNER JOIN center " +
            "ON center.center_id = area_center_relation.area_center_relation_center_id " +
            "INNER JOIN central_relation " +
            "ON central_relation.central_relation_center_id = center.center_id " +
            "INNER JOIN shabad " +
            "ON shabad.shabad_id = central_relation.central_relation_shabad_id " +
            "INNER JOIN remarks " +
            "ON remarks.remarks_id = central_relation.central_relation_remarks_id " +
            "WHERE center.center_id = :centerId")
    List<ShabadDoneInCenter> getShabadDoneInCenter(int centerId);

    @Query("SELECT center.center_id, center.center_name, central_relation.relation_id, shabad_text, remarks.remarks_text, central_relation.date_time " +
            " FROM area " +
            "INNER JOIN area_center_relation " +
            "ON area_center_relation.area_center_relation_area_id = area.area_id " +
            "INNER JOIN center " +
            "ON center.center_id = area_center_relation.area_center_relation_center_id " +
            "INNER JOIN central_relation " +
            "ON central_relation.central_relation_center_id = center.center_id " +
            "INNER JOIN shabad " +
            "ON shabad.shabad_id = central_relation.central_relation_shabad_id " +
            "INNER JOIN remarks " +
            "ON remarks.remarks_id = central_relation.central_relation_remarks_id " +
            "WHERE area.area_id = :currentAreaId")
    List<ShabadDoneInCenter> getShabadDoneInOtherCenter(int currentAreaId);


}