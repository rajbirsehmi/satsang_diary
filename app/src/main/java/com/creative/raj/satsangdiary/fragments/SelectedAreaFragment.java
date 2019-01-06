package com.creative.raj.satsangdiary.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

import androidx.fragment.app.Fragment;

public class SelectedAreaFragment extends Fragment implements SelectedArea {

    private SelectedAreaImpl selectedArea;
    private ExpandableListView elvSelecedArea;

    private BroadcastReceiver brUpdateSelectedArea = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            selectedArea.loadSelectedAreaData();
        }
    };

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
        getActivity().registerReceiver(brUpdateSelectedArea, new IntentFilter("update_selected_area"));
        elvSelecedArea = getActivity().findViewById(R.id.elv_selected_area);
        selectedArea = new SelectedAreaImpl(getContext(), this);
        selectedArea.loadSelectedAreaData();
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(brUpdateSelectedArea);
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
