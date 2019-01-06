package com.creative.raj.satsangdiary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.dataholders.selectedarea.DataHolder;
import com.creative.raj.satsangdiary.dataholders.selectedarea.ExpandedData;
import com.creative.raj.satsangdiary.parser.Parser;

import java.util.List;

public class SelectedAreaAdapter extends BaseExpandableListAdapter {

    private List<DataHolder> listDataHolder;

    public SelectedAreaAdapter(List<DataHolder> listDataHolder) {
        this.listDataHolder = listDataHolder;
    }

    @Override
    public int getGroupCount() {
        return listDataHolder.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listDataHolder.get(groupPosition).getExpandedData().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHolder.get(groupPosition).getCenterName();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listDataHolder.get(groupPosition).getExpandedData().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return listDataHolder.get(groupPosition).getCenterId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return listDataHolder.get(groupPosition).getExpandedData().get(childPosition).getRelationId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String centerName = (String) getGroup(groupPosition);
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_selected_area_center, parent, false);
        ((TextView) convertView.findViewById(R.id.tv_center_name)).setText(centerName);
        if (isExpanded)
            ((ImageView) convertView.findViewById(R.id.iv_arrow)).setImageDrawable(convertView.getContext().getResources().getDrawable(R.drawable.icon_arrow_up));
        else
            ((ImageView) convertView.findViewById(R.id.iv_arrow)).setImageDrawable(convertView.getContext().getResources().getDrawable(R.drawable.icon_arrow_down));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ExpandedData expandedData = (ExpandedData) getChild(groupPosition, childPosition);
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_selected_area_shabad_detail, parent, false);
        ((TextView) convertView.findViewById(R.id.tv_shabad)).setText(expandedData.getShabad());
        ((TextView) convertView.findViewById(R.id.tv_date_time)).setText(expandedData.getDateTime());
        ((TextView) convertView.findViewById(R.id.tv_remarks)).setText(expandedData.getRemarks());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}