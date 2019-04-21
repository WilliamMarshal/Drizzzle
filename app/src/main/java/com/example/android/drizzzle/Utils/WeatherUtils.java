package com.example.android.drizzzle.Utils;

import android.util.Pair;

import com.example.android.drizzzle.R;
import com.example.android.drizzzle.models.DailyWeatherModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WeatherUtils {
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM");
    private static final SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm a");

    public static List<Pair<DailyWeatherModel.ListItem, DailyWeatherModel.ListItem>> getNext5Days
            (List<DailyWeatherModel.ListItem> items) {
        Map<String, DailyWeatherModel.ListItem> dailyItems = new LinkedHashMap<>();
        for (DailyWeatherModel.ListItem item : items) {
            dailyItems.put(formatDate(item.dt), item);
        }
        List<DailyWeatherModel.ListItem> fiveDaysItems = new ArrayList<>(dailyItems.values());
        List<Pair<DailyWeatherModel.ListItem, DailyWeatherModel.ListItem>> fiveDayNightItems = new ArrayList<>();
        for (DailyWeatherModel.ListItem item : fiveDaysItems) {
            int midnightIndex = items.indexOf(item);
            int nightIndex = midnightIndex - 1;
            int dayIndex = midnightIndex - 4;
            DailyWeatherModel.ListItem nightItem = null, dayItem = null;
            if (nightIndex >= 0) {
                nightItem = items.get(nightIndex);
            }
            if (dayIndex >= 0) {
                dayItem = items.get(dayIndex);
            }
            if (nightItem == null && dayItem == null) {
                continue;
            }
            fiveDayNightItems.add(new Pair<>(dayItem, nightItem));
            if (fiveDayNightItems.size() == 5) {
                break;
            }
        }
        return fiveDayNightItems;
    }

    public static int getWeatherIcon(String id) {
        id = id.replace("n", "d");
        switch (id) {
            case "01d":
                return R.drawable.ic01d;
            case "02d":
                return R.drawable.ic02d;
            case "03d":
                return R.drawable.ic03d;
            case "04d":
                return R.drawable.ic04d;
            case "09d":
                return R.drawable.ic09d;
            case "10d":
                return R.drawable.ic10d;
            case "11d":
                return R.drawable.ic11d;
            case "013":
                return R.drawable.ic13d;
            case "50d":
                return R.drawable.ic50d;
            default:
                return 0;
        }
    }

    public static List<DailyWeatherModel.ListItem> getHourlyWeather(List<DailyWeatherModel.ListItem> list) {
        return list.subList(0, 5);
    }

    public static String formatDate(long dt) {
        return dateFormatter.format(new Date(dt * 1000));
    }

    public static String formatTime(long dt) {
        return timeFormatter.format(new Date(dt * 1000));
    }
}
