package com.example.android.drizzzle.ui.weather.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.drizzzle.R;
import com.example.android.drizzzle.Utils.WeatherUtils;
import com.example.android.drizzzle.models.DailyWeatherModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyWeatherAdapter extends RecyclerView.Adapter<DailyWeatherAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private final List<Pair<DailyWeatherModel.ListItem, DailyWeatherModel.ListItem>> items;

    public DailyWeatherAdapter(Context context) {

        layoutInflater = LayoutInflater.from(context);
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.weather_daily_list_item,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,
                                 int position) {
        final Pair<DailyWeatherModel.ListItem, DailyWeatherModel.ListItem> itemPair = items.get(position);
        if (itemPair.first != null) {
            holder.dailyWeatherDayTemp.setText((int) itemPair.first.main.temp + "\u00B0");
            holder.dailyWeatherDate.setText(WeatherUtils.formatDate(itemPair.first.dt));
            holder.dailyWeatherDayIcon.setImageResource(WeatherUtils.getWeatherIcon(itemPair.first.weather[0].icon));
        }
        if (itemPair.second != null) {
            holder.dailyWeatherNightTemp.setText((int) itemPair.second.main.temp + "\u00B0");
            holder.dailyWeatherDate.setText(WeatherUtils.formatDate(itemPair.second.dt));
            holder.dailyWeatherNightIcon.setImageResource(WeatherUtils.getWeatherIcon(itemPair.second.weather[0].icon));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Pair<DailyWeatherModel.ListItem, DailyWeatherModel.ListItem>> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.weather_daily_date)
        AppCompatTextView dailyWeatherDate;

        @BindView(R.id.icon_weather_daily_day)
        AppCompatImageView dailyWeatherDayIcon;

        @BindView(R.id.weather_daily_day_temp)
        AppCompatTextView dailyWeatherDayTemp;

        @BindView(R.id.icon_weather_daily_night)
        AppCompatImageView dailyWeatherNightIcon;

        @BindView(R.id.weather_daily_night_temp)
        AppCompatTextView dailyWeatherNightTemp;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, this.itemView);
        }
    }
}

