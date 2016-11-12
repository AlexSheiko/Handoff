package com.example.handoff.signin

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.example.handoff.R
import com.example.handoff.base.BaseActivity
import com.example.handoff.base.Extensions
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.onClick
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class RegisterActivity : BaseActivity(), Extensions {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        load(backgroundImageView)

        signUpButton.onClick { signUp() }
    }

    private fun signUp() {
        if (valid()) {
            val user = captureFields()
            register(user)
            goHome()
        }
    }

    private fun captureFields(): User {
        return User(
                name.text.toString(),
                email.text.toString(),
                password.text.toString(),
                password.text.toString())
    }

    private fun valid(): Boolean {
        var valid = true
        if (password.text.isBlank()) {
            valid = false
            showRequired(password)
        }

        return valid
    }

    private fun showRequired(field: EditText) {
        field.error = getString(R.string.error_required)
    }

    private fun register(user: User) {
        val webService = ServiceGenerator().createService()
        webService.createUser(user.name, user.email, user.password, user.password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { responseBody -> Log.d("Users", responseBody.string()) },
                        { error -> Log.e("Error", error.message) })
    }
}