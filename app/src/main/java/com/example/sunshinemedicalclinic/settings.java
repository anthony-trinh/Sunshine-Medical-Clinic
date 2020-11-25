package com.example.sunshinemedicalclinic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class settings extends AppCompatActivity {


    private SharedPreferences sp ;
    private SharedPreferences.Editor spEditor ;
    private Boolean nightModeFlag ;
    private Boolean muteFlag ;
    private Button darkModeButton ;
    private Button muteSoundsButton ;
    private Button logout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final Context c = this ;

        sp = getSharedPreferences("AppSettingsPref",0) ;
        spEditor = sp.edit() ;
        nightModeFlag = sp.getBoolean("NightMode", false) ;
        darkModeButton = findViewById(R.id.darkModeButton) ;
        muteSoundsButton = findViewById(R.id.muteSoundButton) ;
        logout = findViewById(R.id.logoutButton) ;
        muteFlag = false ;


        if(nightModeFlag){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES) ;
            darkModeButton.setText("Disable Dark Mode");
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) ;
            darkModeButton.setText("Enable Dark Mode");
        }

       darkModeButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(nightModeFlag){
                   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) ;
                   spEditor.putBoolean("NightMode", false) ;
                   spEditor.apply();
                   darkModeButton.setText("Disable Dark Mode");
               }
               else{
                   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES) ;
                   spEditor.putBoolean("NightMode", true) ;
                   spEditor.apply() ;
                   darkModeButton.setText("Enable Dark Mode");
               }
           }
       });

        muteSoundsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(muteFlag){
                    spEditor.putBoolean("Sound", true) ;
                    spEditor.apply() ;
                    muteSoundsButton.setText("Mute Sounds");
                    muteFlag = false ;

                }
                else{
                    spEditor.putBoolean("Sound", false) ;
                    spEditor.apply() ;
                    muteSoundsButton.setText("Unmute Sound");
                    muteFlag = true ;
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setTitle("Logout").setMessage("Are you sure you want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(settings.this, "Logging out", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(settings.this, Login.class));
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
        });




    }
}