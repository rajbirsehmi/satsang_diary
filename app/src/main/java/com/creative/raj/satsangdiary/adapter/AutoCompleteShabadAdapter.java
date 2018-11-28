package com.creative.raj.satsangdiary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.dataholders.populators.Shabad;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AutoCompleteShabadAdapter extends ArrayAdapter<Shabad> {

    private Context context;
    private int layoutResource;
    private List<Shabad> list;

    public AutoCompleteShabadAdapter(@NonNull Context context, int resource, @NonNull List<Shabad> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResource = resource;
        this.list = objects;
    }

    public AutoCompleteShabadAdapter getAdapter() {
        return this;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false);
        TextView textView = convertView.findViewById(R.id.tv_list_item);
        textView.setText(list.get(position).getText());
//        textView.setTag(list.get(position).getId());
        return convertView;
    }
}