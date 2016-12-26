package com.example.handoff.signin

import android.os.Bundle
import com.example.handoff.R
import com.example.handoff.api.ServiceGenerator
import com.example.handoff.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        transparentStatus()
        load(backgroundImageView)

        signInButton.onClick { signUp() }
        restoreButton.onClick { restore() }
    }

    private fun signUp() {
        val service = ServiceGenerator.createService()
        goHome()
    }

    fun restore() {
        toast("Coming soon")
    }
}