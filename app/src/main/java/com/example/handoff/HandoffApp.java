package com.example.handoff;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class HandoffApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initCustomFont();
    }

    private void initCustomFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Brandon-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}