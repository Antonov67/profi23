package com.example.profi23.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.profi23.R;
import com.google.android.material.button.MaterialButton;

public class PswrdCreateActivity extends AppCompatActivity {

    private static final String APP_PREFERENCES = "preferences";
    private static final String APP_PREFERENCES_PSWRD_STATUS = "pswrd_status";
    private static final String APP_PREFERENCES_PSWRD_VALUE = "pswrd";

    private String pswrd = "";
    TextView skipCreatePswrd;
    ImageView lenPswrdImg;  // графическое отображение длины пароля

    //цифровые кнопки
    MaterialButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;
    Button delButton; // кнопка стирания


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pswrd_create);
        getSupportActionBar().hide();




        skipCreatePswrd = findViewById(R.id.skip_create_pswrd);

        lenPswrdImg = findViewById(R.id.image_pswrd_length);

        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        button9 = findViewById(R.id.button_9);
        button0 = findViewById(R.id.button_0);

        delButton = findViewById(R.id.button_del);
        //кнопка стирания удаляет символы в пароле, пока есть что удалять
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pswrd.length() > 0){
                    pswrd = pswrd.substring(0,pswrd.length() - 1);
                    Log.d("777", pswrd);
                    updateImg(pswrd.length());
                }
            }
        });

        //сохраним пароль и информацию о том, был ли пароль создан
        SharedPreferences preferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);

        //для тестирования в процессе разработки необходимо "обнулять" sharedPreferences
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putBoolean(APP_PREFERENCES_PSWRD_STATUS, false);
//        editor.putString(APP_PREFERENCES_PSWRD_VALUE, null);
//        editor.commit();


        boolean isPswrdCreate = preferences.getBoolean(APP_PREFERENCES_PSWRD_STATUS, false);
        //контроль сохраненного пароля, если пароль был создан в прошлые запуски, то сразу откроется активити
        //создания мед карты.
        if (isPswrdCreate){
            startActivity(new Intent(PswrdCreateActivity.this, CardCreateActivity.class));
        }
        //если нажали на кнопку пропустить, то окно ввода пароля откроется еще раз при следующем запуске приложения
        skipCreatePswrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(APP_PREFERENCES_PSWRD_STATUS, false);
                editor.commit();
                startActivity(new Intent(PswrdCreateActivity.this, CardCreateActivity.class));
            }
        });

        //напишем класс для обработчика нажатия на цифровую кнопку - реализуем интерфейс слушателя нажатия, все это для более короткого кода
        //один метод для 10 кнопок
        class MyClickListener implements View.OnClickListener{

            String num;

            public MyClickListener(String num) {
                this.num = num;
            }

            @Override
            public void onClick(View view) {
                //добавление цифры после нажатия в пароль
                pswrd += num;
                Log.d("777", pswrd);
                //контролируем длину пароля в 4 символа, как только пароль этой длины будет введен

                if (pswrd.length() < 4){
                    updateImg(pswrd.length());
                }else if (pswrd.length() == 4) {
                    updateImg(pswrd.length());
                    //сохраним пароль в устройстве и больше показываться окно ввода пароля не будет
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean(APP_PREFERENCES_PSWRD_STATUS, true);
                    editor.putString(APP_PREFERENCES_PSWRD_VALUE, pswrd);
                    editor.commit();
                    //открываем активити для создания мед карты.
                    startActivity(new Intent(PswrdCreateActivity.this, CardCreateActivity.class));
                }

            }
        }

        // добавим наш слушатель в кнопки, каждая кнопка добавляет свою цифру в пароль
        button1.setOnClickListener(new MyClickListener("1"));
        button2.setOnClickListener(new MyClickListener("2"));
        button3.setOnClickListener(new MyClickListener("3"));
        button4.setOnClickListener(new MyClickListener("4"));
        button5.setOnClickListener(new MyClickListener("5"));
        button6.setOnClickListener(new MyClickListener("6"));
        button7.setOnClickListener(new MyClickListener("7"));
        button8.setOnClickListener(new MyClickListener("8"));
        button9.setOnClickListener(new MyClickListener("9"));
        button0.setOnClickListener(new MyClickListener("0"));

    }

    //метод обновления картинки по длине пароля
    void updateImg(int length){
        switch (length){
            case 0:
                lenPswrdImg.setImageResource(R.drawable.progress0);
                break;
            case 1:
                lenPswrdImg.setImageResource(R.drawable.progress1);
                break;
            case 2:
                lenPswrdImg.setImageResource(R.drawable.progress2);
                break;
            case 3:
                lenPswrdImg.setImageResource(R.drawable.progress3);
                break;
            case 4:
                lenPswrdImg.setImageResource(R.drawable.progress4);
                break;
        }
    }
}