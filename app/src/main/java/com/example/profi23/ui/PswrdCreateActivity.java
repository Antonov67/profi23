package com.example.profi23.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.profi23.R;

public class PswrdCreateActivity extends AppCompatActivity {

    private static final String APP_PREFERENCES = "preferences";
    private static final String APP_PREFERENCES_PSWRD_STATUS = "pswrd_status";
    private static final String APP_PREFERENCES_PSWRD_VALUE = "pswrd";

    private int pswrd;
    TextView skipCreatePswrd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pswrd_create);
        getSupportActionBar().hide();

        skipCreatePswrd = findViewById(R.id.skip_create_pswrd);

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



    }
}