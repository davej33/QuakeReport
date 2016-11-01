package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

import static com.example.android.quakereport.EarthquakeActivity.LOG_TAG;

/**
 * Created by dnj on 10/31/16.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    String mUrl;

    public EarthquakeLoader(Context context, String url){
        super(context);
        mUrl = url;
        Log.v(LOG_TAG, "EarthQuake Loader");
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.v(LOG_TAG, "on Start Loader");
    }

    @Override
    public List<Earthquake> loadInBackground() {
        List<Earthquake> quakeList = QueryUtils.fetchData(mUrl);
        Log.v(LOG_TAG, "load in background");
        return quakeList;
    }
}
