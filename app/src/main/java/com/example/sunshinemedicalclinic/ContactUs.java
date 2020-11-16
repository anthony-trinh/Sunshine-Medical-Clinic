package com.example.sunshinemedicalclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;

public class ContactUs extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int flag = 0 ;
    int index ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        Spinner clinicSpinner = findViewById(R.id.clinicSpinner) ;
        clinicSpinner.setSelection(0) ;
        ArrayAdapter<CharSequence> adapterNames = ArrayAdapter.createFromResource(this, R.array.clinicNames, android.R.layout.simple_spinner_item) ;
        final String[] clinicPhones = getResources().getStringArray(R.array.clinicPhones) ;
        final String[] clinicEmails = getResources().getStringArray(R.array.clinicEmails) ;
        clinicSpinner.setAdapter(adapterNames) ;
        clinicSpinner.setOnItemSelectedListener(this) ;
        Button callButton = findViewById(R.id.callButton) ;
        Button emailButton = findViewById(R.id.emailButton) ;


        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse(clinicPhones[index]));
                startActivity(phoneIntent);

            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND) ;
                    emailIntent.setType("text/plain") ;
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{clinicEmails[index]}) ;
                    startActivity(emailIntent) ;
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView txtPhone = findViewById(R.id.txtPhoneNo);
        TextView txtEmail = findViewById(R.id.txtEmail);

        String text = parent.getItemAtPosition(position).toString() ;
        index = position ;
        flag = 1 ;
        txtPhone.setText("Phone: " + (getResources().getStringArray(R.array.clinicPhones)[index].substring(4)));
        txtEmail.setText("Email: " + getResources().getStringArray(R.array.clinicEmails)[index]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}