package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dnj on 10/20/16.
 */

public class EqAdapter extends ArrayAdapter<Earthquake> {

    public EqAdapter(Context context, ArrayList<Earthquake> earthquakes){
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_view, parent, false);
        }

        final Earthquake currentQuake = getItem(position);

        TextView mag = (TextView) listItemView.findViewById(R.id.mag_view);
        mag.setText(currentQuake.getmMag());

        TextView location = (TextView) listItemView.findViewById(R.id.location_view);
        location.setText(currentQuake.getmLocation());

        TextView date = (TextView) listItemView.findViewById(R.id.date_view);
        date.setText(currentQuake.getmDate());


        return listItemView;
    }
}

