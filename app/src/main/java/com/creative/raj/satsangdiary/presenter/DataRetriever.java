package com.creative.raj.satsangdiary.presenter;

import com.creative.raj.satsangdiary.adapter.AutoCompleteAllAreaAdapter;
import com.creative.raj.satsangdiary.adapter.AutoCompleteAssociatedCenterAdapter;
import com.creative.raj.satsangdiary.adapter.AutoCompleteShabadAdapter;

public interface DataRetriever {
    void setAreaAdapter(AutoCompleteAllAreaAdapter autoCompleteAllAreaAdapter);
    void setCenterAdapter(AutoCompleteAssociatedCenterAdapter autoCompleteAssociatedCenterAdapter);
    void setShabadAdapter(AutoCompleteShabadAdapter autoCompleteShabadAdapter);
}
