package com.creative.raj.satsangdiary.view;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.fragments.AllShabadFragment;
import com.creative.raj.satsangdiary.fragments.NotificationFragment;
import com.creative.raj.satsangdiary.fragments.OtherAreaFragment;
import com.creative.raj.satsangdiary.fragments.SelectedAreaFragment;
import com.creative.raj.satsangdiary.listener.FragmentChangeListener;
import com.creative.raj.satsangdiary.presenter.FragmentProcessor;

public class MainActivity extends AppCompatActivity implements FragmentProcessor {

    private LinearLayout fragmentContainer;
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0.0f);

        navigationView = findViewById(R.id.navigation_bar);
        navigationView.setOnNavigationItemSelectedListener(new FragmentChangeListener(this));

        loadInitialFragment();
    }

    private void loadInitialFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_containter, new SelectedAreaFragment()).commit();
        navigationView.getMenu().findItem(R.id.navigation_selected_area).setChecked(true);
    }

    @Override
    public void setSelectedAreaFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter, new SelectedAreaFragment()).commit();
        navigationView.getMenu().findItem(R.id.navigation_selected_area).setChecked(true);
    }

    @Override
    public void setOtherAreaFragment() {
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
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter, new AllShabadFragment()).commit();
        navigationView.getMenu().findItem(R.id.navigation_all_shabad_list).setChecked(true);
    }
}