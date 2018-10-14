package com.creative.raj.satsangdiary.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creative.raj.satsangdiary.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllShabadFragment extends Fragment {


    public AllShabadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_shabad, container, false);
    }

}
