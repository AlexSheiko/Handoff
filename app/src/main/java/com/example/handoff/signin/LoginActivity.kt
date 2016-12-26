package com.example.handoff.signin

import android.os.Bundle
import android.view.View
import com.example.handoff.R
import com.example.handoff.api.ServiceGenerator.Companion.authService
import com.example.handoff.api.model.TokenRequest
import com.example.handoff.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.enabled
import org.jetbrains.anko.longToast
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        transparentStatus()
        load(backgroundImageView)

        signInButton.onClick { signIn() }
        restoreButton.onClick { restore() }
    }

    private fun signIn() {
        showLoading(true)

        authService.getToken(TokenRequest())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { token -> goHome() },
                        { error -> showLoading(false, error.message) })
    }

    private fun showLoading(l: Boolean, error: String? = "") {
        progressBar.visibility = if (l) View.VISIBLE else View.GONE
        signInButton.enabled = if (l) false else true
        signInButton.alpha = if (l) 0.5f else 1.0f
        if (!error.isNullOrBlank()) longToast(R.string.error_login_network)
    }

    fun restore() {
        toast("Coming soon")
    }
}