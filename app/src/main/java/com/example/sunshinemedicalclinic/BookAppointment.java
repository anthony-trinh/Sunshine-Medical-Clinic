package com.example.sunshinemedicalclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

public class BookAppointment extends AppCompatActivity {
    MediaPlayer failSound = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        failSound = MediaPlayer.create(this, R.raw.xpshutdown);
        Button dateSelect = findViewById(R.id.btnDate);
        Button confirm = findViewById(R.id.btnConfirm);
        final TextView dateSelected = findViewById(R.id.txtDate);
        final DateFormat date = DateFormat.getDateInstance();
        final Calendar c = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dateSelected.setText(""+date.format(c.getTime()));
            }
        };
        dateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(BookAppointment.this, d, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentDate=java.util.Calendar.getInstance().getTime();
                if(currentDate.before(c.getTime()))
                {
                    startActivity(new Intent(BookAppointment.this, bookingSuccess.class));
                }
                else{
                    failSound.stop();
                    failSound = MediaPlayer.create(BookAppointment.this, R.raw.xpshutdown);
                    failSound.start();
                    Toast.makeText(getApplicationContext(),"Invalid Date: Select another date.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}