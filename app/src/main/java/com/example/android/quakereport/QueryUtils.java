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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.quakereport.EarthquakeActivity.LOG_TAG;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    public static List<Earthquake> fetchData(String urlString) {
        Log.v(LOG_TAG, "fetch Data");


        URL url = createURL(urlString);
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Http request error", e);
        }

        List<Earthquake> quakeList = extractData(jsonResponse);

        return quakeList;
    }

    private static URL createURL(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Malformed URL", e);
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
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "URL connection error", e);
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) {
        StringBuilder output = new StringBuilder();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "stream reader error", e);
        }
        return output.toString();
    }

    private static List<Earthquake> extractData(String data) {

        List<Earthquake> quakeList = new ArrayList<>();

        if (data == null) {
            return null;
        }
        try {
            JSONObject root = new JSONObject(data);
            JSONArray quakeElement = root.getJSONArray("features");
            for (int i = 0; i < quakeElement.length(); i++) {
                JSONObject element = quakeElement.getJSONObject(i);
                JSONObject properties = element.getJSONObject("properties");

                double mag = properties.getDouble("mag");
                String loc = properties.getString("place");
                long date = properties.getLong("time");
                String url = properties.getString("url");

                Earthquake quake = new Earthquake(mag, loc, date, url);
                quakeList.add(quake);

            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "JSON parsing error", e);
        }
        return quakeList;
    }


}

