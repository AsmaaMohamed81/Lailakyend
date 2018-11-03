package com.Alatheer.Projects.lailaky.ApiServices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Emad on 2018-04-03.
 */

public class Api {
    private static Retrofit retrofit=null;

    public static Retrofit getClient()
    {
        if (retrofit==null)
        {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(20,TimeUnit.SECONDS)
                    .readTimeout(20,TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Tags.BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
