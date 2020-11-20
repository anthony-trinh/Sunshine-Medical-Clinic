package com.example.sunshinemedicalclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {
    MediaPlayer openingSound = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        openingSound = MediaPlayer.create(this, R.raw.whoosh);
        openingSound.start();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                openingSound.stop();
                finish();
                startActivity(new Intent(SplashScreen.this,Register.class));
            }
        };

        Timer opening = new Timer();
        opening.schedule(task, 3500);
    }
}