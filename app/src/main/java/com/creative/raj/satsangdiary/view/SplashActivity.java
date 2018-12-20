package com.creative.raj.satsangdiary.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.creative.raj.satsangdiary.R;
import com.creative.raj.satsangdiary.model.RemarksInjectionImpl;
import com.creative.raj.satsangdiary.presenter.RemarksInjection;
import com.creative.raj.satsangdiary.roomdatabase.database.DiaryDatabase;

public class SplashActivity extends AppCompatActivity implements RemarksInjection {

    private RemarksInjectionImpl remarksInjection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        DiaryDatabase.createInstance(getBaseContext());
        remarksInjection = new RemarksInjectionImpl(getBaseContext(), this);
        remarksInjection.injectRemarks();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void callbackInjectionSuccess() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }, 2500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        DiaryDatabase.destroyInstance();
    }
}
