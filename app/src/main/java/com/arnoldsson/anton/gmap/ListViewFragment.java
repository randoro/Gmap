package com.arnoldsson.anton.gmap;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends Fragment {

    public ListView lvWeather;
    private View view;


    public ListViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (View)inflater.inflate(R.layout.list_row_layout, container, false);

        InitializeComponents();

        return view;

    }

    private void InitializeComponents()
    {
        lvWeather = (ListView) view.findViewById(R.id.lvWeather);
    }

}

