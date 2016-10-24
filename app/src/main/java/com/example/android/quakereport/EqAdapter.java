package com.example.android.quakereport;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by dnj on 10/20/16.
 */

public class EqAdapter extends ArrayAdapter<Earthquake> {

    public EqAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_view, parent, false);
        }

        final Earthquake currentQuake = getItem(position);

        TextView mag = (TextView) listItemView.findViewById(R.id.mag_view);
        mag.setText(currentQuake.getmMag());

        TextView proximity = (TextView) listItemView.findViewById(R.id.proximity_view);
        proximity.setText(proximity(currentQuake.getmLocation()));

        TextView location = (TextView) listItemView.findViewById(R.id.location_view);
        location.setTypeface(null, Typeface.BOLD);
        location.setText(city(currentQuake.getmLocation()));

        Date dateObj = new Date(currentQuake.getmDate());
        SimpleDateFormat dateForm = new SimpleDateFormat("MMM DD,yyyy");
        String dateString = dateForm.format(dateObj);

        TextView dateView = (TextView) listItemView.findViewById(R.id.date_view);
        dateView.setText(dateString);

        SimpleDateFormat timeForm = new SimpleDateFormat("h MM a");
        String timeString = timeForm.format(dateObj);

        TextView time = (TextView) listItemView.findViewById(R.id.time_view);
        time.setText(timeString);

        return listItemView;
    }

    private String proximity(String loc) {
        String of = "of ";
        if (loc.contains(of)) {
            String[] proxim = loc.split(of);
            return proxim[0];
        } else {
            return "Near the";
        }
    }

    private String city(String loc) {
        String of = "of ";
        if (loc.contains(of)) {
            String[] proxim = loc.split(of);
            return proxim[1];
        } else {
            return loc;
        }
    }

}

