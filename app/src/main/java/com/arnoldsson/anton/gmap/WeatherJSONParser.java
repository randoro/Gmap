package com.arnoldsson.anton.gmap;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Carl on 2016-10-25.
 */
public class WeatherJSONParser {
    public static WeatherObject parseFeed(String[] content, Context context) {

        int taskCity = Integer.parseInt(content[3]);

        String compareWithThis = "";
        City city = City.values()[taskCity];

        switch (city) {
            case Malmö:
                compareWithThis = "Malmö";
                break;
            case Helsingborg:
                compareWithThis = "Helsingborg";
                break;
            case Eslöv:
                compareWithThis = "Eslöv";
                break;
            default:
                break;
        }

        double accuWeather = (int)AccuWeather(content[0]);
        double arduWeather = (int)OurWeather(content[1], compareWithThis);
        int[] accu7Days = Accu7Days(content[2], compareWithThis);
        int[] ardu7Days = Our7Days(content[2], compareWithThis);

        return new WeatherObject(City.values()[taskCity], accuWeather, arduWeather, accu7Days, ardu7Days);

//        try {
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
    }
    private static double AccuWeather(String content){
        try {
            JSONObject everything_obj = new JSONObject(content);

            JSONArray array = everything_obj.getJSONArray("DailyForecasts");

            JSONObject temp1 = array.getJSONObject(0).getJSONObject("Temperature").getJSONObject("Maximum");
            JSONObject temp2 = array.getJSONObject(0).getJSONObject("Temperature").getJSONObject("Minimum");

            double average = (temp1.getDouble("Value") + temp2.getDouble("Value")) / 2;

            return  average;
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }
    private static double OurWeather(String content, String compareWithThis){
        try {
            JSONArray array = new JSONArray(content);

            for(int i = 0; i < array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                if(obj.getString("city").equals(compareWithThis)){
                    return obj.getInt("temp");
                }
            }
            return 0;
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }
    private static int[] Accu7Days(String content, String compareWithThis){
        try {
            JSONArray array = new JSONArray(content);

            int[] accu7days = new int[7];
            int accu_index = accu7days.length - 1;

            for(int i = 0; i < array.length(); i++){
                JSONObject obj = array.getJSONObject(i);

                if(obj.getString("city").equals(compareWithThis)){
                    accu7days[accu_index] = obj.getInt("accutemp");
                    accu_index--;

                    if(accu_index < 0){
                        break;
                    }
                }
            }
            return accu7days;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static int[] Our7Days(String content, String compareWithThis){
        try {
            JSONArray array = new JSONArray(content);

            int[] our7days = new int[7];
            int ourIndex = our7days.length - 1;

            for(int i = 0; i < array.length(); i++){
                JSONObject obj = array.getJSONObject(i);

                if(obj.getString("city").equals(compareWithThis)){
                    our7days[ourIndex] = obj.getInt("ardutemp");
                    ourIndex--;

                    if(ourIndex < 0){
                        break;
                    }
                }
            }
            return our7days;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
