package com.creative.raj.satsangdiary.fragments;


import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creative.raj.satsangdiary.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherAreaFragment extends Fragment {


    private FloatingActionButton fabAdd;

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
    }
}
