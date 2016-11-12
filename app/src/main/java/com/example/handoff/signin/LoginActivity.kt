package com.example.handoff.signin

import android.os.Bundle
import com.example.handoff.R
import com.example.handoff.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loadBackground(backgroundImageView)
        transparentNavigation()

        signInButton.onClick { signIp() }
        restoreButton.onClick { restore() }
    }

    private fun signIp() {
        goHome()
    }

    fun restore() {
        toast("Coming soon")
    }
}