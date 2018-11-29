package com.creative.raj.satsangdiary.filters;

import android.widget.Filter;

import com.creative.raj.satsangdiary.dataholders.populators.Shabad;
import com.creative.raj.satsangdiary.lists.ShabadList;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteShabadFilter extends Filter {

    private List<Shabad> filteredList;

    public List<Shabad> getFilteredList() {
        return filteredList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence prefix) {
        if (prefix == null)
            return new FilterResults();
        final String searchLowerCase = prefix.toString().toLowerCase();
        List<Shabad> matchingShabads = new ArrayList<>();

        for (Shabad shabad : ShabadList.getInstance()) {
            if (shabad.getText().toLowerCase().startsWith(searchLowerCase)) {
                matchingShabads.add(shabad);
            }
        }
        FilterResults filterResults = new FilterResults();
        filterResults.values = matchingShabads;
        filterResults.count = matchingShabads.size();
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        if (results.values == null) {
            filteredList = null;
            return;
        }
        filteredList = new ArrayList<>();
        filteredList = (List<Shabad>) results.values;
    }

    @Override
    public CharSequence convertResultToString(Object resultValue) {
        return ((Shabad) resultValue).getText();
    }

}
