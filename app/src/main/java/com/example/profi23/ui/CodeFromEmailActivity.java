package com.example.profi23.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.profi23.R;
import com.example.profi23.model.APIservice;
import com.example.profi23.model.EmailCode;
import com.example.profi23.model.RetrofitConnection;
import com.example.profi23.model.TokenClass;
import com.example.profi23.model.Utils;
import com.google.android.material.button.MaterialButton;

import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CodeFromEmailActivity extends AppCompatActivity {

    EditText codeNum1, codeNum2, codeNum3, codeNum4;
    TextView textTime;
    MaterialButton returnButton;
    TimeClass timeClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_from_email);
        codeNum1 = findViewById(R.id.email_code_num1);
        codeNum2 = findViewById(R.id.email_code_num2);
        codeNum3 = findViewById(R.id.email_code_num3);
        codeNum4 = findViewById(R.id.email_code_num4);
        textTime = findViewById(R.id.text_time);
        returnButton = findViewById(R.id.button_return);

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
                            Toast.makeText(CodeFromEmailActivity.this,"token получен", Toast.LENGTH_SHORT).show();
                            Utils.token = response.body().token;
                            Log.d("777", Utils.token);
                            //остановим поток отсчета времени и откроем активити для создания пароля
                            timeClass.isThreadWork(false);
                            startActivity(new Intent(CodeFromEmailActivity.this, PswrdCreateActivity.class));
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

        //организуем обратный отсчет времени в новом потоке
        timeClass = new TimeClass();
        timeClass.start();

        //Если нажать на кнопку "назад", то вернемся на активити отправки кода
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CodeFromEmailActivity.this, SigninActivity.class));
                //остановим поток обратного отсчета времени
                timeClass.isThreadWork(false);
            }
        });

    }

    //создадим внутренний класс для обратного отсчета времени
    class TimeClass extends Thread{

        private boolean isWork; // переменная для останова потока

        private TimeClass() {
            isWork = true;
        }

        public void isThreadWork(boolean isWork){
            this.isWork = isWork;
        }

        @Override
        public void run() {
            int time = 59;
            while (time>=0 && isWork){

                int finalTime = time;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textTime.setText(finalTime + "");
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time--;
                if (time==1){
                    //если время кончилось, то отправляем код еще раз
                    //отправим запрос на почту для получения кода и обнулим счетчик
                    APIservice apIservice = RetrofitConnection.getInstance().getRetrofit().create(APIservice.class);
                    Call<EmailCode> call = apIservice.sendEmailCode(Utils.email);
                    call.enqueue(new Callback<EmailCode>() {
                        @Override
                        public void onResponse(Call<EmailCode> call, Response<EmailCode> response) {
                            Toast.makeText(CodeFromEmailActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(Call<EmailCode> call, Throwable t) {
                            Toast.makeText(CodeFromEmailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("777",t.getMessage());
                        }
                    });
                    //
                    time = 59;
                }
            }


        }

    }
}

