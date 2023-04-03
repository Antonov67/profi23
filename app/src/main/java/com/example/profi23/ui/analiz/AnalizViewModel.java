package com.example.profi23.ui.analiz;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.profi23.model.APIservice;
import com.example.profi23.model.News;
import com.example.profi23.model.Product;
import com.example.profi23.model.RetrofitConnection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnalizViewModel extends ViewModel {

    //получение новостей из списка товаров от сервера с помощью Ретрофита и возврат данных в виде LiveData

    private final MutableLiveData<List<News>> newsList;
    private final MutableLiveData<List<Product>> productList;
    private final  MutableLiveData<HashSet<String>> categorySet;

    public AnalizViewModel() {
        newsList = new MutableLiveData<>();
        productList = new MutableLiveData<>();
        categorySet= new MutableLiveData<>();
        APIservice apIservice = RetrofitConnection.getInstance().getRetrofit().create(APIservice.class);
        //список новостей
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
        //список товаров
        Call<List<Product>> call2 = apIservice.getProducts();
        call2.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                //получим все товары
                productList.postValue(response.body());
                //выберем только категории без дублей
                HashSet<String> tmpList = new HashSet<>();
                for (Product pr : response.body()) {
                    tmpList.add(pr.category);
                }
                categorySet.postValue(tmpList);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }

    public LiveData<List<News>> getNews() {
        return newsList;
    }

    public LiveData<List<Product>> getProducts() {
        return productList;
    }

    //метод возврата категорий товаров
    public LiveData<HashSet<String>> getCategories(){
        return categorySet;
    }
}