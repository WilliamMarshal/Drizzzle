package com.example.android.drizzzle.models;

import java.util.List;
import java.util.Objects;

public class DailyWeatherModel {

    public List<ListItem> list;

    public static class ListItem {

        public long dt;
        public ListItem.Main main;
        public ListItem.Weather[] weather;

        @Override
        public int hashCode() {
            return Objects.hash(dt);
        }

        public static class Main {
            public float temp;
        }

        public static class Weather {
            public String icon;
        }
    }
}
