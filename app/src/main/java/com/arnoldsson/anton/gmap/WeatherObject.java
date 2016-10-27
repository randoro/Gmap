package com.arnoldsson.anton.gmap;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

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
    private City city;

    public WeatherObject(City _city, int _accuWeather, int _ourWeather)
    {
        this.accuWeather = _accuWeather;
        this.ourWeather = _ourWeather;
        this.city = _city;
    }

    public String accuToday() {

        if(accuWeather == 0)
        {
            return Double.toString(accuWeather) + " °C";
        }

        if(accuWeather > 0)
        {
            return "+"+Double.toString(accuWeather) + " °C";
        }

        return Double.toString(accuWeather) + " °C";
    }

    public String ourToday() {

        if(ourWeather == 0)
        {
            return Double.toString(ourWeather) + " °C";
        }

        if(ourWeather > 0)
        {
            return "+"+Double.toString(ourWeather) + " °C";
        }

        return Double.toString(ourWeather) + " °C";
    }

    public String getCity() {
        return city.toString();
    }

}

