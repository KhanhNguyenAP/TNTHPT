package com.thud.thpt_dh.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.thud.thpt_dh.R;
import com.thud.thpt_dh.utils.Preference;

/**
 * Created by KhanhNguyen on 5/11/2016.
 * Contact phone: 0975 282 877
 */
public class SplashActivity extends Activity{
    private Preference preference;

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        //get share preference
        preference = new Preference(SplashActivity.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
