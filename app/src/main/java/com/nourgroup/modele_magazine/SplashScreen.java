package com.nourgroup.modele_magazine;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebIconDatabase;
import android.widget.ImageView;
import android.widget.TextView;



/**
 * Created by BlackJack on 24/03/2015.
 */
public class SplashScreen extends Activity {
    // Splash screen timer
    TextView txt;
    private static int SPLASH_TIME_OUT = 4000;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        //ImageView iv = (ImageView) findViewById(R.id.imageViews);
        txt = (TextView) findViewById(R.id.version);
        //Ion.with(iv).load("http://projetftpestgi.olympe.in/loading.gif");


        txt.setText(BuildConfig.VERSION_NAME+"");

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
