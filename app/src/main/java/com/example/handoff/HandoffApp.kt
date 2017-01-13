package com.example.handoff

import android.app.Application

import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class HandoffApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initCustomFont()
    }

    private fun initCustomFont() {
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Brandon-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build())
    }
}