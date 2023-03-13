package com.example.profi23.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnection {

    private static final String BASE_URL = "https://medic.madskill.ru";
    static Retrofit retrofit;
    static {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static RetrofitConnection instance;
    private RetrofitConnection(){};
    public static RetrofitConnection getInstance(){
        if (instance == null){
            instance = new RetrofitConnection();
        }
        return instance;
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }
}
