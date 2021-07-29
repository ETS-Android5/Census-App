package com.example.jpro;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class UserGoogleMaps extends AppCompatActivity {


    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    String refnum = "";
    String enufullname = "";
    String houseowner = "";

    Double lat;
    Double lon;

    ExtendedFloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_google_maps);

        fab = findViewById(R.id.extended_fab);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            refnum = bundle.getString("refNum");
            enufullname = bundle.getString("enufullname");
            houseowner = bundle.getString("houseowner");

//            Log.d("wele", "onCreate: "+enufullname);
//            Log.d("wele", "onCreate: "+refnum);
//            Log.d("wele", "onCreate: "+houseowner);
        }


        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapfragment);

        //initialize fused location
        client = LocationServices.getFusedLocationProviderClient(this);

        //check permission
        if (ActivityCompat.checkSelfPermission(UserGoogleMaps.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED | ActivityCompat.checkSelfPermission(UserGoogleMaps.this, Manifest.permission.ACCESS_COARSE_LOCATION) ==PackageManager.PERMISSION_GRANTED ) {
            //call location
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString("refNum", refnum);
                bundle.putString("enufullname", enufullname);
                bundle.putString("houseowner", houseowner);
                bundle.putDouble("lat", lat);
                bundle.putDouble("lon", lon);

                Intent intent = new Intent(UserGoogleMaps.this, General_Information_Form2.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED | ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            Task<Location> task = client.getLastLocation();

        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                //when success
                if (location != null){
                    //syn map
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            //initialize latln
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                            //create marker
                            MarkerOptions options = new MarkerOptions()
                                    .position(latLng)
                                    .title("Current Location")
                                    .snippet(location.getLatitude()+" " + location.getLongitude());
                            //zoom in on map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                            //add marker
                            googleMap.addMarker(options);

                            lat = location.getLatitude();
                            lon = location.getLongitude();
                        }
                    });
                } else {
                    Task<Location> task1 = client.getLastLocation();
                }
            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //when permission granted
                //call method
                getCurrentLocation();
            }
        }
    }
}