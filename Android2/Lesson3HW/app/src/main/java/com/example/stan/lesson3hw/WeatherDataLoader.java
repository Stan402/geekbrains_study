package com.example.stan.lesson3hw;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by stan on 10/12/2017.
 */

public class WeatherDataLoader {

    private static final String LOG_TAG = WeatherDataLoader.class.getSimpleName();

    private static final String OPEN_API_KEY = "2b808ddf45acbdde4a522f0e55db5ca4";
    private static final String KEY = "x-api-key";
    private static final String OPEN_API_MAP = "http://api.openweathermap.org/data/2.5/weather?q=%s";
    private static final String RESPONSE = "cod";
    private static final String NEW_LINE = "\n";
    private static final int ALL_GOOD = 200;

    static JSONObject getJSONData(Context context, String city){
        try{

            URL url = new URL(String.format(OPEN_API_MAP, city));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty(KEY, OPEN_API_KEY);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder rawData = new StringBuilder(1024);
            String tempVariable;
            while ((tempVariable = reader.readLine()) != null) {
                rawData.append(tempVariable).append(NEW_LINE);
            }
            reader.close();
            JSONObject jsonObject = new JSONObject(rawData.toString());
            if (jsonObject.getInt(RESPONSE) != ALL_GOOD){
                return null;
            }
            return jsonObject;

        } catch (Exception e){
            Log.d(LOG_TAG, "Something wrong with getting data from openweathermap", e);
            return null;
        }
    }

}
