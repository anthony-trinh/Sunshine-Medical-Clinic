package com.example.sunshinemedicalclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "Register";
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText healthcardNo = findViewById(R.id.regHealthcardNo);
        final EditText fName = findViewById(R.id.regFName);
        final EditText lName = findViewById(R.id.regLName);
        final EditText sex = findViewById(R.id.regSex);
        final EditText phoneNo = findViewById(R.id.regPhoneNo);
        final EditText dob = findViewById(R.id.regDOB);
        final EditText email = findViewById(R.id.regEmail);
        final EditText password = findViewById(R.id.regPswd);
        TextView login = findViewById(R.id.txtLogin);
        Button register = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usrEmail = email.getText().toString().trim();
                String usrPassword = password.getText().toString().trim();

                if(TextUtils.isEmpty(usrEmail)){
                    email.setError("Email is required.");
                    return;
                }
                if(TextUtils.isEmpty(usrPassword)){
                    password.setError("Password is required.");
                    return;
                }
                if(usrPassword.length() < 6) {
                    password.setError("Password must be at least 6 characters long.");
                    return;
                }
                mAuth.createUserWithEmailAndPassword(usrEmail,usrPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            final int usrHealthcardNo = Integer.parseInt(healthcardNo.getText().toString());
                            String usrFName = fName.getText().toString();
                            String usrLName = lName.getText().toString();
                            String usrSex = sex.getText().toString();
                            String usrPhoneNo = phoneNo.getText().toString();
                            String usrDOB = dob.getText().toString();
                            String usrEmail = email.getText().toString();
                            Map<String,Object> user = new HashMap<>();
                            Log.d(TAG, "putting user info");
                            user.put("healthcard", usrHealthcardNo);
                            user.put("fname", usrFName);
                            user.put("lname", usrLName);
                            user.put("sex", usrSex);
                            user.put("phone", usrPhoneNo);
                            user.put("dob", usrDOB);
                            user.put("email", usrEmail);
                            Log.d(TAG, "adding patient to database");
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
                            Toast.makeText(Register.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else {
                            Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}