package com.creative.raj.satsangdiary.model;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Debug;

import com.creative.raj.satsangdiary.presenter.EntryProcessor;
import com.creative.raj.satsangdiary.roomdatabase.database.DiaryDatabase;
import com.creative.raj.satsangdiary.roomdatabase.entities.Area;
import com.creative.raj.satsangdiary.roomdatabase.entities.AreaCenterRelation;
import com.creative.raj.satsangdiary.roomdatabase.entities.Center;
import com.creative.raj.satsangdiary.roomdatabase.entities.CentralRelation;
import com.creative.raj.satsangdiary.roomdatabase.entities.Shabad;
import com.creative.raj.satsangdiary.utils.NumberCodes;

public class EntryProcessorImpl {

    private EntryProcessor entryProcessor;
    private String sewaTypeTag;
    private String sewaDateTime;

    public EntryProcessorImpl(Context context, EntryProcessor entryProcessor) {
        this.entryProcessor = entryProcessor;
    }

    public void addEntry() {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                if (Debug.isDebuggerConnected())
                    Debug.waitForDebugger();
                int areaId;
                int centerId;
                int shabadId;
                int remarksId;
                int centralRelationId;

                long areaCenterRelationId;

                boolean flagIsAreaNew = false;
                boolean flagIsCenterNew = false;
                boolean flagThisIsNewRelation = false;

                String selectedAreaName = entryProcessor.getSelectedAreaText();
                String selectedCenterName = entryProcessor.getSelectedCenterText();
                String selectedShabadText = entryProcessor.getSelectedShabadText();

                if (selectedAreaName.trim().isEmpty()) {
                    return NumberCodes.ERROR_AREA_MISSING;
                }
                if (selectedCenterName.trim().isEmpty()) {
                    return NumberCodes.ERROR_CENTER_MISSING;
                }
                if (selectedShabadText.trim().isEmpty()) {
                    return NumberCodes.ERROR_SHABAD_MISSING;
                }
                if (sewaTypeTag == null) {
                    return NumberCodes.ERROR_REMARKS_MISSING;
                }
                if (sewaDateTime == null) {
                    return NumberCodes.ERROR_DATE_TIME_MISSING;
                }

                Area areaIfExists = DiaryDatabase.getInstance().areaDao().getSelectedArea(selectedAreaName);

                if (areaIfExists == null) {
                    areaId = (int) addNewArea(selectedAreaName);
                    flagIsAreaNew = true;
                } else {
                    areaId = areaIfExists.getAreaId();
                }

                Center centerIfExists = DiaryDatabase.getInstance().centerDao().getSelectedCenter(selectedCenterName);

                if (centerIfExists == null) {
                    centerId = (int) addNewCenter(selectedCenterName);
                    flagIsCenterNew = true;
                } else {
                    centerId = centerIfExists.getCenterId();
                }

                Shabad shabadIfExists = DiaryDatabase.getInstance().shabadDao().getShabadText(selectedShabadText);

                if (shabadIfExists == null) {
                    shabadId = (int) addNewShabad(selectedShabadText);
                } else {
                    shabadId = shabadIfExists.getShabadId();
                }

                remarksId = Integer.parseInt(getSewaTypeTag());

                CentralRelation centralRelation = DiaryDatabase.getInstance().centralRelationDao().getCentralRelation(centerId, shabadId, getSewaDateTime());

                if (centralRelation == null) {
                    centralRelationId = (int) addNewCentralRelation(centerId, shabadId, remarksId, getSewaDateTime());
                } else {
                    return NumberCodes.ERROR_RELATION_ALREADY_EXISTS;
                }

                if ((flagIsAreaNew && flagIsCenterNew) || (!flagIsAreaNew && flagIsCenterNew)) {
                    areaCenterRelationId = addNewAreaCenterRelation(areaId, centerId);
                }

                if (flagIsAreaNew && !flagIsCenterNew) {
                    return NumberCodes.ERROR_CENTER_ALREADY_EXISTS;
                }

                return NumberCodes.THREAD_NATURAL_DEATH;
            }

            @Override
            protected void onPostExecute(Integer responseCode) {
                super.onPostExecute(responseCode);
                switch (responseCode) {
                    case NumberCodes.THREAD_NATURAL_DEATH:
                        entryProcessor.dataSaved("Data is added successfully");
                        setSewaDateTime(null);
                        setSewaTypeTag(null);
                        break;
                    case NumberCodes.ERROR_AREA_MISSING:
                        entryProcessor.notifyAreaNameMissing("Area name is Missing...");
                        break;
                    case NumberCodes.ERROR_CENTER_MISSING:
                        entryProcessor.notifyCenterNameMissing("Center name is Missing...");
                        break;
                    case NumberCodes.ERROR_SHABAD_MISSING:
                        entryProcessor.notifyShabadTextMissing("Shabad is Missing...");
                        break;
                    case NumberCodes.ERROR_CENTER_ALREADY_EXISTS:
                        entryProcessor.notifyCenterConflict("Center You are trying to enter already exists to some other Area.");
                        break;
                    case NumberCodes.ERROR_REMARKS_MISSING:
                        entryProcessor.notifySewaTagMissing("Sewa Remarks are missing...");
                        break;
                    case NumberCodes.ERROR_DATE_TIME_MISSING:
                        entryProcessor.notifySewaDateTimeMissing("Sewa Date Time is Missing...");
                        break;
                    case NumberCodes.ERROR_RELATION_ALREADY_EXISTS:
                        entryProcessor.notifyRelationAleadyExists("You already have same data existing...");
                        break;
                }
            }
        }.execute();
    }

    private boolean suchRelationNotExists(int areaId, int centerId) {
        AreaCenterRelation relationExists = DiaryDatabase.getInstance().areaCenterDao().lookIfSuchRelationExists(areaId, centerId);
        return relationExists == null;
    }

    private long addNewCentralRelation(int centerId, int shabadId, int remarksId, String dateTime) {
        CentralRelation centralRelation = new CentralRelation();
        centralRelation.setCenterId(centerId);
        centralRelation.setShabadId(shabadId);
        centralRelation.setRemarksId(remarksId);
        centralRelation.setDateTime(dateTime);
        return DiaryDatabase.getInstance().centralRelationDao().insertNewCentralRelation(centralRelation);
    }

    private long addNewArea(String selectedAreaName) {
        Area area = new Area();
        area.setAreaName(selectedAreaName);
        return DiaryDatabase.getInstance().areaDao().insertNewArea(area);
    }

    private long addNewCenter(String centerName) {
        Center center = new Center();
        center.setCenterName(centerName);
        return DiaryDatabase.getInstance().centerDao().insertNewCenter(center);
    }

    private long addNewAreaCenterRelation(int areaId, int centerId) {
        AreaCenterRelation relation = new AreaCenterRelation();
        relation.setAreaId(areaId);
        relation.setCenterId(centerId);
        return DiaryDatabase.getInstance().areaCenterDao().addNewAreaCenterRelation(relation);
    }

    private long addNewShabad(String selectedShabadText) {
        Shabad shabad = new Shabad();
        shabad.setShabadText(selectedShabadText);
        return DiaryDatabase.getInstance().shabadDao().insertNewShabad(shabad);
    }

    public String getSewaTypeTag() {
        return sewaTypeTag;
    }

    public void setSewaTypeTag(Object sewaTypeTag) {
        this.sewaTypeTag = (String) sewaTypeTag;
    }

    public String getSewaDateTime() {
        return sewaDateTime;
    }

    public void setSewaDateTime(String sewaDateTime) {
        this.sewaDateTime = sewaDateTime;
    }
}
