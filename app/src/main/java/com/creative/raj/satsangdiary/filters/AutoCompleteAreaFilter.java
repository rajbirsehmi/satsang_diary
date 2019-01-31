package com.creative.raj.satsangdiary.filters;

import android.widget.Filter;

import com.creative.raj.satsangdiary.dataholders.populators.Area;
import com.creative.raj.satsangdiary.lists.AreaList;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteAreaFilter extends Filter {

    private List<Area> filteredList;

    public List<Area> getFilteredList() {
        return filteredList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence prefix) {
        if (prefix == null)
            return new FilterResults();
        final String searchLowerCase = prefix.toString().toLowerCase();
        List<Area> matchingAreas = new ArrayList<>();

        for (Area area : AreaList.getInstance())
            if (area.getName().toLowerCase().startsWith(searchLowerCase))
                matchingAreas.add(area);
        FilterResults filterResults = new FilterResults();
        filterResults.values = matchingAreas;
        filterResults.count = matchingAreas.size();
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        if (results.values == null) {
            filteredList = null;
            return;
        }
        filteredList = new ArrayList<>();
        filteredList = (List<Area>) results.values;
    }

    @Override
    public CharSequence convertResultToString(Object resultValue) {
        return ((Area) resultValue).getName();
    }
}