package com.creative.raj.satsangdiary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.dataholders.populators.Area;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AutoCompleteAllAreaAdapter extends ArrayAdapter<Area> {

    private Context context;
    private int layoutResource;
    private List<Area> list;

    public AutoCompleteAllAreaAdapter(@NonNull Context context, int resource, @NonNull List<Area> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResource = resource;
        this.list = objects;
    }

    public AutoCompleteAllAreaAdapter getAdapter() {
        return this;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false);
        TextView textView = convertView.findViewById(R.id.tv_list_item);
        textView.setText(list.get(position).getName());
//        textView.setTag(list.get(position).getId());
        return convertView;
    }
}