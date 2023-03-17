package com.example.profi23.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.profi23.R;

public class CardCreateActivity extends AppCompatActivity {

    Spinner spinner;

    private static final String[] pol = {"Мужской", "Женский"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_create);
        getSupportActionBar().hide();

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CardCreateActivity.this,
                android.R.layout.simple_spinner_item,pol);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
}