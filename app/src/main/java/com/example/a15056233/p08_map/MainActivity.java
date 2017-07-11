package com.example.a15056233.p08_map;

import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);

                UiSettings ui2 = map.getUiSettings();
                ui2.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                }

                LatLng poi_GamersStore = new LatLng(35.698619, 139.773075);
                Marker cp = map.addMarker(new
                        MarkerOptions()
                        .position(poi_GamersStore)
                        .title("Akihabara gamers store")
                        .snippet("Akihabara station")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.star)));

                LatLng poi_RP = new LatLng(1.44224, 103.785733);
                Marker rp = map.addMarker(new
                        MarkerOptions()
                        .position(poi_RP)
                        .title("Republic Polytechnic")
                        .snippet("9 Woodlands Avenue 9, 738964")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                LatLng poi_UniversalStudio = new LatLng(1.254545,103.821074);
                Marker us = map.addMarker(new
                        MarkerOptions()
                        .position(poi_UniversalStudio)
                        .title("Universal Studio, Sentosa")
                        .snippet("8 Sentosa Gateway, 098269")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(MainActivity.this, marker.getTitle(), Toast.LENGTH_LONG).show();

                        return true;
                    }
                });
            }
        });

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng poi_GamersStore = new LatLng(35.698619, 139.773075);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_GamersStore,
                        15));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng poi_RP = new LatLng(1.44224, 103.785733);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_RP,
                     15));
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng poi_UniversalStudio = new LatLng(1.254545,103.821074);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_UniversalStudio,
                        15));
            }
        });
    }
}