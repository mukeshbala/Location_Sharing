package com.example.orgware.currentlocationshareapp;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.share_location)
    Button shareLocation;
//    @BindView(R.id.txt)
//    TextView txt;

    private SupportMapFragment supportMapFragment;
    private GoogleMap googleMap;
    MarkerOptions markerOptions;
    private double mLat;
    private double mLng;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
    }

    @OnClick(R.id.share_location)
    public void onClick() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        String locationInfo = "http://maps.google.com/maps?q=" + lastLocation.getLatitude() + "," +lastLocation.getLongitude() + "&iwloc=A";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse(locationInfo));
        startActivity(intent);
//        txt.setText(locationInfo+"\n"+mLng+"  :::  "+mLat);

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
//        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//                 markerOptions =new MarkerOptions();
//                markerOptions.position(latLng);
//                mLat =latLng.latitude;
//                mLng =latLng.longitude;
//
//                markerOptions.title(mLat+"  :  "+mLng);
//                googleMap.clear();
//                googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//                googleMap.addMarker(markerOptions);
//            }
//        });
        googleMap.setMyLocationEnabled(true);

    }
}
