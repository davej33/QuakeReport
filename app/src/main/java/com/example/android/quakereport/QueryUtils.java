package com.example.android.quakereport;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.quakereport.EarthquakeActivity.LOG_TAG;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    public static List<Earthquake> fetchEarthquakes(String urlString) {
        URL url = createURL(urlString);
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "http request error", e);
        }

        List<Earthquake> earthquakesList = extractData(jsonResponse);

        return earthquakesList;
    }

    private static URL createURL(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "error processing url");
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String jsonResponse = "";
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "http connection error", e);
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) {
        StringBuilder output = new StringBuilder();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader lineReader = new BufferedReader(inputStreamReader);
            String line = lineReader.readLine();
            while (line != null) {
                output.append(line);
                line = lineReader.readLine();
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "stream parsing error", e);
        }
        return output.toString();
    }

    private static List<Earthquake> extractData(String data) {
        List<Earthquake> eqList = new ArrayList<Earthquake>();
        try {
            // create JSON object from the query results
            JSONObject quakeRoot = new JSONObject(data);

            // create JSON array of quake instances from quakeRoot
            JSONArray quakeArray = quakeRoot.getJSONArray("features");

            // iterate through quakeArray
            for (int i = 0; i < quakeArray.length(); i++) {

                // create JSON object for each quakeArray element
                JSONObject quakeObject = quakeArray.getJSONObject(i);

                // create JSON object from 'properties' JSON object
                JSONObject quakeProperties = quakeObject.getJSONObject("properties");

                // extract values from quakeProperties object
                Double mag = quakeProperties.getDouble("mag");
                String loc = quakeProperties.getString("place");
                long date = quakeProperties.optLong("time");
                String website = quakeProperties.getString("url");


                // create new Earthquake object using extracted values
                Earthquake quakeInstance = new Earthquake(mag, loc, date, website);
                eqList.add(quakeInstance);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return eqList;
    }

}