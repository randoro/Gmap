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
import android.widget.ImageView;
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

            holder.tvDayOne = (TextView)_convertView.findViewById(R.id.tvDayOne);
            holder.tvDayTwo = (TextView)_convertView.findViewById(R.id.tvDayTwo);
            holder.tvDayThree = (TextView)_convertView.findViewById(R.id.tvDayThree);
            holder.tvDayFour = (TextView)_convertView.findViewById(R.id.tvDayFour);
            holder.tvDayFive = (TextView)_convertView.findViewById(R.id.tvDayFive);
            holder.tvDaySix = (TextView)_convertView.findViewById(R.id.tvDaySix);
            holder.tvDaySeven = (TextView)_convertView.findViewById(R.id.tvDaySeven);

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

            holder.gMap = (MapImageView) _convertView.findViewById(R.id.ivMap);
            Log.println(Log.INFO, "gmap added", " ok");


            _convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) _convertView.getTag();
        }

        //Set values
        setValues(holder, _position);


        return _convertView;

    }

    private void setValues(ViewHolder holder, int _position)
    {
        holder.tvCity.setText(listData.get(_position).getCity());

        holder.tvAccuToday.setText(listData.get(_position).accuToday());
        holder.tvOWToday.setText(listData.get(_position).ourToday());

        holder.tvDayOne.setText(listData.get(_position).dayName(0));
        holder.tvDayTwo.setText(listData.get(_position).dayName(1));
        holder.tvDayThree.setText(listData.get(_position).dayName(2));
        holder.tvDayFour.setText(listData.get(_position).dayName(3));
        holder.tvDayFive.setText(listData.get(_position).dayName(4));
        holder.tvDaySix.setText(listData.get(_position).dayName(5));
        holder.tvDaySeven.setText(listData.get(_position).dayName(6)); //<- yesterday

        holder.tvAccuOne.setText(listData.get(_position).accuDay(0));
        holder.tvAccuTwo.setText(listData.get(_position).accuDay(1));
        holder.tvAccuThree.setText(listData.get(_position).accuDay(2));
        holder.tvAccuFour.setText(listData.get(_position).accuDay(3));
        holder.tvAccuFive.setText(listData.get(_position).accuDay(4));
        holder.tvAccuSix.setText(listData.get(_position).accuDay(5));
        holder.tvAccuSeven.setText(listData.get(_position).accuDay(6)); //<- yesterday

        holder.tvOWOne.setText(listData.get(_position).ourDay(0));
        holder.tvOWTwo.setText(listData.get(_position).ourDay(1));
        holder.tvOWThree.setText(listData.get(_position).ourDay(2));
        holder.tvOWFour.setText(listData.get(_position).ourDay(3));
        holder.tvOWFive.setText(listData.get(_position).ourDay(4));
        holder.tvOWSix.setText(listData.get(_position).ourDay(5));
        holder.tvOWSeven.setText(listData.get(_position).ourDay(6)); //<- yesterday

        City city = listData.get(_position).getCityType();
        switch (city) {
            case Malmö:
                holder.gMap.setImageResource(R.drawable.malmo_static);
                break;
            case Lund:
                holder.gMap.setImageResource(R.drawable.lund_static);
                break;
            case Helsingborg:
                holder.gMap.setImageResource(R.drawable.helsingborg_static);
                break;
            case Eslöv:
                holder.gMap.setImageResource(R.drawable.eslov_static);
                break;
            default:
                break;

        }

        holder.gMap.setCoordinates(WeatherObject.Lat[city.ordinal()], WeatherObject.Lng[city.ordinal()]);

    }


    static class ViewHolder
    {
        //City name
        TextView tvCity;

        //Todays weather.
        TextView tvAccuToday;
        TextView tvOWToday;

        //Names of the last seven days
        TextView tvDayOne;
        TextView tvDayTwo;
        TextView tvDayThree;
        TextView tvDayFour;
        TextView tvDayFive;
        TextView tvDaySix;
        TextView tvDaySeven;

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

        MapImageView gMap;


    }

}
