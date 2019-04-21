package com.example.android.drizzzle.ui.weather;

import com.example.android.drizzzle.models.DailyWeatherModel;
import com.example.android.drizzzle.models.WeatherModel;
import com.example.android.drizzzle.mvp.impl.BasePresenterImpl;
import com.example.android.drizzzle.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherPresenter
        extends BasePresenterImpl<WeatherContract.View>
        implements WeatherContract.Presenter {

    @Override
    public void onAttach(WeatherContract.View view) {
        super.onAttach(view);

        ApiClient.OPEN_WEATHER_MAP_SERVICE.currentWeather().enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.isSuccessful()) {
                    WeatherModel model = response.body();
                    if (isViewAttached()) {
                        getView().onShowCurrentWeather(model);
                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
            }
        });

        ApiClient.OPEN_WEATHER_MAP_SERVICE.dailyWeather().enqueue(new Callback<DailyWeatherModel>() {
            @Override
            public void onResponse(Call<DailyWeatherModel> call, Response<DailyWeatherModel> response) {
                if (response.isSuccessful()) {
                    DailyWeatherModel model = response.body();
                    if (isViewAttached()) {
                        getView().onShowDailyWeather(model);
                    }
                }
            }

            @Override
            public void onFailure(Call<DailyWeatherModel> call, Throwable t) {
            }
        });
    }
}
