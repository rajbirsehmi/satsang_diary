package com.creative.raj.satsangdiary.listener;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.creative.raj.satsangdiary.lists.ShabadList;
import com.creative.raj.satsangdiary.model.ShabadManipulatorImpl;
import com.creative.raj.satsangdiary.presenter.ShabadManipulator;
import com.creative.raj.satsangdiary.services.ModificationNotifierService;
import com.creative.raj.satsangdiary.services.UpdateViewService;
import com.creative.raj.satsangdiary.utils.NumberCodes;

public class ShabadContextMenuListener implements View.OnLongClickListener, ShabadManipulator {

    private Context context;
    private int position;
    private ShabadManipulatorImpl shabadManipulator;

    public ShabadContextMenuListener(int position) {
        this.position = position;
        shabadManipulator = new ShabadManipulatorImpl(this);
    }

    @Override
    public boolean onLongClick(View v) {
        String shabadText = ShabadList.getShabadItemFromList(position).getText();
        int shabadId = ShabadList.getShabadItemFromList(position).getId();
        new AlertDialog.Builder(v.getContext())
                .setTitle(shabadText)
                .setItems(new String[]{"Edit " + shabadText, "Delete " + shabadText}, (dialog, which) -> {
                    switch (which) {
                        case NumberCodes.ITEM_EDIT:

                            break;
                        case NumberCodes.ITEM_DELETE:

                            break;
                    }
                })
                .setPositiveButton("Dismiss", (dialog, which) -> {
                    dialog.dismiss();
                })
                .create()
                .show();
        return true;
    }

    @Override
    public void callbackEditSuccess() {
        Intent intentStartNotifierService = new Intent(context, ModificationNotifierService.class);
        intentStartNotifierService.putExtra("result", NumberCodes.NOTIFY_EDIT_SUCCESS);
        intentStartNotifierService.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startService(intentStartNotifierService);
        postCallback();
    }

    @Override
    public void callbackEditFailed(String message) {
        Intent intentStartNotifierService = new Intent(context, ModificationNotifierService.class);
        intentStartNotifierService.putExtra("result", NumberCodes.NOTIFY_EDIT_FAILED);
        intentStartNotifierService.putExtra("message", message);
        intentStartNotifierService.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startService(intentStartNotifierService);
        postCallback();
    }

    @Override
    public void callbackRemoveSuccess() {
        Intent intentStartNotifierService = new Intent(context, ModificationNotifierService.class);
        intentStartNotifierService.putExtra("result", NumberCodes.NOTIFY_REMOVE_SUCCESS);
        intentStartNotifierService.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startService(intentStartNotifierService);
        postCallback();
    }

    @Override
    public void callbackRemoveFailed(String message) {
        Intent intentStartNotifierService = new Intent(context, ModificationNotifierService.class);
        intentStartNotifierService.putExtra("result", NumberCodes.NOTIFY_REMOVE_FAILED);
        intentStartNotifierService.putExtra("message", message);
        intentStartNotifierService.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startService(intentStartNotifierService);
        postCallback();
    }

    private void postCallback() {
        Intent intentStartUpdateService = new Intent(context, UpdateViewService.class);
        intentStartUpdateService.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startService(intentStartUpdateService);
    }
}
