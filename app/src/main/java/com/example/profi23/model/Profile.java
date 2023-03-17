package com.example.profi23.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//класс для отправки данных на сервер при создании и обновлении профиля пациента
public class Profile {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("middlename")
    @Expose
    private String middlename;
    @SerializedName("bith")
    @Expose
    private String bith;
    @SerializedName("pol")
    @Expose
    private String pol;
    @SerializedName("image")
    @Expose
    private String image;

    public Profile(Integer id, String firstname, String lastname, String middlename, String bith, String pol, String image) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.bith = bith;
        this.pol = pol;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", bith='" + bith + '\'' +
                ", pol='" + pol + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
