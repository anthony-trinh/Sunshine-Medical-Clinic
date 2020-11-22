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
        successSound = MediaPlayer.create(this, R.raw.cheer);
        successSound.start();

    }
}