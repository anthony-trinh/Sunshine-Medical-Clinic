package com.example.sunshinemedicalclinic;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import com.google.firebase.firestore.auth.User;
import com.google.rpc.context.AttributeContext;

import java.lang.ref.Reference;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MyAccount extends AppCompatActivity {
    DrawerLayout drawerLayout;
    String TAG = "MyAccount"; String usrEmail;
    TextView healthcardNum, name, dob, email, phone, gender, pastAppt, futAppt ;
    private FirebaseAuth fbAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db  = FirebaseFirestore.getInstance();
    private CollectionReference ref ;
    final FirebaseUser user = fbAuth.getCurrentUser() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        drawerLayout = findViewById(R.id.drawer_layout);
        healthcardNum = findViewById(R.id.healthcardBox) ;
        name = findViewById(R.id.nameBox) ;
        dob = findViewById(R.id.dobBox) ;
        email = findViewById(R.id.emailBox) ;
        phone = findViewById(R.id.phoneBox) ;
        gender = findViewById(R.id.genderBox) ;
        pastAppt = findViewById(R.id.txtPAppt);
        futAppt = findViewById(R.id.txtFAppt);
        pastAppt.setText(""); futAppt.setText("");
        usrEmail = user.getEmail();
        final DateFormat date = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        db.collection("patients").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "task#1 successful");
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        if (document.exists()) {
                            if ((document.get("email")).equals(usrEmail)) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.get("healthcard"));
                                healthcardNum.setText(document.get("healthcard").toString());
                                String fName = document.get("fname").toString();
                                String lName = document.get("lname").toString();
                                name.setText(fName + " " + lName);
                                dob.setText(document.get("dob").toString());
                                email.setText(document.get("email").toString());
                                phone.setText(document.get("phone").toString());
                                gender.setText(document.get("sex").toString());
                            }
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        db.collection("appointment").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Date currentDate=java.util.Calendar.getInstance().getTime();
                Log.d(TAG, "is successful: " + currentDate);
                if (task.isSuccessful()) {
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        if (document.exists()) {
                            if ((document.get("email")).equals(usrEmail)) {
                                Log.d(TAG, "found user");
                                try {
                                    if (currentDate.before(date.parse(String.valueOf(document.get("date"))))) {
                                        Log.d(TAG, "found date future");
                                        futAppt.append(document.get("type")+": ");
                                        futAppt.append(document.get("date").toString());
                                        futAppt.append("\n");
                                    } else {
                                        Log.d(TAG, "found date past");
                                        pastAppt.append(document.get("type")+": ");
                                        pastAppt.append(document.get("date").toString());
                                        pastAppt.append("\n");
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
    public void ClickMenu (View view){
        MainActivity.closeopenDrawer(drawerLayout);
    }
    public void ClickHome (View view){
        startActivity(new Intent(this, MainActivity.class));
    }
    public void ClickBook (View view) { startActivity(new Intent(this, BookAppointment.class)); }
    public void ClickLocations (View view){ startActivity(new Intent(this, FindLocations.class)); }
    public void ClickMyAccount (View view){ recreate(); }
    public void ClickContactUs(View view){
        startActivity(new Intent(this, ContactUs.class));
    }
    public void ClickSettings (View view){
        startActivity(new Intent(this, settings.class)) ;
    }
}