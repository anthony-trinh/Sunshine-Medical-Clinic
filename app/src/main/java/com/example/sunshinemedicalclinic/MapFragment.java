package com.example.sunshinemedicalclinic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class MapFragment extends DialogFragment implements View.OnClickListener {
    @Nullable

    public String name ;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.map_fragment,container,false) ;
        TextView t = view.findViewById(R.id.clinicNameMapFragment) ;
        Button bookButton = view.findViewById(R.id.bookAppointmentButton) ;
        Button contactButton = view.findViewById(R.id.contactButton) ;
        bookButton.setOnClickListener(this);
        contactButton.setOnClickListener(this) ;
        t.setText(name) ;
        return view ;
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.bookAppointmentButton:
                Intent i = new Intent(getContext(), BookAppointment.class) ;
                i.putExtra("selectClinic", name) ;
                startActivity(i);
        }
        switch(v.getId()){
            case R.id.contactButton:
                Intent i = new Intent(getContext(), ContactUs.class) ;
                i.putExtra("selectClinic", name) ;
                startActivity(i);
        }
    }

    public void setName(String n){
        name = n ;
    }
}
