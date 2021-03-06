package com.creative.raj.satsangdiary.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.dataholders.populators.Shabad;
import com.creative.raj.satsangdiary.listener.ShabadContextMenuListener;
import com.creative.raj.satsangdiary.viewholder.AllShabadViewHolder;

import java.util.List;

public class AllShabadAdapter extends RecyclerView.Adapter<AllShabadViewHolder> {

    private List<Shabad> listShabad;

    public AllShabadAdapter(List<Shabad> listShabad) {
        this.listShabad = listShabad;
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
        allShabadViewHolder.setOnLongClickListener(new ShabadContextMenuListener(position));
    }

    @Override
    public int getItemCount() {
        return listShabad.size();
    }
}
