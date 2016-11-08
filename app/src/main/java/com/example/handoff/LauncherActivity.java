package com.example.handoff;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class LauncherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        ImageView imageView = (ImageView) findViewById(R.id.backgroundImageView);
        Glide.with(this).load(R.drawable.background_login).centerCrop().into(imageView);

        highlightSignInLabel();
    }

    private void highlightSignInLabel() {
        Button signInButton = (Button) findViewById(R.id.signInButton);
        signInButton.setText(Html.fromHtml("Already have an account? <font color='#689F38'><b>Log In</b></font>"));
    }

    public void onClickSignUp(View view) {
        View logoView = findViewById(R.id.logoView);
        View signUpButton = findViewById(R.id.signUpButton);
        Intent intent = new Intent(this, RegisterActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this,
                        new Pair<>(logoView, "logo"),
                        new Pair<>(signUpButton, "button"));
        startActivity(intent, options.toBundle());
    }
}
