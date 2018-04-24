package com.arezoumandi.androidtask.networking;

import android.util.Log;

import com.arezoumandi.androidtask.BuildConfig;
import com.arezoumandi.androidtask.constants.Constants;
import com.arezoumandi.androidtask.model.Country;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bs156 on 09-Dec-16.
 */

public class RetroClient {

    private RetroClient() {
        //
    }

    public static Api getApi() {
        return getClient().create(Api.class);
    }

    private static Retrofit getClient() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttp())
                .build();
    }

    private static OkHttpClient getOkHttp() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.readTimeout(120, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(120, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpBuilder.addNetworkInterceptor(loggingInterceptor);
        }

        return okHttpBuilder.build();
    }

}
