package com.arnoldsson.anton.gmap;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    MapFragment gMap;
//    GoogleMap map;

    public static final String KEY_EXTRA = "com.arnoldsson.anton.gmap.KEY_ITEMS";

    private ListView lvWeather;
    List<MyTask> tasks;
    Spinner spinner;
    ArrayAdapter<String> spinnerAdapter;
    List<AccuTemp> temps;
    private CustomListAdapter CLA;
    private ArrayList<WeatherObject> weatherList = new ArrayList<WeatherObject>();
    public static float lat;
    public static float lng;

    public static String GETMALMO = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/314779?apikey=5JuFyCguaUNELRxwcmUAZUyd0CyAabkg&language=en-us&details=true&metric=true";
//    public static String GETLUND = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/314779?apikey=5JuFyCguaUNELRxwcmUAZUyd0CyAabkg&language=en-us&details=true&metric=true";
    public static String GETHELSINGBORG = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/314777?apikey=5JuFyCguaUNELRxwcmUAZUyd0CyAabkg&language=en-us&details=true&metric=true";
    public static String GETESLOV = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/309562?apikey=5JuFyCguaUNELRxwcmUAZUyd0CyAabkg&language=en-us&details=true&metric=true";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lat = 55.60f;
        lng = 12.95f;

        tasks = new ArrayList<>();

        spinner = (Spinner)findViewById(R.id.spinner);
        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new SpinnerListener());
        lvWeather = (ListView)findViewById(R.id.lvWeather);


        spinnerAdapter.add("Select cities/city");
        spinnerAdapter.add("All Cities");
        spinnerAdapter.add("Malmö");
//        spinnerAdapter.add("Lund");
        spinnerAdapter.add("Helsingborg");
        spinnerAdapter.add("Eslöv");



        CLA = new CustomListAdapter(this, weatherList);

//        weatherList.add(new WeatherObject(City.Malmö, 5, -2, new int[]{0, 2, -5, 3, 2, 5, 3}, new int[]{1, 4, -2, -1, 3, 4, 2}));
//        weatherList.add(new WeatherObject(City.Lund, 3, -4, new int[]{0, 2, -5, 3, 2, 5, 3}, new int[]{1, 4, -2, -1, 3, 4, 2}));
//        weatherList.add(new WeatherObject(City.Helsingborg, 4, -4, new int[]{0, 2, -5, 3, 2, 5, 3}, new int[]{1, 4, -2, -1, 3, 4, 2}));
//        weatherList.add(new WeatherObject(City.Eslöv, 14,15, new int[]{0, 2, -5, 3, 2, 5, 3}, new int[]{1, 4, -2, -1, 3, 4, 2}));


        lvWeather.setAdapter(CLA);
        lvWeather.setOnItemClickListener(new ListViewListener());


    }

    private class SpinnerListener implements android.widget.AdapterView.OnItemSelectedListener {

        boolean init = false;

        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            Object o = spinner.getItemAtPosition(position);
            Object income = (Object)o;

            if(!init) {
                init = true;
                return;
            }

            CLA.clear();

            if (isOnline()) {

                switch (position) {
                    case 0:
                        break;
                    case 1:
                        requestData(GETMALMO, 0);
//                        requestData(GETLUND, 1);
                        requestData(GETHELSINGBORG, 1);
                        requestData(GETESLOV, 2);
                        break;
                    case 2:
                        requestData(GETMALMO, 0);
                        break;
                    case 3:
                        requestData(GETHELSINGBORG, 1);
                        break;
                    case 4:
                        requestData(GETESLOV, 2);
                        break;
                    default:
                        break;
                }


            } else
            {
                Toast.makeText(MainActivity.this, "Network isn't available", Toast.LENGTH_SHORT).show();
            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
            // your code here
        }
    }


    private class ListViewListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Object o = lvWeather.getItemAtPosition(position);
            Object income = (Object)o;

            Log.println(Log.INFO, "clicked", Integer.toString(position));
        }
    }

    private void requestData(String accuToday, int version) {

        String[] allStrings = new String[4];

        allStrings[0] = accuToday;
        String arduToday = "http://ardutemp.sytes.net:7000/";
        allStrings[1] = arduToday;
        String history = "http://ardutemp.sytes.net:7000/history";
        allStrings[2] = history;

        allStrings[3] = Integer.toString(version);

        MyTask task = new MyTask();
        task.execute(allStrings);
    }

    protected boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnected())
        {
            return  true;
        }
        else
        {
            return  false;
        }
    }

    private class MyTask extends AsyncTask<String[], Void, String[]> {

        private int taskCity;

        @Override
        protected void onPreExecute() {
//            updateDisplay("Starting task");

            if(tasks.size() == 0){
                //pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected String[] doInBackground(String[]... params)
        {
            String[] strings = new String[4];
            strings = params[0];

//            taskCity = Integer.parseInt(params[1]);

            String[] contents = new String[4];

            for (int i = 0; i < contents.length - 1; i++){
                contents[i] = HTTPManager.getData(strings[i]);
            }
            contents[3] = strings[3];

            return contents;
        }

        @Override
        protected void onPostExecute(String[] result) {

            CLA.add(WeatherJSONParser.parseFeed(result, getApplicationContext()));

//            CLA.add(new WeatherObject(City.values()[taskCity], accuWeather, -2, new int[]{0, 2, -5, 3, 2, 5, 3}, new int[]{1, 4, -2, -1, 3, 4, 2}));

            tasks.remove(this);
            if(tasks.size() == 0){
                //pb.setVisibility(View.INVISIBLE);
            }
        }

//        @Override
//        protected void onProgressUpdate(String... values) {
////            updateDisplay(values[0]);
//        }
    }
}
