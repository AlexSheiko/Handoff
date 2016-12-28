package com.example.handoff.ui.base

import android.content.Context
import android.content.SharedPreferences
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.KITKAT
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
import android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.handoff.R
import com.example.handoff.ui.main.MainActivity
import com.example.handoff.util.Extensions
import org.jetbrains.anko.startActivity
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

open class BaseActivity : AppCompatActivity(), Extensions {

    /**
     * Use custom font in all screens.
     */
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transparentNavigation()
    }

    fun transparentNavigation() {
        if (SDK_INT >= KITKAT) {
            window.setFlags(FLAG_TRANSLUCENT_NAVIGATION, FLAG_TRANSLUCENT_NAVIGATION)
        }
    }

    fun transparentStatus() {
        if (SDK_INT >= KITKAT) {
            window.setFlags(FLAG_LAYOUT_NO_LIMITS, FLAG_LAYOUT_NO_LIMITS)
        }
    }

    fun load(imageView: ImageView) {
        Glide.with(this)
                .load(R.drawable.background_login)
                .centerCrop()
                .into(imageView)
    }

    fun goHome() {
        startActivity<MainActivity>()
        finishAffinity()
    }

    fun getPrefs(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(this)
    }
}
