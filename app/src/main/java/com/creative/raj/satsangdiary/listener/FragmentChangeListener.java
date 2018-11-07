package com.creative.raj.satsangdiary.listener;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.presenter.FragmentProcessor;

public class FragmentChangeListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentProcessor processor;

    public FragmentChangeListener(FragmentProcessor processor) {
        this.processor = processor;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_selected_area:
                processor.setSelectedAreaFragment();
                break;
            case R.id.navigation_other_area:
                processor.setOtherAreaFragment();
                break;
//            not to be implemented
//            case R.id.navigation_notification:
//                processor.setNotificationFragment();
//                break;
            case R.id.navigation_all_shabad_list:
                processor.setAllShabadFragment();
                break;
        }
        return false;
    }
}
