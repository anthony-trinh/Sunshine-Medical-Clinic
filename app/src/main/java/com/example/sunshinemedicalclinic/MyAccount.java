package com.example.sunshinemedicalclinic;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAccount extends AppCompatActivity {
    String TAG = "MyAccount";
    TextView healthcardNum, name, dob, email, phone, gender ;
    private FirebaseAuth fbAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db  = FirebaseFirestore.getInstance();
    private CollectionReference ref ;
    final FirebaseUser user = fbAuth.getCurrentUser() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        Log.d(TAG, "created");
        healthcardNum = findViewById(R.id.healthcardBox) ;
        name = findViewById(R.id.nameBox) ;
        dob = findViewById(R.id.dobBox) ;
        email = findViewById(R.id.emailBox) ;
        phone = findViewById(R.id.phoneBox) ;
        gender = findViewById(R.id.genderBox) ;
        Log.d(TAG, "assigned");
        //email.setText(user.getEmail());
        Log.d(TAG, "user");
        Log.d(TAG, "instances");
        db.collection("patients").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.d(TAG, "attempt");
                if (task.isSuccessful()) {
                    for(QueryDocumentSnapshot document : task.getResult())
                        if (document.exists()) {
                            if(document.get("email").equals(user.getEmail())) {
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
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


    }
}