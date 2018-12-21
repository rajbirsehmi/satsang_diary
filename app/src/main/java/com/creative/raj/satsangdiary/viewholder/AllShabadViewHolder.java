package com.creative.raj.satsangdiary.viewholder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.dataholders.populators.Shabad;
import com.creative.raj.satsangdiary.dataholders.shabad.DataHolder;

public class AllShabadViewHolder extends RecyclerView.ViewHolder {

    private TextView tvShabadText;

    public AllShabadViewHolder(@NonNull View itemView) {
        super(itemView);
        tvShabadText = itemView.findViewById(R.id.tv_shabad_text);
    }

    public void onBind(Shabad shabad) {
        tvShabadText.setText(shabad.getText());
    }
}
