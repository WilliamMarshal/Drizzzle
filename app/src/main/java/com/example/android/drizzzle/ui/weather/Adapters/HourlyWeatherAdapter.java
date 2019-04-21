package com.example.android.drizzzle.ui.weather.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
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

public class HourlyWeatherAdapter extends RecyclerView.Adapter<HourlyWeatherAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private final List<DailyWeatherModel.ListItem> items;

    public HourlyWeatherAdapter(Context context) {

        layoutInflater = LayoutInflater.from(context);
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.weather_hourly_list_item,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,
                                 int position) {
        final DailyWeatherModel.ListItem item = items.get(position);
        holder.hourlyWeatherTemp.setText((int) item.main.temp + "\u00B0");
        holder.hourlyWeatherTime.setText(WeatherUtils.formatTime(item.dt));
        holder.hourlyWeatherIcon.setImageResource(WeatherUtils.getWeatherIcon(item.weather[0].icon));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems
            (List<DailyWeatherModel.ListItem> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.weather_hourly_temp)
        AppCompatTextView hourlyWeatherTemp;

        @BindView(R.id.icon_weather_hourly)
        AppCompatImageView hourlyWeatherIcon;

        @BindView(R.id.weather_hourly_time)
        AppCompatTextView hourlyWeatherTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, this.itemView);
        }
    }
}

