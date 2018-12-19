package com.creative.raj.satsangdiary.model;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Debug;

import com.creative.raj.satsangdiary.presenter.EntryProcessor;
import com.creative.raj.satsangdiary.roomdatabase.database.DiaryDatabase;
import com.creative.raj.satsangdiary.roomdatabase.entities.Area;
import com.creative.raj.satsangdiary.roomdatabase.entities.AreaCenterRelation;
import com.creative.raj.satsangdiary.roomdatabase.entities.Center;
import com.creative.raj.satsangdiary.roomdatabase.entities.Shabad;

public class EntryProcessorImpl {

    private EntryProcessor entryProcessor;

    private int selectedAreaPosition;
    private int selectedCenterPosition;
    private int selectedShabadPosition;

    public EntryProcessorImpl(Context context, EntryProcessor entryProcessor) {
        this.entryProcessor = entryProcessor;
    }

    public void addEntry() {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                if (Debug.isDebuggerConnected())
                    Debug.waitForDebugger();
                int areaId = 0;
                int centerId = 0;
                int shabadId = 0;
                int remarksId = 0;

                long areaCenterRelationId = 0;

                boolean flagIsAreaNew = false;
                boolean flagIsCenterNew = false;
                boolean flagThisIsNewRelation = false;

                String selectedAreaName = entryProcessor.getSelectedAreaText();
                String selectedCenterName = entryProcessor.getSelectedCenterText();
                String selectedShabadText = entryProcessor.getSelectedShabadText();

                if (selectedAreaName.trim().isEmpty()) {
                    return -1;
                }
                if (selectedCenterName.trim().isEmpty()) {
                    return -2;
                }
                if (selectedShabadText.trim().isEmpty()) {
                    return -3;
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

                if (flagIsAreaNew && !flagIsCenterNew) {
                    return -4;
                }

                if (flagIsAreaNew && flagIsCenterNew) {
                    areaCenterRelationId = addNewAreaCenterRelation(areaId, centerId);
                }

                Shabad shabadIfExists = DiaryDatabase.getInstance().shabadDao().getShabadText(selectedShabadText);

                if (shabadIfExists == null) {
                    shabadId = (int) addNewShabad(selectedShabadText);
                } else {
                    shabadId = shabadIfExists.getShabadId();
                }
                return 0;
            }

            @Override
            protected void onPostExecute(Integer responseCode) {
                super.onPostExecute(responseCode);
                switch (responseCode) {
                    case 0:
                        entryProcessor.dataSaved("Data is added successfully");
                        break;
                    case -1:
                        entryProcessor.notifyAreaNameMissing("Area name is Missing...");
                        break;
                    case -2:
                        entryProcessor.notifyCenterNameMissing("Center name is Missing...");
                        break;
                    case -3:
                        entryProcessor.notifyShabadTextMissing("Shabad is Missing...");
                        break;
                    case -4:
                        entryProcessor.notifyCenterConflict("Center You are trying to enter already exists to some other Area.");
                        break;
                }
            }
        }.execute();
    }

    private boolean suchRelationNotExists(int areaId, int centerId) {
        AreaCenterRelation relationExists = DiaryDatabase.getInstance().areaCenterDao().lookIfSuchRelationExists(areaId, centerId);
        return relationExists == null;
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

    public int getSelectedAreaPosition() {
        return selectedAreaPosition;
    }

    public void setSelectedAreaPosition(int position) {
        selectedAreaPosition = position;
    }

    public int getSelectedCenterPosition() {
        return selectedCenterPosition;
    }

    public void setSelectedCenterPosition(int position) {
        this.selectedCenterPosition = position;
    }

    public int getSelectedShabadPosition() {
        return selectedShabadPosition;
    }

    public void setSelectedShabadPosition(int selectedShabadPosition) {
        this.selectedShabadPosition = selectedShabadPosition;
    }
}
