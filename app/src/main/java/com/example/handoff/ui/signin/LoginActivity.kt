package com.example.handoff.ui.signin

import android.os.Bundle
import android.view.View
import com.example.handoff.R
import com.example.handoff.api.ServiceGenerator.authService
import com.example.handoff.data.model.User
import com.example.handoff.ui.base.BaseActivity
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

        transparentStatusBar()
        load(backgroundImage)

        signInButton.onClick { signIn() }
        restoreButton.onClick { restore() }
    }

    private fun signIn() {
        if (valid(email, password)) {
            val user = captureFields()
            login(user)
        }
    }

    private fun login(user: User) {
        showLoading(true)

        authService.getToken(requestFor(user))
                .map { token -> saveToken(token) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { token -> goHome() },
                        { error -> showLoading(false, error.message) })
    }

    private fun showLoading(l: Boolean, error: String? = "") {
        progressBar.visibility = if (l) View.VISIBLE else View.GONE
        signInButton.enabled = !l
        signInButton.alpha = if (l) 0.5f else 1.0f
        if (!error.isNullOrBlank()) longToast(R.string.error_login_network_2)
    }

    fun restore() {
        toast("Coming soon")
    }

    private fun captureFields(): User {
        return User(
                email = email.text.toString(),
                password = password.text.toString())
    }
}