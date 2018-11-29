package com.creative.raj.satsangdiary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.dataholders.populators.Area;
import com.creative.raj.satsangdiary.filters.AutoCompleteAreaFilter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AutoCompleteAllAreaAdapter extends ArrayAdapter<Area> {

    private int layoutResource;
    private AutoCompleteAreaFilter areaFilter;

    public AutoCompleteAllAreaAdapter(@NonNull Context context, int resource, @NonNull List<Area> objects) {
        super(context, resource, objects);
        this.layoutResource = resource;
        areaFilter = new AutoCompleteAreaFilter();
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return areaFilter;
    }

    @Override
    public int getCount() {
        return areaFilter.getFilteredList().size();
    }

    @Nullable
    @Override
    public Area getItem(int position) {
        return areaFilter.getFilteredList().get(position);
    }

    public AutoCompleteAllAreaAdapter getAdapter() {
        return this;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false);
        TextView textView = convertView.findViewById(R.id.tv_list_item);
        textView.setText(getItem(position).getName());
        textView.setTag(getItem(position).getId());
        return convertView;
    }
}