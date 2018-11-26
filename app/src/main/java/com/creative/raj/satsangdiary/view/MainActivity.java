package com.creative.raj.satsangdiary.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.adapter.AutoCompleteAllAreaAdapter;
import com.creative.raj.satsangdiary.adapter.AutoCompleteAssociatedCenterAdapter;
import com.creative.raj.satsangdiary.adapter.AutoCompleteShabadAdapter;
import com.creative.raj.satsangdiary.fragments.AllShabadFragment;
import com.creative.raj.satsangdiary.fragments.OtherAreaFragment;
import com.creative.raj.satsangdiary.fragments.SelectedAreaFragment;
import com.creative.raj.satsangdiary.listener.FragmentChangeListener;
import com.creative.raj.satsangdiary.lists.AreaList;
import com.creative.raj.satsangdiary.model.DataRetrieverImpl;
import com.creative.raj.satsangdiary.model.EntryProcessorImpl;
import com.creative.raj.satsangdiary.presenter.DataRetriever;
import com.creative.raj.satsangdiary.presenter.EntryProcessor;
import com.creative.raj.satsangdiary.presenter.FragmentProcessor;
import com.creative.raj.satsangdiary.roomdatabase.database.DiaryDatabase;
import com.creative.raj.satsangdiary.utils.ActivityUtils;
import com.creative.raj.satsangdiary.utils.FragmentConstants;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements FragmentProcessor, EntryProcessor, DataRetriever {

    private BottomNavigationView navigationView;
    private FloatingActionMenu fabMenu;
    private DataRetrieverImpl dataRetriever;
    private AutoCompleteTextView actvArea, actvCenter, actvShabad;
    private EntryProcessorImpl entryProcessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0.0f);

        fabMenu = findViewById(R.id.fab_menu);

        navigationView = findViewById(R.id.navigation_bar);
        navigationView.setOnNavigationItemSelectedListener(new FragmentChangeListener(this));
        dataRetriever = new DataRetrieverImpl(getBaseContext(), this);
        entryProcessor = new EntryProcessorImpl(getBaseContext(), MainActivity.this);

        findViewById(R.id.fab_add_entry).setOnClickListener((v) -> {
            fabMenu.close(true);
            raiseDialog();
        });

        findViewById(R.id.fab_add_notification).setOnClickListener((v) -> {
            fabMenu.close(true);
        });
        loadInitialFragment();
    }

    private void raiseDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog_add_new_entry);
        dialog.setCancelable(false);
        actvArea = dialog.findViewById(R.id.actv_area_name);
        actvCenter = dialog.findViewById(R.id.actv_center_name);
        actvShabad = dialog.findViewById(R.id.actv_shabad);
        dataRetriever.getAllAreas();
        dataRetriever.getAllShabads();
        actvArea.setOnItemClickListener((parent, view, position, id) -> {
            entryProcessor.setSelectedAreaPosition(position);
        });
        dialog.findViewById(R.id.btn_dialog_save_entry).setOnClickListener((view) -> {
            entryProcessor.addEntry();
        });
        dialog.findViewById(R.id.btn_dialog_dismiss).setOnClickListener((view) -> {
            dialog.dismiss();
        });
        dialog.create();
        dialog.show();
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void loadInitialFragment() {
        FragmentConstants.setCurrentFragmentId(FragmentConstants.SELECTED_AREA_ID);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_containter, new SelectedAreaFragment()).commit();
        navigationView.getMenu().findItem(R.id.navigation_selected_area).setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mi_settings) {
            ActivityUtils.startActivity(getBaseContext(), new Intent(ActivityUtils.ACTION_URL_SETTING_ACTIVITY));
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public void setAreaAdapter(AutoCompleteAllAreaAdapter autoCompleteAllAreaAdapter) {
        actvArea.setAdapter(autoCompleteAllAreaAdapter);
    }

    @Override
    public void setCenterAdapter(AutoCompleteAssociatedCenterAdapter autoCompleteAssociatedCenterAdapter) {
        actvCenter.setAdapter(autoCompleteAssociatedCenterAdapter);
    }

    @Override
    public void setShabadAdapter(AutoCompleteShabadAdapter autoCompleteShabadAdapter) {
        actvCenter.setAdapter(autoCompleteShabadAdapter);
    }

    @Override
    public int getAreaId() {
        return AreaList.getAreaItemFromList(entryProcessor.getSelectedAreaPosition()).getId();
    }

    @Override
    public String getAreaName() {
        return AreaList.getAreaItemFromList(entryProcessor.getSelectedAreaPosition()).getName();
    }

    @Override
    public String getSelectedAreaText() {
        return actvArea.getText().toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DiaryDatabase.destroyInstance();
    }
}