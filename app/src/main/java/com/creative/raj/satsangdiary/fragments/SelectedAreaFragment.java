package com.creative.raj.satsangdiary.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.adapter.SelectedAreaAdapter;
import com.creative.raj.satsangdiary.model.SelectedAreaImpl;
import com.creative.raj.satsangdiary.presenter.SelectedArea;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;

public class SelectedAreaFragment extends Fragment implements SelectedArea {

    private SelectedAreaImpl selectedArea;
    private ExpandableListView elvSelecedArea;

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
//        fabAdd = getActivity().findViewById(R.id.fab_add);
        elvSelecedArea = getActivity().findViewById(R.id.elv_selected_area);
        selectedArea = new SelectedAreaImpl(this, getContext());
        selectedArea.loadSelectedAreaData();
    }

    @Override
    public void notifyNoSelectedAreaDefined(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void attachAdapterToList(SelectedAreaAdapter areaAdapter) {
        elvSelecedArea.setAdapter(areaAdapter);
    }
}
