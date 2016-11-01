package com.arnoldsson.anton.gmap;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//public class MainActivity2 extends AppCompatActivity {
//
//    TextView output;
//    ProgressBar pb;
//    List<MyTask> tasks;
//    Spinner spinner;
//    ArrayAdapter<String> spinnerAdapter;
//
//    List<AccuTemp> temps;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
////		Initialize the TextView for vertical scrolling
//        output = (TextView) findViewById(R.id.textView);
//        output.setMovementMethod(new ScrollingMovementMethod());
//
//        pb = (ProgressBar)findViewById(R.id.progressBar);
//        pb.setVisibility(View.INVISIBLE);
//
//        tasks = new ArrayList<>();
//
//        spinner = (Spinner)findViewById(R.id.spinner);
//
//        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(spinnerAdapter);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//        if (item.getItemId() == R.id.action_do_task) {
//            if (isOnline()) {
//                requestData("http://dataservice.accuweather.com/forecasts/v1/daily/1day/314779?apikey=5JuFyCguaUNELRxwcmUAZUyd0CyAabkg&language=en-us&details=true&metric=true");
//            } else
//            {
//                Toast.makeText(this, "Network isn't available", Toast.LENGTH_SHORT).show();
//            }
//        }
//        return false;
//    }
//
//    private void requestData(String uri) {
//        MyTask task = new MyTask();
//        task.execute(uri);
//    }
//
//    protected void updateDisplay() {
//
//        if(temps != null){
//            for(AccuTemp temp : temps){
//                spinnerAdapter.add("" + temp.getTemperature());
//                output.append("" + temp.getTemperature() + "\n");
//            }
//        }
//    }
//
//    protected boolean isOnline()
//    {
//        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        if(netInfo != null && netInfo.isConnected())
//        {
//            return  true;
//        }
//        else
//        {
//            return  false;
//        }
//    }
//
//    private class MyTask extends AsyncTask<String, String, String>{
//
//        @Override
//        protected void onPreExecute() {
////            updateDisplay("Starting task");
//
//            if(tasks.size() == 0){
//                pb.setVisibility(View.VISIBLE);
//            }
//            tasks.add(this);
//        }
//
//        @Override
//        protected String doInBackground(String... params)
//        {
//
//        String content = HTTPManager.getData(params[0]);
//            return content;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//            temps = TempJSONParser.parseFeed(result, getApplicationContext());
//
//            updateDisplay();
//
//            tasks.remove(this);
//            if(tasks.size() == 0){
//                pb.setVisibility(View.INVISIBLE);
//            }
//        }
//
//        @Override
//        protected void onProgressUpdate(String... values) {
////            updateDisplay(values[0]);
//        }
//    }
//}
