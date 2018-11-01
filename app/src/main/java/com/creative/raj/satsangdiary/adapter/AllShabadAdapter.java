package com.creative.raj.satsangdiary.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.dataholders.shabad.DataHolder;
import com.creative.raj.satsangdiary.viewholder.AllShabadViewHolder;

import java.util.List;

public class AllShabadAdapter extends RecyclerView.Adapter<AllShabadViewHolder> {

    private List<DataHolder> listShabad;
    private Context context;

    public AllShabadAdapter(List<DataHolder> listShabad, Context context) {
        this.listShabad = listShabad;
        this.context = context;
    }

    @NonNull
    @Override
    public AllShabadViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.template_all_shabad, viewGroup, false);
        return new AllShabadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllShabadViewHolder allShabadViewHolder, int position) {
        allShabadViewHolder.onBind(listShabad.get(position));
    }

    @Override
    public int getItemCount() {
        return listShabad.size();
    }
}
