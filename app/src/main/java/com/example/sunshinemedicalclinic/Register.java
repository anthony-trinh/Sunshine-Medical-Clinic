package com.example.sunshinemedicalclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
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
                            Toast.makeText(Register.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else {
                            Toast.makeText(Register.this, "Error creating user: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}