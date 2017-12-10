package com.example.stan.lesson3hw.entity;

import com.google.gson.annotations.SerializedName;


public class Sys {
    private int type;
    private int id;
    private String message;
    @SerializedName("country")
    private String countryCode;
    private long sunrise;
    private long sunset;


}
