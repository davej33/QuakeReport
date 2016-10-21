package com.example.android.quakereport;

/**
 * Created by dnj on 10/20/16.
 */

public class Earthquake {
    private double mMag;
    private String mLocation;
    private String mDate;

    public Earthquake(double mag, String location, String date){
        mMag = mag;
        mLocation = location;
        mDate = date;
    }

    public double getmMag(){ return mMag;}

    public String getmLocation(){ return mLocation;}

    public String getmDate(){return mDate;}
}
