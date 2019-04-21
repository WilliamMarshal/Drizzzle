package com.example.android.drizzzle.ui.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.drizzzle.R;
import com.example.android.drizzzle.Utils.WeatherUtils;
import com.example.android.drizzzle.models.DailyWeatherModel;
import com.example.android.drizzzle.models.WeatherModel;
import com.example.android.drizzzle.ui.weather.Adapters.DailyWeatherAdapter;
import com.example.android.drizzzle.ui.weather.Adapters.HourlyWeatherAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.View {

    @BindView(R.id.city)
    AppCompatTextView city;

    @BindView(R.id.country)
    AppCompatTextView country;

    @BindView(R.id.weather_temp_today)
    AppCompatTextView weatherTempToday;

    @BindView(R.id.icon_weather_today)
    AppCompatImageView iconWeatherToday;

    @BindView(R.id.weather_today_description)
    AppCompatTextView weatherTodayDescription;

    @BindView(R.id.weather_daily_recycler_view)
    RecyclerView weatherDailyRecyclerView;

    @BindView(R.id.weather_hourly_recycler_view)
    RecyclerView weatherHourlyRecyclerView;

    private DailyWeatherAdapter dailyWeatherAdapter;
    private HourlyWeatherAdapter hourlyWeatherAdapter;
    private WeatherContract.Presenter presenter = new WeatherPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        hourlyWeatherAdapter = new HourlyWeatherAdapter(this);
        weatherHourlyRecyclerView.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false));
        weatherHourlyRecyclerView.setAdapter(hourlyWeatherAdapter);

        dailyWeatherAdapter = new DailyWeatherAdapter(this);
        weatherDailyRecyclerView.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false));
        weatherDailyRecyclerView.setAdapter(dailyWeatherAdapter);
        presenter.onAttach(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onShowCurrentWeather(WeatherModel weatherModel) {
        city.setText(weatherModel.name);
        country.setText(weatherModel.sys.country);
        weatherTempToday.setText(((int) weatherModel.main.temp) + "\u00B0");
        weatherTodayDescription.setText(weatherModel.weather[0].main);
        iconWeatherToday.setImageResource(WeatherUtils.getWeatherIcon(weatherModel.weather[0].icon));
    }

    @Override
    public void onShowDailyWeather(DailyWeatherModel dailyWeatherModel) {
        dailyWeatherAdapter.setItems(WeatherUtils.getNext5Days(dailyWeatherModel.list));
        hourlyWeatherAdapter.setItems(WeatherUtils.getHourlyWeather(dailyWeatherModel.list));
    }

    @Override
    public void showLoader() {
    }

    @Override
    public void showContent() {
    }
}
