package com.example.handoff.signin

import android.os.Bundle
import com.example.handoff.R
import com.example.handoff.base.BaseActivity
import com.example.handoff.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.onClick
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loadBackground(backgroundImageView)
        makeNavigationTransparent()

        signInButton.onClick { signIp() }
        restoreButton.onClick { restorePassword() }
    }

    private fun signIp() {
        navigateToMainActivity()
    }

    private fun navigateToMainActivity() {
        startActivity(intentFor<MainActivity>().singleTop())
        finish()
    }

    fun restorePassword() {
        toast("Coming soon")
    }
}