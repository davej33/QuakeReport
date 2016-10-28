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
import java.net.URL;
import java.util.ArrayList;

import static com.example.android.quakereport.EarthquakeActivity.LOG_TAG;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public class QueryUtils {

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public ArrayList<Earthquake> extractEarthquakes(String urlString) {
        URL url = createURL(urlString);
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "http request error", e);
        }

        ArrayList<Earthquake> quakes = extractData(jsonResponse);

        return quakes;
    }

    private URL createURL(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (IOException e) {
            Log.e(LOG_TAG, "error parsing url", e);
        }
        return url;
    }

    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        InputStream inputStream = null;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
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

    private String readFromStream(InputStream inputStream) {
        StringBuilder output = new StringBuilder();

        try {
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            BufferedReader bufferReader = new BufferedReader(inputReader);
            String line = bufferReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferReader.readLine();
            }
        } catch (IOException e){
            Log.e(LOG_TAG, "http connection error", e);
        }

            /* TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and

            JSONObject root = new JSONObject();
            JSONArray features = root.getJSONArray("features");
            for (int i = 0; i < features.length(); i++) {
                JSONObject instance = features.getJSONObject(i);
                JSONObject properties = instance.getJSONObject("properties");

                double mag = properties.getDouble("mag");
                String loc = properties.getString("place");
                long date = properties.getLong("time");
                String web = properties.getString("url");

                Earthquake quake = new Earthquake(mag,loc,date,web);
                earthquakes[i].add(quake);
            }




        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}