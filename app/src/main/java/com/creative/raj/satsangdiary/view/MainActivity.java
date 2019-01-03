package com.creative.raj.satsangdiary.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.adapter.AutoCompleteAllAreaAdapter;
import com.creative.raj.satsangdiary.adapter.AutoCompleteAssociatedCenterAdapter;
import com.creative.raj.satsangdiary.adapter.AutoCompleteShabadAdapter;
import com.creative.raj.satsangdiary.fragments.AllShabadFragment;
import com.creative.raj.satsangdiary.fragments.OtherAreaFragment;
import com.creative.raj.satsangdiary.fragments.SelectedAreaFragment;
import com.creative.raj.satsangdiary.listener.FragmentChangeListener;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements FragmentProcessor, EntryProcessor, DataRetriever {

    private BottomNavigationView navigationView;
    private FloatingActionMenu fabMenu;
    private AutoCompleteTextView actvArea, actvCenter, actvShabad;
    private Dialog dialogAddEntry;
    private RadioGroup rgSewaType;

    private DataRetrieverImpl dataRetriever;
    private EntryProcessorImpl entryProcessor;

    @RequiresApi(api = Build.VERSION_CODES.N)
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void raiseDialog() {
        dialogAddEntry = new Dialog(this);
        dialogAddEntry.setContentView(R.layout.layout_dialog_add_new_entry);
        dialogAddEntry.setCancelable(false);
        actvArea = dialogAddEntry.findViewById(R.id.actv_area_name);
        actvCenter = dialogAddEntry.findViewById(R.id.actv_center_name);
        actvShabad = dialogAddEntry.findViewById(R.id.actv_shabad);
        rgSewaType = dialogAddEntry.findViewById(R.id.rg_sewa_type);
        dataRetriever.getAllAreas();
        dataRetriever.getAllShabads();
        actvArea.setOnItemClickListener((parent, view, position, id) -> {
            dataRetriever.getAllAssociatedCenters(actvArea.getText().toString());
        });
        rgSewaType.setOnCheckedChangeListener((group, checkedId) -> {
            entryProcessor.setSewaTypeTag(dialogAddEntry.findViewById(checkedId).getTag());
        });
        dialogAddEntry.findViewById(R.id.btn_select_date_time).setOnClickListener((view) -> {
            raiseDatePickerDialog();
        });
        dialogAddEntry.findViewById(R.id.btn_dialog_save_entry).setOnClickListener((view) -> {
            entryProcessor.addEntry();
        });
        dialogAddEntry.findViewById(R.id.btn_dialog_dismiss).setOnClickListener((view) -> {
            dialogAddEntry.dismiss();
        });
        dialogAddEntry.create();
        dialogAddEntry.show();
        dialogAddEntry.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void raiseDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.setOnDateSetListener((view, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            entryProcessor.setSewaDateTime(String.valueOf(calendar.getTimeInMillis()));
            ((Button)(dialogAddEntry.findViewById(R.id.btn_select_date_time))).setText(new SimpleDateFormat("EEEE, MMM dd, YYYY").format(new Date(Long.parseLong(entryProcessor.getSewaDateTime()))));
        });
        datePickerDialog.show();
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
        if (item.getItemId() == R.id.mi_about_app) {
            Dialog dialogAboutApp = new Dialog(this);
            dialogAboutApp.setCancelable(false);
            dialogAboutApp.setContentView(R.layout.layout_about_app);
            dialogAboutApp.findViewById(R.id.btn_dialog_dismiss).setOnClickListener((v) -> {
                dialogAboutApp.dismiss();
            });
            dialogAboutApp.create();
            dialogAboutApp.show();
            dialogAboutApp.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
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
        actvShabad.setAdapter(autoCompleteShabadAdapter);
    }

    @Override
    public String getSelectedAreaText() {
        return actvArea.getText().toString();
    }

    @Override
    public String getSelectedCenterText() {
        return actvCenter.getText().toString();
    }

    @Override
    public String getSelectedShabadText() {
        return actvShabad.getText().toString();
    }

    @Override
    public void notifyAreaNameMissing(String message) {
        actvArea.setError(message);
    }

    @Override
    public void notifyCenterNameMissing(String message) {
        actvCenter.setError(message);
    }

    @Override
    public void notifyShabadTextMissing(String message) {
        actvShabad.setError(message);
    }

    @Override
    public void dataSaved(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        if (dialogAddEntry.isShowing()) {
            dialogAddEntry.dismiss();
        }
    }

    @Override
    public void notifyCenterConflict(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifySewaTagMissing(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifySewaDateTimeMissing(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DiaryDatabase.destroyInstance();
    }
}