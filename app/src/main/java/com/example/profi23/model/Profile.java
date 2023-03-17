package com.example.profi23.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//класс для отправки данных на сервер при создании и обновлении профиля пациента
public class Profile {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("firstname")
    @Expose
    public String firstname;
    @SerializedName("lastname")
    @Expose
    public String lastname;
    @SerializedName("middlename")
    @Expose
    public String middlename;
    @SerializedName("bith")
    @Expose
    public String bith;
    @SerializedName("pol")
    @Expose
    public String pol;
    @SerializedName("image")
    @Expose
    public String image;

}
