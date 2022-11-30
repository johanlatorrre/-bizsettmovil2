package com.sena.splashscreenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {
 private ImageView imgv;

    TextView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


//
//        imgv = findViewById(R.id.imageView);
//        loading = findViewById(R.id.bizsett);
//        Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
//        Animation blink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
//        imgv.startAnimation(rotate);
//        loading.startAnimation(blink);
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        };
//
//        Timer timer = new Timer();
//        timer.schedule(timerTask, 6000);
//    }

        imgv = findViewById(R.id.imageView);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animacion);
        imgv.setAnimation(animation);

        Thread splashScreenStarter = new Thread() {
            public void run() {
                try {
                    int delay = 0;
                    while (delay < 2000) {
                        sleep(150);
                        delay = delay + 100;
                    }

                    startActivity(new

                            Intent(SplashScreenActivity.this, MainActivity.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }
        };
        splashScreenStarter.start();
    }

}