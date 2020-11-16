package com.example.sunshinemedicalclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class bookingSuccess extends AppCompatActivity {
    MediaPlayer successSound = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_success);
        successSound = MediaPlayer.create(this, R.raw.whoosh);
        successSound.start();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                successSound.stop();
                finish();
            }
        };
        //Timer opening = new Timer();
        //opening.schedule(task, 3500);
    }
}