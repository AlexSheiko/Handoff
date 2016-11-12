package com.example.handoff.signin

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.example.handoff.R
import com.example.handoff.base.BaseActivity
import com.example.handoff.base.Extensions
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.enabled
import org.jetbrains.anko.longToast
import org.jetbrains.anko.onClick
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class RegisterActivity : BaseActivity(), Extensions {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        transparentStatus()
        load(backgroundImageView)

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
                password.text.toString(),
                password.text.toString())
    }

    private fun register(user: User) {
        showLoading(true)

        val webService = ServiceGenerator().createService()
        webService.createUser(user.name, user.email, user.password, user.password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { responseBody ->
                            goHome()
                        },
                        { error ->
                            showLoading(false, error.message)
                        })
    }

    private fun showLoading(b: Boolean, error: String? = "") {
        progressBar.visibility = if (b) VISIBLE else GONE
        signUpButton.enabled = if (b) false else true
        if (!error.isNullOrBlank()) longToast(R.string.error_login_network)
    }
}