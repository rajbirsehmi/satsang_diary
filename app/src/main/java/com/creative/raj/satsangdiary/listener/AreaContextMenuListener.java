package com.creative.raj.satsangdiary.listener;

import android.content.Context;
import android.view.View;

import com.creative.raj.satsangdiary.model.AreaManipulatorImpl;
import com.creative.raj.satsangdiary.presenter.AreaManipulator;

public class AreaContextMenuListener implements View.OnLongClickListener, AreaManipulator {

    private int position;
    private AreaManipulatorImpl areaManipulator;
    private Context context;

    public AreaContextMenuListener(int position) {
        this.position = position;
        areaManipulator = new AreaManipulatorImpl(this);
    }

    @Override
    public boolean onLongClick(View v) {
        context = v.getContext();

        return false;
    }

    @Override
    public void callbackAreaEditSuccess() {

    }

    @Override
    public void callbackAreaEditFailed() {

    }

    @Override
    public void callbackAreaRemoveSuccess() {

    }

    @Override
    public void callbackAreaRemoveFailed() {

    }
}
