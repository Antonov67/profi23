package com.example.profi23;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class CodeFromEmailActivity extends AppCompatActivity {

    EditText codeNum1, codeNum2, codeNum3, codeNum4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_from_email);
        codeNum1 = findViewById(R.id.email_code_num1);
        codeNum2 = findViewById(R.id.email_code_num2);
        codeNum3 = findViewById(R.id.email_code_num3);
        codeNum4 = findViewById(R.id.email_code_num4);

        //сделаем переход по полям ввода после ввода в них цифр из кода, полученного по email
        codeNum1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() == 1){
                    codeNum2.requestFocus();
                }
            }
        });
        codeNum2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() == 1){
                    codeNum3.requestFocus();
                }
            }
        });
        codeNum3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() == 1){
                    codeNum4.requestFocus();
                }
            }
        });
        //после ввода цифры в последнее окно, сделаем запрос к серверу на проверку корректности
        // ввода кода из email
        codeNum4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //если введена последняя цифра, то делаем запрос
                if(editable.length() == 1){

                }
            }
        });
    }
}