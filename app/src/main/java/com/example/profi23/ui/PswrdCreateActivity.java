package com.example.profi23.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.profi23.R;
import com.google.android.material.button.MaterialButton;

public class PswrdCreateActivity extends AppCompatActivity {

    private static final String APP_PREFERENCES = "preferences";
    private static final String APP_PREFERENCES_PSWRD_STATUS = "pswrd_status";
    private static final String APP_PREFERENCES_PSWRD_VALUE = "pswrd";

    private String pswrd = "";
    TextView skipCreatePswrd;

    MaterialButton button1, button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pswrd_create);
        getSupportActionBar().hide();

        skipCreatePswrd = findViewById(R.id.skip_create_pswrd);
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);

        //сохраним пароль и информацию о том, был ли пароль создан
        SharedPreferences preferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        boolean isPswrdCreate = preferences.getBoolean(APP_PREFERENCES_PSWRD_STATUS, false);
        if (isPswrdCreate){
            startActivity(new Intent(PswrdCreateActivity.this, CardCreateActivity.class));
        }

        skipCreatePswrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(APP_PREFERENCES_PSWRD_STATUS, false);
                editor.commit();
            }
        });

        class MyClickListener implements View.OnClickListener{

            String num;

            public MyClickListener(String num) {
                this.num = num;
            }

            @Override
            public void onClick(View view) {
                pswrd += num;
                Log.d("777", pswrd);
            }
        }

        button1.setOnClickListener(new MyClickListener("1"));
        button2.setOnClickListener(new MyClickListener("2"));

    }
}