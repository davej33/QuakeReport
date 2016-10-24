package com.example.android.quakereport;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.android.quakereport.R.color.magnitude1;
import static com.example.android.quakereport.R.id.mag_view;

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

        TextView mag = (TextView) listItemView.findViewById(mag_view);
        DecimalFormat reformat = new DecimalFormat("0.0");
        String magnitude = reformat.format(currentQuake.getmMag());
        mag.setText(magnitude);

        GradientDrawable magBackground = (GradientDrawable) mag.getBackground();
        int color = getBackgroudColor(currentQuake.getmMag());
        magBackground.setColor(color);


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

        SimpleDateFormat timeForm = new SimpleDateFormat("h:MM a");
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

    private int getBackgroudColor(double magnitude) {
        int mag = (int) magnitude;
        switch (mag) {
            case 1:
                int magnitude1Color = ContextCompat.getColor(getContext(), magnitude1);
                mag = magnitude1Color;
                break;
            case 2:
                int magnitude2Color = ContextCompat.getColor(getContext(), R.color.magnitude2);
                mag = magnitude2Color;
                break;
            case 3:
                int magnitude3Color = ContextCompat.getColor(getContext(), R.color.magnitude3);
                mag = magnitude3Color;
                break;
            case 4:
                int magnitude4Color = ContextCompat.getColor(getContext(), R.color.magnitude4);
                mag = magnitude4Color;
                break;
            case 5:
                int magnitude5Color = ContextCompat.getColor(getContext(), R.color.magnitude5);
                mag = magnitude5Color;
                break;
            case 6:
                int magnitude6Color = ContextCompat.getColor(getContext(), R.color.magnitude6);
                mag = magnitude6Color;
                break;
            case 7:
                int magnitude7Color = ContextCompat.getColor(getContext(), R.color.magnitude7);
                mag = magnitude7Color;
                break;
            case 8:
                int magnitude8Color = ContextCompat.getColor(getContext(), R.color.magnitude8);
                mag = magnitude8Color;
                break;
            default:
                int magnitude9Color = ContextCompat.getColor(getContext(), R.color.magnitude9);
                mag = magnitude9Color;
                break;

        }
        return mag;


    }

}

