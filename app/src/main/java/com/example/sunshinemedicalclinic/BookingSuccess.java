package com.example.sunshinemedicalclinic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class BookingSuccess extends DialogFragment implements View.OnClickListener {

    private String clinic ;
    private String time ;
    private String type ;
    private String address ;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState) ;
        View view = inflater.inflate(R.layout.activity_booking_success,container,false) ;
        TextView bookingInfo = view.findViewById(R.id.appointmentInfo) ;
        Button returnButton = view.findViewById(R.id.returnHomeButton) ;
        Button calendarButton = view.findViewById(R.id.calendarButton) ;
        returnButton.setOnClickListener(this) ;
        calendarButton.setOnClickListener(this) ;
        this.setAddress();
        bookingInfo.setText("Date: " + time + "\nLocation: " + clinic);
        return view ;
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.returnHomeButton:
                Intent i = new Intent(getContext(), MainActivity.class) ;
                startActivity(i) ;
        }
        switch(v.getId()){
            case R.id.calendarButton:
                Calendar event = Calendar.getInstance() ;
                Intent i = new Intent(Intent.ACTION_INSERT) ;
                i.setData(CalendarContract.Events.CONTENT_URI) ;
                i.putExtra(CalendarContract.Events.TITLE,"Sunshine Medical Appointment") ;
                i.putExtra(CalendarContract.Events.DESCRIPTION, type) ;
                i.putExtra(CalendarContract.Events.EVENT_LOCATION, address) ;
                //i.putExtra(CalendarContract.Events.DTSTART, "1:00pm") ;
                startActivity(i);
        }
    }

    public void setTime(String t){
        time = t ;
    }

    public void setClinic(String c){
        clinic = c ;
    }

    public void setType(String t){
        type = t ;
    }

    public void setAddress(){
        ArrayList<String> addresses = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.clinicAddresses))) ;
        ArrayList<String> names = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.clinicNames))) ;
        int index = names.indexOf(clinic) ;
        address = addresses.get(index) ;
    }
}