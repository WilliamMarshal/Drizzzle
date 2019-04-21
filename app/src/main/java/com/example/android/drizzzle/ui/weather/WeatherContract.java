package com.example.android.drizzzle.ui.weather;

import com.example.android.drizzzle.models.DailyWeatherModel;
import com.example.android.drizzzle.models.WeatherModel;
import com.example.android.drizzzle.mvp.BasePresenter;
import com.example.android.drizzzle.mvp.BaseView;

public class WeatherContract {

    public interface View extends BaseView {

        void onShowCurrentWeather(WeatherModel weatherModel);

        void onShowDailyWeather(DailyWeatherModel dailyWeatherModel);
    }

    public interface Presenter extends BasePresenter<View> {
    }
}
