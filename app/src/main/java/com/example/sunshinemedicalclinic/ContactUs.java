package com.example.sunshinemedicalclinic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;

public class ContactUs extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DrawerLayout drawerLayout;
    int flag = 0 ;
    int index ;
    String clinicName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        drawerLayout = findViewById(R.id.drawer_layout);
        Spinner clinicSpinner = findViewById(R.id.clinicSpinner) ;
        clinicSpinner.setSelection(0) ;
        ArrayAdapter<CharSequence> adapterNames = ArrayAdapter.createFromResource(this, R.array.clinicNames, android.R.layout.simple_spinner_item) ;
        final String[] clinicPhones = getResources().getStringArray(R.array.clinicPhones) ;
        final String[] clinicEmails = getResources().getStringArray(R.array.clinicEmails) ;
        clinicSpinner.setAdapter(adapterNames) ;
        clinicSpinner.setOnItemSelectedListener(this) ;
        Button callButton = findViewById(R.id.callButton) ;
        Button emailButton = findViewById(R.id.emailButton) ;
        final ArrayList<String> clinicNames = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.clinicNamesAlt))) ;
        clinicName = getIntent().getStringExtra("selectClinic") ;
        index = clinicNames.indexOf(clinicName) ;
        clinicSpinner.setSelection(index) ;

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

    public void ClickMenu (View view){
        MainActivity.closeopenDrawer(drawerLayout);
    }
    public void ClickHome (View view){
        startActivity(new Intent(this, MainActivity.class));
    }
    public void ClickBook (View view){
        startActivity(new Intent(this, BookAppointment.class));
    }
    public void ClickLocations (View view){
        startActivity(new Intent(this, FindLocations.class));
    }
    public void ClickMyAccount (View view){
        startActivity(new Intent(this, MyAccount.class));
    }
    public void ClickContactUs(View view){ recreate(); }
    public void ClickSettings (View view){
        startActivity(new Intent(this, settings.class)) ;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView txtPhone = findViewById(R.id.txtPhoneNo);
        TextView txtEmail = findViewById(R.id.logEmail);

        String text = parent.getItemAtPosition(position).toString() ;
        index = position ;
        flag = 1 ;
        txtPhone.setText(getResources().getStringArray(R.array.clinicPhones)[index].substring(4));
        txtEmail.setText(getResources().getStringArray(R.array.clinicEmails)[index]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}