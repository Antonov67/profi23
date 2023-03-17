package com.example.profi23.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.profi23.R;
import com.example.profi23.model.APIservice;
import com.example.profi23.model.Profile;
import com.example.profi23.model.ResponseServer;
import com.example.profi23.model.RetrofitConnection;
import com.example.profi23.model.TokenClass;
import com.example.profi23.model.Utils;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardCreateActivity extends AppCompatActivity {

    private Spinner spinner;
    private MaterialButton createButton;
    EditText name, middleName, surname, birthday;

    private static final String[] pol = {"Мужской", "Женский"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_create);
        getSupportActionBar().hide();

        //выпадающий список для выбора пола пациента
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CardCreateActivity.this,
                android.R.layout.simple_spinner_item,pol);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //поля ввода данных клиента
        name = findViewById(R.id.editText_name);
        middleName = findViewById(R.id.editText_middle_name);
        surname = findViewById(R.id.editText_surname);
        birthday = findViewById(R.id.editText_birthday);


        //кнопка создания карты пациента
        createButton = findViewById(R.id.button_create);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //создадим профиль пользователя для добавления на сервер
                Profile profile = new Profile(1
                        ,name.getText().toString()
                        ,surname.getText().toString(),
                        middleName.getText().toString()
                        ,"1"
                        ,"Мужской"
                        ,"1");

                //сделаем запрос к серверу для добавления пользователя
                APIservice apIservice = RetrofitConnection.getInstance().getRetrofit().create(APIservice.class);
                Call<ResponseServer> call = apIservice.createProfile(Utils.token, profile);
                call.enqueue(new Callback<ResponseServer>() {
                    @Override
                    public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                        Toast.makeText(CardCreateActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseServer> call, Throwable t) {

                    }
                });
                Log.d("777","token: " +  Utils.token);
                Log.d("777", profile.toString());





            }
        });


    }
}