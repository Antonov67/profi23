package com.example.profi23.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.profi23.model.APIservice;
import com.example.profi23.model.News;
import com.example.profi23.model.RetrofitConnection;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnalizViewModel extends ViewModel {

    private final MutableLiveData<List<News>> newsList;

    public AnalizViewModel() {
        newsList = new MutableLiveData<>();
        APIservice apIservice = RetrofitConnection.getInstance().getRetrofit().create(APIservice.class);
        Call<List<News>> call = apIservice.getNews();
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                newsList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });

    }

    public LiveData<List<News>> getText() {
        return newsList;
    }
}