package com.example.android.drizzzle.models;

public class WeatherModel {

    public Weather[] weather;
    public Main main;
    public Sys sys;
    public String name;

    public static class Weather {
        public String main;
        public String icon;
    }

    public static class Main {
        public float temp;
    }

    public static class Sys {
        public String country;
    }
}
