package com.test.ln.postapitask.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.test.ln.postapitask.R;

public class ResLocationActivity extends AppCompatActivity {


    SupportMapFragment map;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_location);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent=getIntent();

        double lat= Double.parseDouble(intent.getStringExtra("lat"));
        double lng=Double.parseDouble(intent.getStringExtra("lng"));
        String name=intent.getStringExtra("name");
        LatLng latLng=new LatLng(lat,lng);

        map=((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map));
        googleMap=map.getMap();

        googleMap.addMarker(new MarkerOptions().position(latLng).snippet(name));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
    }
}
