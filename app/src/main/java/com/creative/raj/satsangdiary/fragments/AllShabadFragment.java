package com.creative.raj.satsangdiary.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.adapter.AllShabadAdapter;
import com.creative.raj.satsangdiary.model.AllShabadImpl;
import com.creative.raj.satsangdiary.presenter.AllShabad;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllShabadFragment extends Fragment implements AllShabad {

    private RecyclerView rvAllShabad;
    private AllShabadImpl allShabad;

    private BroadcastReceiver brUpdateShabadList = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            allShabad.loadAllShabad();
        }
    };

    public AllShabadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_shabad, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().registerReceiver(brUpdateShabadList, new IntentFilter("update_shabad_list"));

        rvAllShabad = getActivity().findViewById(R.id.rv_all_shabad);
        rvAllShabad.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        rvAllShabad.setItemAnimator(new DefaultItemAnimator());

        allShabad = new AllShabadImpl(this, getActivity().getBaseContext());
        allShabad.loadAllShabad();
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(brUpdateShabadList);
    }

    @Override
    public void attachAdapterToView(AllShabadAdapter shabadAdapter) {
        rvAllShabad.setAdapter(shabadAdapter);
    }

    @Override
    public void notifyNoShabadFound(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
