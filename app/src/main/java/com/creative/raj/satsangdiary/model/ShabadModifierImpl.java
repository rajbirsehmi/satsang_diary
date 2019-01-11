package com.creative.raj.satsangdiary.model;

import android.content.Context;

import com.creative.raj.satsangdiary.presenter.ShabadModifier;

public class ShabadModifierImpl {

    private Context context;
    private ShabadModifier shabadModifier;

    public ShabadModifierImpl(Context context, ShabadModifier shabadModifier) {
        this.context = context;
        this.shabadModifier = shabadModifier;
    }

    public void editShabad(int shabadId, String newShabadText) {
    }
}
