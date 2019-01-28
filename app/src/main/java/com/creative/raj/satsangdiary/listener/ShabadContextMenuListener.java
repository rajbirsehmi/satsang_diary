package com.creative.raj.satsangdiary.listener;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.creative.raj.satsangdiary.R;
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
    private Dialog dialogEditShabad;

    public ShabadContextMenuListener(int position) {
        this.position = position;
        shabadManipulator = new ShabadManipulatorImpl(this);
    }

    @Override
    public boolean onLongClick(View v) {
        context = v.getContext();
        String shabadText = ShabadList.getShabadItemFromList(position).getText();
        int shabadId = ShabadList.getShabadItemFromList(position).getId();
        new AlertDialog.Builder(v.getContext())
                .setTitle(shabadText)
                .setItems(new String[]{"Edit " + shabadText, "Delete " + shabadText}, (dialog, which) -> {
                    switch (which) {
                        case NumberCodes.ITEM_EDIT:
                            raiseEditShabadDialog(v.getContext(), shabadId, shabadText);
                            break;
                        case NumberCodes.ITEM_DELETE:
                            raiseDeleteShabadDialog(shabadId, shabadText);
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

    private void raiseDeleteShabadDialog(int shabadId, String shabadText) {
        new AlertDialog.Builder(context)
                .setTitle("Delete " + shabadText + "?")
                .setMessage("Are you sure want to delete the Selected Shabad?\n\nRemoving Shabad Will also remove the Sewa you did with this Shabad.\n\nProceed with Caution.!")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shabadManipulator.removeShabad(shabadId);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    private void raiseEditShabadDialog(Context context, int shabadId, String oldShabadText) {
        EditText etNewShabadText;
        dialogEditShabad = new Dialog(context);
        dialogEditShabad.setContentView(R.layout.layout_edit_shabad_dialog);
        dialogEditShabad.setCancelable(false);
        etNewShabadText = dialogEditShabad.findViewById(R.id.et_new_shabad_name);
        etNewShabadText.setText(oldShabadText);
        dialogEditShabad.findViewById(R.id.btn_save_changes).setOnClickListener((view) -> {
            String newShabadText = etNewShabadText.getText().toString();
            shabadManipulator.updateShabad(shabadId, newShabadText);
        });
        dialogEditShabad.findViewById(R.id.btn_dismiss_changes).setOnClickListener((view) -> {
            dialogEditShabad.dismiss();
        });
        dialogEditShabad.create();
        dialogEditShabad.show();
        dialogEditShabad.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void callbackEditSuccess() {
        dialogEditShabad.dismiss();
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
