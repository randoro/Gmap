package com.arnoldsson.anton.gmap;

import android.app.Activity;
import android.content.Context;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;

/**
 * Created by Rasmus Dator on 2016-10-26.
 */

public class CustomListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<WeatherObject> listData;


    public CustomListAdapter(Context _context, ArrayList<WeatherObject> _listData)
    {
        this.listData = _listData;
        inflater = LayoutInflater.from(_context);

    }



    @Override
    public int getCount()
    {
        return listData.size();
    }

    @Override
    public Object getItem(int _position)
    {
        return listData.get(_position);
    }

    @Override
    public long getItemId(int _position)
    {
        return _position;
    }

    public View getView(int _position, View _convertView, ViewGroup _parent)
    {
        ViewHolder holder;
        if(_convertView == null)
        {
            _convertView = inflater.inflate(R.layout.list_row_layout, null);
            holder = new ViewHolder();

            holder.tvCity = (TextView) _convertView.findViewById(R.id.tvCity);

            holder.tvAccuToday = (TextView) _convertView.findViewById(R.id.tvTodayAccu);
            holder.tvOWToday = (TextView)_convertView.findViewById(R.id.tvTodayOurWeather);

            holder.tvAccuOne = (TextView)_convertView.findViewById(R.id.tvAccuOne);
            holder.tvAccuTwo = (TextView)_convertView.findViewById(R.id.tvAccuTwo);
            holder.tvAccuThree = (TextView)_convertView.findViewById(R.id.tvAccuThree);
            holder.tvAccuFour = (TextView)_convertView.findViewById(R.id.tvAccuFour);
            holder.tvAccuFive = (TextView)_convertView.findViewById(R.id.tvAccuFive);
            holder.tvAccuSix = (TextView)_convertView.findViewById(R.id.tvAccuSix);
            holder.tvAccuSeven = (TextView)_convertView.findViewById(R.id.tvAccuSeven);

            holder.tvOWOne = (TextView)_convertView.findViewById(R.id.tvOurWeatherOne);
            holder.tvOWTwo = (TextView)_convertView.findViewById(R.id.tvOurWeatherTwo);
            holder.tvOWThree = (TextView)_convertView.findViewById(R.id.tvOurWeatherThree);
            holder.tvOWFour = (TextView)_convertView.findViewById(R.id.tvOurWeatherFour);
            holder.tvOWFive = (TextView)_convertView.findViewById(R.id.tvOurWeatherFive);
            holder.tvOWSix = (TextView)_convertView.findViewById(R.id.tvOurWeatherSix);
            holder.tvOWSeven = (TextView)_convertView.findViewById(R.id.tvOUrWeatherSeven);
            final Context context = _parent.getContext();
            FragmentManager fm = ((Activity) context).getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();

            GMapFragment f1 = new GMapFragment();
            fragmentTransaction.add(R.id.frGmap, f1);
            fragmentTransaction.commit();

            //holder.gMap = (GMapFragment)fm.findFragmentById(R.id.frGmap);
            Log.println(Log.INFO, "gmap added", " ok");
            //holder.gMap.getMapAsync(holder);

            //Set values

            holder.tvCity.setText(listData.get(_position).getCity());

            holder.tvAccuToday.setText(listData.get(_position).accuToday());
            holder.tvOWToday.setText(listData.get(_position).ourToday());




        }
        else
        {
            holder = (ViewHolder) _convertView.getTag();
        }

        return _convertView;

    }

    static class ViewHolder
    {
        //City name
        TextView tvCity;

        //Todays weather.
        TextView tvAccuToday;
        TextView tvOWToday;

        //Measurements by AccuWeather the last seven days
        TextView tvAccuOne;
        TextView tvAccuTwo;
        TextView tvAccuThree;
        TextView tvAccuFour;
        TextView tvAccuFive;
        TextView tvAccuSix;
        TextView tvAccuSeven;

        //Our measurements the last seven days.
        TextView tvOWOne;
        TextView tvOWTwo;
        TextView tvOWThree;
        TextView tvOWFour;
        TextView tvOWFive;
        TextView tvOWSix;
        TextView tvOWSeven;

        GMapFragment gMap;


    }

}
