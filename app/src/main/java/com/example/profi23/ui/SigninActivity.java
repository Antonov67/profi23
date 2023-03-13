package com.example.profi23.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.profi23.R;
import com.example.profi23.model.APIservice;
import com.example.profi23.model.EmailCode;
import com.example.profi23.model.RetrofitConnection;

import java.util.concurrent.Semaphore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninActivity extends AppCompatActivity {

    EditText email;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        getSupportActionBar().hide();

        email = findViewById(R.id.editText_signin);
        nextButton = findViewById(R.id.button_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //проверка корректности почты на шаблон и на то, что все символы маленькие
                if (isEmailValid(email.getText().toString()) && email.getText().toString().toLowerCase().equals(email.getText().toString())){
                    Toast.makeText(SigninActivity.this, "email correct", Toast.LENGTH_SHORT).show();

                    //отправим запрос на почту для получения кода
                    APIservice apIservice = RetrofitConnection.getInstance().getRetrofit().create(APIservice.class);
                    Call<EmailCode> call = apIservice.sendEmailCode(email.getText().toString());
                    call.enqueue(new Callback<EmailCode>() {
                        @Override
                        public void onResponse(Call<EmailCode> call, Response<EmailCode> response) {
                            Toast.makeText(SigninActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<EmailCode> call, Throwable t) {
                            Toast.makeText(SigninActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("777",t.getMessage());
                        }
                    });
                }
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0){
                    int color = Color.parseColor("#C9D4FB");
                    nextButton.setBackgroundColor(color);
                }else {
                    int color = Color.parseColor("#1A6FEE");
                    nextButton.setBackgroundColor(color);
                }
            }
        });
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}