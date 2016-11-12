package com.example.handoff.signin

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import com.example.handoff.R
import com.example.handoff.base.BaseActivity
import kotlinx.android.synthetic.main.activity_welcome.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class WelcomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        transparentStatus()
        load(backgroundImageView)

        highlightLogin()
    }

    private fun highlightLogin() {
        signInButton.text = Html.fromHtml("Already have an account? " +
                "<font color='#689F38'><b>Log\u00A0In</b></font>")
    }

    fun onClickSignUp(view: View) {
        val intent = intentFor<RegisterActivity>()
//        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
//                *arrayOf(Pair(logoView, "logo"),
//                Pair(signUpButton, "button")))
//        startActivity(intent, options.toBundle())
        startActivity(intent)
    }

    fun onClickFacebook(view: View) {
        toast("Coming soon")
    }

    fun onClickSignIn(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
//        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
//                Pair(logoView, "logo"))
//        startActivity(intent, options.toBundle())
        startActivity(intent)
    }
}
