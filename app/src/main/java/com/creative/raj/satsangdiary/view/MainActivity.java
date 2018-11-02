package com.creative.raj.satsangdiary.view;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.fragments.AllShabadFragment;
import com.creative.raj.satsangdiary.fragments.OtherAreaFragment;
import com.creative.raj.satsangdiary.fragments.SelectedAreaFragment;
import com.creative.raj.satsangdiary.listener.FragmentChangeListener;
import com.creative.raj.satsangdiary.presenter.FragmentProcessor;
import com.creative.raj.satsangdiary.utils.FragmentConstants;
import com.github.clans.fab.FloatingActionMenu;

public class MainActivity extends AppCompatActivity implements FragmentProcessor {

    private BottomNavigationView navigationView;
    private FloatingActionMenu fabMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0.0f);

        fabMenu = findViewById(R.id.fab_menu);

        navigationView = findViewById(R.id.navigation_bar);
        navigationView.setOnNavigationItemSelectedListener(new FragmentChangeListener(this));

        findViewById(R.id.fab_add_entry).setOnClickListener((v) -> {
            fabMenu.close(true);

        });

        findViewById(R.id.fab_add_notification).setOnClickListener((v) -> {
            fabMenu.close(true);
        });

        loadInitialFragment();
    }

    private void loadInitialFragment() {
        FragmentConstants.setCurrentFragmentId(FragmentConstants.SELECTED_AREA_ID);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_containter, new SelectedAreaFragment()).commit();
        navigationView.getMenu().findItem(R.id.navigation_selected_area).setChecked(true);
    }

    @Override
    public void setSelectedAreaFragment() {
        FragmentConstants.setCurrentFragmentId(FragmentConstants.SELECTED_AREA_ID);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter, new SelectedAreaFragment()).commit();
        navigationView.getMenu().findItem(R.id.navigation_selected_area).setChecked(true);
    }

    @Override
    public void setOtherAreaFragment() {
        FragmentConstants.setCurrentFragmentId(FragmentConstants.OTHER_AREA_ID);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter, new OtherAreaFragment()).commit();
        navigationView.getMenu().findItem(R.id.navigation_other_area).setChecked(true);
    }

//    not to be implemented
//    @Override
//    public void setNotificationFragment() {
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter, new NotificationFragment()).commit();
//        navigationView.getMenu().findItem(R.id.navigation_notification).setChecked(true);
//    }

    @Override
    public void setAllShabadFragment() {
        FragmentConstants.setCurrentFragmentId(FragmentConstants.SHABAD_LIST);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter, new AllShabadFragment()).commit();
        navigationView.getMenu().findItem(R.id.navigation_all_shabad_list).setChecked(true);
    }
}