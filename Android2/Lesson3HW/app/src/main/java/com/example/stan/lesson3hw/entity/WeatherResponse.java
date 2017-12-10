package com.example.stan.lesson3hw.entity;

import java.util.ArrayList;
import java.util.List;


public class WeatherResponse {

    private Coordinate coord;
    private List<Weather> weather;
    private String base;
    private WeatherMain main;
    private String visibility;
    private Wind wind;
    private Clouds clouds;
    private long dt;
    private Sys sys;
    private int id;
    private String name;
    private int cod;

    public WeatherResponse() {
        weather = new ArrayList<>();
        main = new WeatherMain();
        wind = new Wind();
        sys = new Sys();
        coord = new Coordinate();
        clouds = new Clouds();
    }

    public Coordinate getCoord() {
        return coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public WeatherMain getMain() {
        return main;
    }

    public String getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public long getDt() {
        return dt;
    }

    public Sys getSys() {
        return sys;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }
}
