package com.example.stan.lesson3hw;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.stan.lesson3hw.entity.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView texts;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        texts = findViewById(R.id.texts);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    public void run() {
                        final JSONObject json = WeatherDataLoader.getJSONData(getApplicationContext(), "London,uk");
                        if (json == null) return;
                        Log.d("TAG", json.toString());
                        handler.post(new Runnable() {
                            public void run() {
                                renderWeather(json);
                            }
                        });
                    }
                }.start();
            }
        });
    }

    private void renderWeather(JSONObject json) {
        Gson gson = new GsonBuilder().create();
        WeatherResponse weatherResponse = gson.fromJson(json.toString(), WeatherResponse.class);
        texts.setText(String.format(Locale.ENGLISH, "City: %s \nTemperature: %.2f\nHumidity: %d\nPressure: %d", weatherResponse.getName()
                , weatherResponse.getMain().getTemp()
                , weatherResponse.getMain().getHumidity()
                , weatherResponse.getMain().getPressure()));
    }

}
