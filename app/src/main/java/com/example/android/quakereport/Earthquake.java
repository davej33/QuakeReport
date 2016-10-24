package com.example.android.quakereport;

/**
 * Created by dnj on 10/20/16.
 */

public class Earthquake {
    private Double mMag;
    private String mLocation;
    private long mDate;
    private long mTime;
    private String mProximity;

    public Earthquake(Double mag, String proximity, String location, long date, long time){
        mMag = mag;
        mLocation = location;
        mDate = date;
        mTime = time;
        mProximity = proximity;
    }

    public Double getmMag(){ return mMag;}

    public String getmLocation(){ return mLocation;}

    public long getmDate(){
        return mDate;}

    public long getmTime() {
        return mTime;
    }

    public String getmProximity() {
        return mProximity;
    }
}
