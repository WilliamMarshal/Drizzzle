package com.example.android.drizzzle.network;

import com.example.android.drizzzle.models.DailyWeatherModel;
import com.example.android.drizzzle.models.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OpenWeatherMapService {

    @GET("/data/2.5/weather?q=London,uk&units=metric&APPID=436e53b3d25d6a6d692dcc4319f62a75")
    Call<WeatherModel> currentWeather();

    @GET("/data/2.5/forecast?q=London,uk&units=metric&APPID=436e53b3d25d6a6d692dcc4319f62a75")
    Call<DailyWeatherModel> dailyWeather();
}
