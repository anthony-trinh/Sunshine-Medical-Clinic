package com.example.sunshinemedicalclinic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class FindLocations extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    // These arraylists will hold the coordinates and names of clinics
    ArrayList<LatLng> locationsXY = new ArrayList<LatLng>() ;
    ArrayList<String> locationsName = new ArrayList<String>() ;

    // Initializing clinics
    LatLng dundas_hurontario = new LatLng( 43.581343,-79.61709 );
    LatLng bloor_yonge = new LatLng( 43.670786 ,-79.385687) ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_locations);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //add coordinates and names to the array lists
        locationsXY.add(dundas_hurontario);
        locationsXY.add(bloor_yonge) ;

        locationsName.add("Dundas-Hurontario Clinic") ;
        locationsName.add("Bloor-Christie Clinic") ;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // loop for adding markers / setting names
        for(int i=0 ; i<locationsXY.size() ; i++){
            for(int j=0 ; j<locationsName.size() ; j++){
                mMap.addMarker(new MarkerOptions().position(locationsXY.get(i)).title(String.valueOf(locationsName.get(i))));
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationsXY.get(i))) ;
        }

        LatLngBounds ontarioBounds = new LatLngBounds(
                new LatLng(43.159660, -80.265443), //SW bound
                new LatLng(44.088140, -78.862274) //NE bound
        ) ;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ontarioBounds.getCenter(), 7));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String clinicName = marker.getTitle() ;
                MapFragment mapFragment = new MapFragment() ;
                mapFragment.show(getSupportFragmentManager(), "myFragment") ;
                mapFragment.setName(clinicName.replace(" Clinic","")) ;
                return true ;
            }
        });
    }
}