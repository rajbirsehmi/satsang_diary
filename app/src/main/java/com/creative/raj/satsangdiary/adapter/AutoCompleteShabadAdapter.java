package com.creative.raj.satsangdiary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.dataholders.populators.Shabad;
import com.creative.raj.satsangdiary.filters.AutoCompleteShabadFilter;
import com.creative.raj.satsangdiary.lists.ShabadList;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AutoCompleteShabadAdapter extends ArrayAdapter<Shabad> {

    private int layoutResource;
    private AutoCompleteShabadFilter shabadFilter;

    public AutoCompleteShabadAdapter(@NonNull Context context, int resource, @NonNull List<Shabad> objects) {
        super(context, resource, objects);
        this.layoutResource = resource;
        shabadFilter = new AutoCompleteShabadFilter();
    }

    public AutoCompleteShabadAdapter getAdapter() {
        return this;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return shabadFilter;
    }

    @Override
    public int getCount() {
        return shabadFilter.getFilteredList().size();
    }

    @Nullable
    @Override
    public Shabad getItem(int position) {
        return shabadFilter.getFilteredList().get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false);
        TextView textView = convertView.findViewById(R.id.tv_list_item);
        textView.setText(getItem(position).getText());
        textView.setTag(getItem(position).getId());
        return convertView;
    }
}