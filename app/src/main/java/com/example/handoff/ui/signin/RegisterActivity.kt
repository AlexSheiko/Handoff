package com.example.handoff.ui.signin

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.example.handoff.R
import com.example.handoff.api.ServiceGenerator.authService
import com.example.handoff.api.ServiceGenerator.userService
import com.example.handoff.data.model.User
import com.example.handoff.data.model.helper.TokenRequest
import com.example.handoff.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.enabled
import org.jetbrains.anko.longToast
import org.jetbrains.anko.onClick
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        transparentStatusBar()
        load(backgroundImage)

        signUpButton.onClick { signUp() }
    }

    private fun signUp() {
        if (valid(password, email, phone, name)) {
            val user = captureFields()
            register(user)
        }
    }

    private fun captureFields(): User {
        return User(
                name.text.toString(),
                email.text.toString(),
                password.text.toString())
    }

    private fun register(user: User) {
        showLoading(true)

        authService.getToken(TokenRequest())
                .flatMap { token -> userService.createUser(user, bearer(token)) }
                .flatMap { body -> authService.getToken(requestFor(user)) }
                .map { token -> saveToken(token) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { goHome() },
                        { error -> showLoading(false, error.message) })
    }

    private fun showLoading(l: Boolean, error: String? = "") {
        progressBar.visibility = if (l) VISIBLE else GONE
        signUpButton.enabled = !l
        signUpButton.alpha = if (l) 0.5f else 1.0f
        if (!error.isNullOrBlank()) longToast(R.string.error_login_network_2)
    }
}