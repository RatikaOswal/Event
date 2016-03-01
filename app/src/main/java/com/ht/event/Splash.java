package com.ht.event;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;



public class Splash extends AppCompatActivity {

    private static boolean splashLoaded = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!splashLoaded) {
            setContentView(R.layout.activity_splash);
            int secondsDelayed = 1;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    startActivity(new Intent(Splash.this, Main.class));
                    finish();
                }
            }, secondsDelayed * 500);
            splashLoaded = true;

        }
        else {
            Intent goToMainActivity = new Intent(Splash.this, Main.class);
            startActivity(goToMainActivity);
            finish();
        }
    }




}
