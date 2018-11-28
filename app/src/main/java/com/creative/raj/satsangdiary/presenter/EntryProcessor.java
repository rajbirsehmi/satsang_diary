package com.creative.raj.satsangdiary.presenter;

public interface EntryProcessor {

    int getAreaId();
    String getAreaName();
    String getSelectedAreaText();

    int getCenterId();
    String getSelectedCenterText();

    int getShabadId();
    String getSelectedShabadText();

    void notifyAreaNameMissing(String message);

    void notifyCenterNameMissing(String message);

    void notifyShabadTextMissing(String message);

    void dataSaved(String message);
}
