package com.creative.raj.satsangdiary.presenter;

import com.creative.raj.satsangdiary.adapter.SelectedAreaAdapter;

public interface SelectedArea {
    void notifyNoSelectedAreaDefined(String errorMessage);

    void attachAdapterToList(SelectedAreaAdapter areaAdapter);
}
