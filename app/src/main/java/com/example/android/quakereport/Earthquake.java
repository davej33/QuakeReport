package com.example.android.quakereport;

/**
 * Created by dnj on 10/20/16.
 */

public class Earthquake {
    private Double mMag;
    private String mLocation;
    private long mDate;
    private String mUrl;

    public Earthquake(Double mag, String location, long date, String Url){
        mMag = mag;
        mLocation = location;
        mDate = date;
        mUrl = Url;
    }

    public Double getmMag(){ return mMag;}

    public String getmLocation(){ return mLocation;}

    public long getmDate(){
        return mDate;}

    public String getmUrl() {
        return mUrl;
    }
}
