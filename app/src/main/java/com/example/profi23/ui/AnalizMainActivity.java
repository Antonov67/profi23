package com.example.profi23.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.example.profi23.R;
import com.example.profi23.controller.NewsAdapter;
import com.example.profi23.model.APIservice;
import com.example.profi23.model.News;
import com.example.profi23.model.RetrofitConnection;
import com.example.profi23.model.TokenClass;
import com.example.profi23.model.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnalizMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analiz_main);
        getSupportActionBar().hide();

        //получим новости от сервера
        //сделаем запрос к серверу для проверки
        APIservice apIservice = RetrofitConnection.getInstance().getRetrofit().create(APIservice.class);
        Call<List<News>> call = apIservice.getNews();
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                List<News> news = response.body();
                Log.d("777", news.get(0).image);
                NewsAdapter newsAdapter = new NewsAdapter(news);
                ViewPager2 viewPagerNews = findViewById(R.id.view_pager_news);
                viewPagerNews.setAdapter(newsAdapter);
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });

    }
}