package com.creative.raj.satsangdiary.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.model.SelectedAreaImpl;
import com.creative.raj.satsangdiary.presenter.SelectedArea;

public class SelectedAreaFragment extends Fragment implements SelectedArea {

    private SelectedAreaImpl selectedArea;

    public SelectedAreaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selected_area, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        selectedArea = new SelectedAreaImpl(this);
        selectedArea.loadSelectedAreaData();
    }
}
