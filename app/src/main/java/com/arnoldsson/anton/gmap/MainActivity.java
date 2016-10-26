package com.arnoldsson.anton.gmap;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    MapFragment gMap;
    GoogleMap map;

    private ListView lvWeather;
    private CustomListAdapter CLA;
    private ArrayList<WeatherObject> weatherList = new ArrayList<WeatherObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gMap = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        gMap.getMapAsync(this);

        lvWeather = (ListView)findViewById(R.id.lvWeather);
        CLA = new CustomListAdapter(this, weatherList);

        weatherList.add(new WeatherObject(1, 2));
        weatherList.add(new WeatherObject(3, 4));
        weatherList.add(new WeatherObject(4,5));

        Toast.makeText(MainActivity.this, "" + CLA.getCount(), Toast.LENGTH_LONG).show();

        lvWeather.setAdapter(CLA);
        //lvWeather.setOnItemClickListener(new ListViewListener());
    }


    @Override
    public void onMapReady(final GoogleMap map) {
        this.map = map;
        map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(55.612f, 13.0f), 11, 10, 10)));
        // 55.612, 13.0 Malmö
        // 55.70, 13.20 Lund
        // 56.047073, 12.695367 Helsingborg
        // 55.839154, 13.303499 Eslöv

    }

    public void MapReady() {

    }


    private class ListViewListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Object o = lvWeather.getItemAtPosition(position);
            Object income = (Object)o;
        }
    }
}
