package com.example.handoff.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.handoff.R
import com.example.handoff.main.MainActivity
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

open class BaseActivity : AppCompatActivity() {

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
    }

    fun load(imageView: ImageView) {
        Glide.with(this)
                .load(R.drawable.background_login)
                .centerCrop()
                .into(imageView)
    }

    fun goHome() {
        startActivity(intentFor<MainActivity>().singleTop())
        finish()
    }
}
