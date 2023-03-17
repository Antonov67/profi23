package com.example.profi23.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIservice {

    //отправка кода на почту
    @POST("/api/sendCode")
    Call <EmailCode> sendEmailCode(@Header("email") String email);

    //получение токена по коду и почте
    @POST("/api/signin")
    Call <TokenClass> checkEmailCode(@Header("email") String email, @Header("code") String code);

    //создание карты пациента по токену
    @POST("/api/createProfile")
    Call<List<Profile>> createProfile(@Header("Authorization") String token, @Body ResponseServer responseServer);
}
