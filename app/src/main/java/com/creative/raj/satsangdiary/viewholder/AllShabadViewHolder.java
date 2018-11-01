package com.creative.raj.satsangdiary.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.dataholders.shabad.DataHolder;

public class AllShabadViewHolder extends RecyclerView.ViewHolder {

    private TextView tvShabadText;

    public AllShabadViewHolder(@NonNull View itemView) {
        super(itemView);
        tvShabadText = itemView.findViewById(R.id.tv_shabad_text);
    }

    public void onBind(DataHolder holder) {
        tvShabadText.setText(holder.getShabad());
    }
}
