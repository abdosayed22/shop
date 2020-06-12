package com.example.shop;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

import androidx.fragment.app.FragmentActivity;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    String LAT , LONGT ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        LAT = getIntent().getStringExtra("lat") ;
        LONGT =getIntent().getStringExtra("longt") ;
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);



        FloatingActionButton fabe = findViewById(R.id.fab);
        fabe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Location location= null;
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},10);
                        }
                    }
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                if(location != null){
                    Toast.makeText(MapsActivity.this, "fsdfsddfsdfsdfsdf", Toast.LENGTH_SHORT).show();
                    LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                    mMap.clear();
                    Geocoder geocoder= new Geocoder(MapsActivity.this);
                    try {
                        Address address = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1).get(0);
                        String addressLine = "";
                        for(int i = 0; i <= address.getMaxAddressLineIndex(); i++){
                            addressLine += address.getAddressLine(i) +",";
                        }
                        mMap.addMarker(new MarkerOptions().position(latLng).title("Current Location").snippet(addressLine)).setDraggable(true);
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,20));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    LatLng sydney = new LatLng(Double.parseDouble(LAT),Double.parseDouble(LONGT));
                    mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Current Location"));
                }
            }
        });

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

          LatLng sydney = new LatLng(Double.parseDouble(LAT),Double.parseDouble(LONGT));
          mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Customer"));
         mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,20));
         mMap.setBuildingsEnabled(true);
          mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                Geocoder geocoder= new Geocoder(MapsActivity.this);
                try {
                    Address address = geocoder.getFromLocation(marker.getPosition().latitude,marker.getPosition().longitude,1).get(0);
                    String addressLine = "";
                    for(int i = 0;i <= address.getMaxAddressLineIndex(); i++){
                        addressLine += address.getAddressLine(i) +",";
                    }
                    marker.setTitle("Current Location");
                    marker.setSnippet(addressLine);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
