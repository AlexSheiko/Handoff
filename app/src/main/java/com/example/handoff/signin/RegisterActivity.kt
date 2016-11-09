package com.example.handoff.signin

import android.os.Bundle
import com.example.handoff.R
import com.example.handoff.base.BaseActivity
import com.example.handoff.main.MainActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.onClick
import org.jetbrains.anko.singleTop

class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        loadBackground(backgroundImageView)
        makeNavigationTransparent()

        signUpButton.onClick { signUp() }
    }

    private fun signUp() {
        makeRequest()
        navigateToMainActivity()
    }

    private fun makeRequest() {
        val loginService = ServiceGenerator().createService()
    }

    private fun navigateToMainActivity() {
        startActivity(intentFor<MainActivity>().singleTop())
        finish()
    }
}