package com.example.profi23.model;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIservice {

    @POST("/api/sendCode")
    Call <EmailCode> sendEmailCode(@Header("email") String email);
}
