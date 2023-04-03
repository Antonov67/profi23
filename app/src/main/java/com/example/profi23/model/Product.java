package com.example.profi23.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Product {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("price")
    @Expose
    public String price;
    @SerializedName("category")
    @Expose
    public String category;
    @SerializedName("time_result")
    @Expose
    public String timeResult;
    @SerializedName("preparation")
    @Expose
    public String preparation;
    @SerializedName("bio")
    @Expose
    public String bio;

    @Override
    public String toString() {
        return "id=" + id + ", category=" + category + "\n";
    }

}