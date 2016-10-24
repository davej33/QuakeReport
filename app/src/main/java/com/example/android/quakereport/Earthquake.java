package com.example.android.quakereport;

/**
 * Created by dnj on 10/20/16.
 */

public class Earthquake {
    private String mMag;
    private String mLocation;
    private String mDate;

    public Earthquake(String mag, String location, String date){
        mMag = mag;
        mLocation = location;
        mDate = date;
    }

    public String getmMag(){ return mMag;}

    public String getmLocation(){ return mLocation;}

    public String getmDate(){return mDate;}
}
