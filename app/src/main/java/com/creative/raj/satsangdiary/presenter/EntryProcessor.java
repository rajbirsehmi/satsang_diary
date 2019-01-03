package com.creative.raj.satsangdiary.presenter;

public interface EntryProcessor {

    String getSelectedAreaText();

    String getSelectedCenterText();

    String getSelectedShabadText();

    void notifyAreaNameMissing(String message);

    void notifyCenterNameMissing(String message);

    void notifyShabadTextMissing(String message);

    void dataSaved(String message);

    void notifyCenterConflict(String message);

    void notifySewaTagMissing(String message);

    void notifySewaDateTimeMissing(String message);
}
