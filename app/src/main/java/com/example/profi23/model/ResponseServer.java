package com.example.profi23.model;

//класс для получения ответа сервера при создании профиля или его обновлении

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ResponseServer {

   @SerializedName("id")
   @Expose
   private Integer id;
   @SerializedName("user_id")
   @Expose
   private Integer userId;
   @SerializedName("firstname")
   @Expose
   private String firstname;
   @SerializedName("lastname")
   @Expose
   private String lastname;
   @SerializedName("middlename")
   @Expose
   private String middlename;
   @SerializedName("pol")
   @Expose
   private String pol;
   @SerializedName("image")
   @Expose
   private String image;
   @SerializedName("created_at")
   @Expose
   private String createdAt;
   @SerializedName("updated_at")
   @Expose
   private String updatedAt;
   @SerializedName("bith")
   @Expose
   private String bith;

   @Override
   public String toString() {
      return "ResponseServer{" +
              "id=" + id +
              ", userId=" + userId +
              ", firstname='" + firstname + '\'' +
              ", lastname='" + lastname + '\'' +
              ", middlename='" + middlename + '\'' +
              ", pol='" + pol + '\'' +
              ", image='" + image + '\'' +
              ", createdAt='" + createdAt + '\'' +
              ", updatedAt='" + updatedAt + '\'' +
              ", bith='" + bith + '\'' +
              '}';
   }
}
