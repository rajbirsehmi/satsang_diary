package com.creative.raj.satsangdiary.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.adapter.OtherAreaAdapter;
import com.creative.raj.satsangdiary.model.OtherAreaImpl;
import com.creative.raj.satsangdiary.presenter.OtherArea;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherAreaFragment extends Fragment implements OtherArea {

    private OtherAreaImpl otherArea;
    private ExpandableListView elvOtherArea;

    public OtherAreaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other_area, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        elvOtherArea = getActivity().findViewById(R.id.elv_other_area);
        otherArea = new OtherAreaImpl(getContext(), this);
        otherArea.loadOtherAreas();
    }

    @Override
    public void attachAdapterToList(OtherAreaAdapter otherAreaAdapter) {

    }
}
