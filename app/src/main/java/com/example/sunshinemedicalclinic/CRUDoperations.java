package com.example.sunshinemedicalclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CRUDoperations extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "CRUDoperations";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_operations);
        //Create data
        final EditText addHealthcardNo = findViewById(R.id.addHealthcardNo);
        final EditText addFName = findViewById(R.id.addFName);
        final EditText addLName = findViewById(R.id.addLName);
        final EditText addSex = findViewById(R.id.addSex);
        final EditText addPhoneNo = findViewById(R.id.addPhoneNo);
        final EditText addDOB = findViewById(R.id.addDOB);
        Button btnCreate = findViewById(R.id.btnCreate);
        //Retrieve data
        final EditText retHealthcardNo = findViewById(R.id.retHealthcardNo);
        Button btnShow = findViewById(R.id.btnShow);
        final TextView showPatient = findViewById(R.id.showText);
        //Update data
        EditText updHealthcardNo = findViewById(R.id.updHealthcardNo);
        EditText updFName = findViewById(R.id.updFName);
        EditText updLName = findViewById(R.id.updLName);
        EditText updPhoneNo = findViewById(R.id.updPhoneNo);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        //Delete data
        final EditText delHealthcardNo = findViewById(R.id.delHealthcardNo);
        Button btnDelete = findViewById(R.id.btnDelete);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int usrHealthcardNo = Integer.parseInt(addHealthcardNo.getText().toString());
                String usrFName = addFName.getText().toString();
                String usrLName = addLName.getText().toString();
                String usrSex = addSex.getText().toString();
                String usrPhoneNo = addPhoneNo.getText().toString();
                String usrDOB = addDOB.getText().toString();
                Map<String,Object> user = new HashMap<>();
                user.put("healthcard", usrHealthcardNo);
                user.put("fname", usrFName);
                user.put("lname", usrLName);
                user.put("sex", usrSex);
                user.put("phone", usrPhoneNo);
                user.put("dob", usrDOB);
                db.collection("patients").document(String.valueOf(usrHealthcardNo)).set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid){
                                Log.d(TAG, "Patient added with ID: " + usrHealthcardNo);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding patient", e);
                            }
                        });
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int usrHealthcardNo = Integer.parseInt(retHealthcardNo.getText().toString());
                db.collection("patients").document(String.valueOf(usrHealthcardNo)).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                showPatient.setText(document.getData().toString());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int usrHealthcardNo = Integer.parseInt(delHealthcardNo.getText().toString());
                db.collection("patients").document(String.valueOf(usrHealthcardNo))
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully deleted!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error deleting document", e);
                            }
                        });
            }
        });
    }
}