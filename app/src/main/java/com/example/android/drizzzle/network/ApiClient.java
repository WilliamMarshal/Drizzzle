package com.example.android.drizzzle.network;

import com.example.android.drizzzle.BuildConfig;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final OpenWeatherMapService OPEN_WEATHER_MAP_SERVICE = createRetrofit(new Gson(),
            createOkHttpClient(createLoggingInterceptor()),
            "http://api.openweathermap.org").create(OpenWeatherMapService.class);

    private static OkHttpClient createOkHttpClient(Interceptor loggingInterceptor) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor);
        }
        return builder.build();
    }

    private static Interceptor createLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private static Retrofit createRetrofit(Gson gson,
                                           OkHttpClient okHttpClient,
                                           String serverEndpoint) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(serverEndpoint)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
