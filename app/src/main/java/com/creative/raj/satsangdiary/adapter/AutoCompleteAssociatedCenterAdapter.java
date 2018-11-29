package com.creative.raj.satsangdiary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.dataholders.populators.Center;
import com.creative.raj.satsangdiary.filters.AutoCompleteCenterFilter;
import com.creative.raj.satsangdiary.lists.CenterList;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AutoCompleteAssociatedCenterAdapter extends ArrayAdapter<Center> {

    private int layoutResource;
    private AutoCompleteCenterFilter centerFilter;

    public AutoCompleteAssociatedCenterAdapter(@NonNull Context context, int resource, @NonNull List<Center> objects) {
        super(context, resource, objects);
        this.layoutResource = resource;
        centerFilter = new AutoCompleteCenterFilter();
    }

    public AutoCompleteAssociatedCenterAdapter getAdapter() {
        return this;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return centerFilter;
    }

    @Override
    public int getCount() {
        return centerFilter.getFilteredList().size();
    }

    @Nullable
    @Override
    public Center getItem(int position) {
        return centerFilter.getFilteredList().get(position);
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
