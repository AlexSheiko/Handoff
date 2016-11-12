package com.example.handoff.signin

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.text.Html
import android.view.View
import android.widget.Toast
import com.example.handoff.R
import com.example.handoff.base.BaseActivity
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        load(backgroundImageView)

        highlightLogin()
    }

    private fun highlightLogin() {
        signInButton.text = Html.fromHtml("Already have an account? " +
                "<font color='#689F38'><b>Log\u00A0In</b></font>")
    }

    fun onClickSignUp(view: View) {
        val logoView = findViewById(R.id.logoView)
        val signUpButton = findViewById(R.id.signUpButton)
        val intent = Intent(this, RegisterActivity::class.java)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                Pair(logoView, "logo"),
                Pair(signUpButton, "button"))
        startActivity(intent, options.toBundle())
    }

    fun onClickFacebook(view: View) {
        Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show()
    }

    fun onClickSignIn(view: View) {
        val logoView = findViewById(R.id.logoView)
        val signInButton = findViewById(R.id.signInButton)
        val intent = Intent(this, LoginActivity::class.java)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                Pair(logoView, "logo"))
        startActivity(intent, options.toBundle())
    }
}
