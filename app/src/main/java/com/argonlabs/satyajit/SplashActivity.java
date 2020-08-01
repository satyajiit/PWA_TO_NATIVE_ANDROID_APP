package com.argonlabs.satyajit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.messaging.FirebaseMessaging;

public class SplashActivity extends AppCompatActivity {

    final long SPLASH_DELAY = 2000; //2 milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        runMainApp();

        //RegisterToTopic for FCM
        FirebaseMessaging.getInstance().subscribeToTopic("all");

    }

    private void runMainApp() {

        new Handler().postDelayed(() -> {

                    startActivity(new Intent(SplashActivity.this, com.google.androidbrowserhelper.trusted.LauncherActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    finish();
                    overridePendingTransition(R.anim.anim_right_in, R.anim.anim_left_out);
                }, SPLASH_DELAY);

    }

}