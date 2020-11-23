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

    private TextView healthcardNum, name, dob, email, phone, gender ;
    private FirebaseAuth fbAuth ;
    private FirebaseFirestore db ;
    private CollectionReference ref ;
    final FirebaseUser user = fbAuth.getInstance().getCurrentUser() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        healthcardNum = findViewById(R.id.healthcardBox) ;
        name = findViewById(R.id.nameBox) ;
        dob = findViewById(R.id.dobBox) ;
        email = findViewById(R.id.emailBox) ;
        phone = findViewById(R.id.phoneBox) ;
        gender = findViewById(R.id.genderBox) ;

        email.setText(user.getEmail());


        fbAuth = FirebaseAuth.getInstance() ;
        db = FirebaseFirestore.getInstance() ;

        




    }
}