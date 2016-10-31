package com.arnoldsson.anton.gmap;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carl on 2016-10-25.
 */
public class TempJSONParser {
    public static double parseFeed(String content, Context context) {
        try {
            JSONObject everything_obj = new JSONObject(content);

            List<AccuTemp> temps = new ArrayList<>();

            JSONArray array = everything_obj.getJSONArray("DailyForecasts");

            JSONObject obj = array.getJSONObject(0).getJSONObject("Temperature").getJSONObject("Maximum");


            return obj.getDouble("Value");
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
