package com.arnoldsson.anton.gmap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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

public class MainActivity extends Activity {

//    MapFragment gMap;
//    GoogleMap map;

    private ListView lvWeather;
    private CustomListAdapter CLA;
    private ArrayList<WeatherObject> weatherList = new ArrayList<WeatherObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        gMap = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
//        gMap.getMapAsync(this);

        lvWeather = (ListView)findViewById(R.id.lvWeather);
        CLA = new CustomListAdapter(this, weatherList);

        weatherList.add(new WeatherObject(City.Malmö, 5, -2));
        weatherList.add(new WeatherObject(City.Lund, 3, -4));
        weatherList.add(new WeatherObject(City.Helsingborg, 14,15));
        weatherList.add(new WeatherObject(City.Eslöv, 14,15));

        Toast.makeText(MainActivity.this, "" + CLA.getCount(), Toast.LENGTH_LONG).show();

        lvWeather.setAdapter(CLA);
        lvWeather.setOnItemClickListener(new ListViewListener());
    }




    public void MapReady() {

    }


    private class ListViewListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Object o = lvWeather.getItemAtPosition(position);
            Object income = (Object)o;

            Log.println(Log.INFO, "clicked", Integer.toString(position));
        }
    }
}
