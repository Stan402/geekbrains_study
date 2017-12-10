package com.example.stan.lesson3hw.entity;

public class WeatherMain {
    private float temp; // Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    private int pressure; //Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
    private int humidity; // Humidity, %
    private float temp_min; //Minimum temperature at the moment. This is deviation from current temp that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    private float temp_max; // Maximum temperature at the moment. This is deviation from current temp that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    private int sea_level; // Atmospheric pressure on the sea level, hPa
    private int grnd_level; // Atmospheric pressure on the ground level, hPa

    public float getTemp() {
        return temp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public int getSea_level() {
        return sea_level;
    }

    public int getGrnd_level() {
        return grnd_level;
    }
}
