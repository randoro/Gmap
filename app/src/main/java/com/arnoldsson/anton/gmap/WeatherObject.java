package com.arnoldsson.anton.gmap;


import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Rasmus Dator on 2016-10-26.
 */

public class WeatherObject {

    public static double[] Lat = {55.612, 55.70, 56.047073, 55.839154};
    public static double[] Lng = {13.0, 13.20, 12.695367, 13.303499};

    // 55.612, 13.0 Malmö
    // 55.70, 13.20 Lund
    // 56.047073, 12.695367 Helsingborg
    // 55.839154, 13.303499 Eslöv

    private double accuWeather, ourWeather;
    private int[] accu7days,  our7days;
    private City city;

    public WeatherObject(City _city, int _accuWeather, int _ourWeather, int[] _accu7days, int[] _our7days)
    {
        this.accuWeather = _accuWeather;
        this.ourWeather = _ourWeather;
        this.accu7days = _accu7days;
        this.our7days = _our7days;
        this.city = _city;
    }

    public String accuToday() {

        if(accuWeather == 0)
        {
            return "AccuWeather: "+Double.toString(accuWeather) + " °C";
        }

        if(accuWeather > 0)
        {
            return "AccuWeather: +"+Double.toString(accuWeather) + " °C";
        }

        return "AccuWeather: "+Double.toString(accuWeather) + " °C";
    }

    public String ourToday() {

        if(ourWeather == 0)
        {
            return "OurWeather: "+Double.toString(ourWeather) + " °C";
        }

        if(ourWeather > 0)
        {
            return "OurWeather: +"+Double.toString(ourWeather) + " °C";
        }

        return "OurWeather: "+Double.toString(ourWeather) + " °C";
    }

    public String dayName(int index) {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EE");
        cal.add(Calendar.DATE, (-7 + index));
        //Log.println(Log.DEBUG, "date yesterday", dateFormat.format(cal.getTime()));

        return dateFormat.format(cal.getTime());
    }

    public String accuDay(int index) {

        if(accu7days[index] == 0)
        {
            return Double.toString(accu7days[index]) + " °C";
        }

        if(accu7days[index] > 0)
        {
            return "+"+Double.toString(accu7days[index]) + " °C";
        }

        return Double.toString(accu7days[index]) + " °C";
    }

    public String ourDay(int index) {

        if(our7days[index] == 0)
        {
            return Double.toString(our7days[index]) + " °C";
        }

        if(our7days[index] > 0)
        {
            return "+"+Double.toString(our7days[index]) + " °C";
        }

        return Double.toString(our7days[index]) + " °C";
    }




    public String getCity() {
        return city.toString();
    }

    public City getCityType() {
        return city;
    }

}

