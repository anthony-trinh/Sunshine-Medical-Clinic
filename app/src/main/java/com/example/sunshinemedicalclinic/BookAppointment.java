package com.example.sunshinemedicalclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BookAppointment extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private FirebaseAuth fbAuth = FirebaseAuth.getInstance();
    final FirebaseUser user = fbAuth.getCurrentUser() ;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "BookAppointment";
    String clinicName;
    int index, healthcard;
    MediaPlayer failSound = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        drawerLayout = findViewById(R.id.drawer_layout);
        final Spinner clinicSpinner = findViewById(R.id.clinicSpinner) ;
        ArrayAdapter<CharSequence> adapterNames = ArrayAdapter.createFromResource(this, R.array.clinicNames, android.R.layout.simple_spinner_item) ;
        clinicSpinner.setAdapter(adapterNames) ;
        final ArrayList<String> clinicNames = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.clinicNamesAlt))) ;
        clinicName = getIntent().getStringExtra("selectClinic") ;
        index = clinicNames.indexOf(clinicName) ;
        clinicSpinner.setSelection(index) ;


        failSound = MediaPlayer.create(this, R.raw.xpshutdown);
        Button dateSelect = findViewById(R.id.btnDate);
        Button confirm = findViewById(R.id.btnConfirm);
        final TextView dateSelected = findViewById(R.id.txtDate);
        //final EditText healthcard = findViewById(R.id.edtHealthcard);
        final DateFormat date = DateFormat.getDateInstance();
        final Calendar c = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dateSelected.setText(""+date.format(c.getTime()));
            }
        };

        final RadioButton book = findViewById(R.id.rdBook) ;
        final RadioButton covidTest = findViewById(R.id.rdTest) ;

        dateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(BookAppointment.this, d, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentDate=java.util.Calendar.getInstance().getTime();
                if(currentDate.before(c.getTime()))
                {
                    Map<String, Object> appointment = new HashMap<>();
                    appointment.put("clinic", clinicSpinner.getSelectedItem().toString());
                    appointment.put("email", user.getEmail());
                    appointment.put("date", date.format(c.getTime()));
                    if (book.isChecked()) {
                        appointment.put("type", "General Appointment");
                    }
                    if (covidTest.isChecked()) {
                        appointment.put("type", "Covid-19 Test");
                    }
                    db.collection("appointment").add(appointment)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    String appointmentID = documentReference.getId();
                                    Log.d(TAG, "Appointment booked with ID: " + appointmentID);
                                    BookingSuccess bookingSuccess = new BookingSuccess();
                                    bookingSuccess.setClinic(clinicSpinner.getSelectedItem().toString());
                                    bookingSuccess.setTime(date.format(c.getTime()));
                                    if (book.isChecked()) {
                                        bookingSuccess.setType("General appointment at " + clinicSpinner.getSelectedItem().toString());
                                    }
                                    if (covidTest.isChecked()) {
                                        bookingSuccess.setType("Booking test at " + clinicSpinner.getSelectedItem().toString());
                                    }
                                    bookingSuccess.show(getSupportFragmentManager(), "fragment");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding patient", e);
                                }
                            });
                }
                else{
                    failSound.stop();
                    failSound = MediaPlayer.create(BookAppointment.this, R.raw.xpshutdown);
                    failSound.start();
                    Toast.makeText(getApplicationContext(),"Invalid Date: Select another date.", Toast.LENGTH_SHORT).show();
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
    public void ClickBook (View view) { recreate(); }
    public void ClickLocations (View view){ startActivity(new Intent(this, FindLocations.class)); }
    public void ClickMyAccount (View view){
        startActivity(new Intent(this, MyAccount.class));
    }
    public void ClickContactUs(View view){
        startActivity(new Intent(this, ContactUs.class));
    }
    public void ClickLogout (View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout").setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(BookAppointment.this, "Logging out", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(BookAppointment.this, Login.class));
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    public void setName(String name){
        clinicName = name ;
    }
}