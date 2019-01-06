package com.creative.raj.satsangdiary.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.dataholders.otherarea.DataHolder;
import com.creative.raj.satsangdiary.dataholders.otherarea.ExpandedData;
import com.creative.raj.satsangdiary.parser.Parser;

import java.util.List;

public class OtherAreaAdapter extends BaseExpandableListAdapter {

    private List<DataHolder> listDataHolder;

    public OtherAreaAdapter(List<DataHolder> listDataHolder) {
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
        return listDataHolder.get(groupPosition).getAreaName();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listDataHolder.get(groupPosition).getExpandedData().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return listDataHolder.get(groupPosition).getAreaId();
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
        String areaName = (String) getGroup(groupPosition);
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_other_area_name, parent, false);
        ((TextView) convertView.findViewById(R.id.tv_area_name)).setText(areaName);
        if (isExpanded)
            ((ImageView) convertView.findViewById(R.id.iv_arrow)).setImageDrawable(convertView.getContext().getResources().getDrawable(R.drawable.icon_arrow_up));
        else
            ((ImageView) convertView.findViewById(R.id.iv_arrow)).setImageDrawable(convertView.getContext().getResources().getDrawable(R.drawable.icon_arrow_down));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ExpandedData expandedData = (ExpandedData) getChild(groupPosition, childPosition);
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_other_center_shabad_detail, parent, false);
        ((TextView) convertView.findViewById(R.id.tv_center_name)).setText(expandedData.getCenterName());
        ((TextView) convertView.findViewById(R.id.tv_shabad)).setText(expandedData.getShabad());
        ((TextView) convertView.findViewById(R.id.tv_date_time)).setText(expandedData.getDatetime());
        ((TextView) convertView.findViewById(R.id.tv_remarks)).setText(expandedData.getRemarks());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
