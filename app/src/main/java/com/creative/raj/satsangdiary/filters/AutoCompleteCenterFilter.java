package com.creative.raj.satsangdiary.filters;

import android.widget.Filter;

import com.creative.raj.satsangdiary.dataholders.populators.Center;
import com.creative.raj.satsangdiary.lists.CenterList;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteCenterFilter extends Filter {

    private List<Center> filteredList;

    public List<Center> getFilteredList() {
        return filteredList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence prefix) {
        if (prefix == null)
            return new FilterResults();
        final String searchLowerCase = prefix.toString().toLowerCase();
        List<Center> matchingCenters = new ArrayList<>();

        for (Center center : CenterList.getInstance())
            if (center.getName().toLowerCase().startsWith(searchLowerCase))
                matchingCenters.add(center);
        FilterResults filterResults = new FilterResults();
        filterResults.values = matchingCenters;
        filterResults.count = matchingCenters.size();
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        if (results.values == null) {
            filteredList = null;
            return;
        }
        filteredList = new ArrayList<>();
        filteredList = (List<Center>) results.values;
    }

    @Override
    public CharSequence convertResultToString(Object resultValue) {
        return ((Center) resultValue).getName();
    }

}
