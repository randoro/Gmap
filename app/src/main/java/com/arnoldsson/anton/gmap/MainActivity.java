package com.arnoldsson.anton.gmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    MapFragment gMap;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gMap = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        gMap.getMapAsync(this);
    }


    @Override
    public void onMapReady(final GoogleMap map) {
        this.map = map;
        map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(10.0f, 10.0f), 10, 10, 10)));
    }

    public void MapReady() {

    }


}
