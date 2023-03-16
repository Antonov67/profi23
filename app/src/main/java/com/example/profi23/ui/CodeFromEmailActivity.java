package com.example.profi23.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.profi23.R;
import com.example.profi23.model.APIservice;
import com.example.profi23.model.EmailCode;
import com.example.profi23.model.RetrofitConnection;
import com.example.profi23.model.TokenClass;
import com.example.profi23.model.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                    //запишем цифру в CODE, предварительно очистив ее
                    Utils.code = "";
                    Utils.code += editable.toString();
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
                    //запишем цифру в CODE
                    Utils.code += editable.toString();
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
                    //запишем цифру в CODE
                    Utils.code += editable.toString();
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
                    //запишем цифру в CODE
                    Utils.code += editable.toString();
                    //сделаем запрос к серверу для проверки
                    APIservice apIservice = RetrofitConnection.getInstance().getRetrofit().create(APIservice.class);
                    Call<TokenClass> call = apIservice.checkEmailCode(Utils.email,Utils.code);
                    call.enqueue(new Callback<TokenClass>() {
                        @Override
                        public void onResponse(Call<TokenClass> call, Response<TokenClass> response) {
                            //Если код и почта верны, то ...
                            Toast.makeText(CodeFromEmailActivity.this,"token: " + response.body().token, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<TokenClass> call, Throwable t) {
                            Toast.makeText(CodeFromEmailActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("777",t.getMessage());
                        }
                    });
                }
            }
        });
    }
}