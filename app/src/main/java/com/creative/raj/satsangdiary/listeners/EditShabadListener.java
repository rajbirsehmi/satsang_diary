package com.creative.raj.satsangdiary.listeners;

import android.app.Dialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.lists.ShabadList;

public class EditShabadListener implements View.OnLongClickListener {

    private int position;

    public EditShabadListener(int position) {
        this.position = position;
    }

    @Override
    public boolean onLongClick(View v) {
        String shabadText = ShabadList.getShabadItemFromList(position).getText();
        int shabadId = ShabadList.getShabadItemFromList(position).getId();
        Dialog dialog = new Dialog(v.getContext());
        dialog.setContentView(R.layout.layout_edit_shabad_dialog);
        ((TextView)(dialog.findViewById(R.id.tv_current_shabad_text))).setText(shabadText);
        ((EditText)(dialog.findViewById(R.id.et_new_shabad_name))).setText(shabadText);
        dialog.findViewById(R.id.btn_save_changes).setOnClickListener((view) -> {
            // TODO: 10 Jan, 2019 Put Code to Edit Existing Shabad. And push code to GitHub

        });
        dialog.findViewById(R.id.btn_dismiss_changes).setOnClickListener((view) -> {
            dialog.dismiss();
        });
        dialog.setCancelable(false);
        dialog.create();
        dialog.show();
        return true;
    }
}
