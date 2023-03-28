package com.example.profi23.ui.analiz;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.profi23.R;
import com.example.profi23.controller.AnalizArrayAdapter;
import com.example.profi23.controller.NewsAdapter;
import com.example.profi23.databinding.FragmentAnalizBinding;
import com.example.profi23.model.News;
import com.example.profi23.model.Product;
import com.google.android.material.button.MaterialButton;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class AnalizFragment extends Fragment {

    private FragmentAnalizBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        AnalizViewModel analizViewModel =
                new ViewModelProvider(this).get(AnalizViewModel.class);

        binding = FragmentAnalizBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        ViewPager2 viewPagerNews = root.findViewById(R.id.view_pager_news_fr);

        //создадим кнопки товаров динамически
        analizViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                MaterialButton myButton;
                Set<String> category = new HashSet<>();
                //выберем только категории без дублей
                for (Product pr : products) {
                    category.add(pr.category);
                }
                 //создадим кнопки с названиями категорий
                for (String str : category) {
                    myButton = new MaterialButton(getContext());
                    myButton.setText(str);
                    myButton.setAllCaps(false);
                    myButton.setCornerRadius(10);
                    myButton.setTextColor(Color.parseColor("#FFFFFF"));
//                    myButton.setBackgroundColor(Color.parseColor("#1A6FEE"));


                    Log.d("777", str);
                    LinearLayout ll = root.findViewById(R.id.product_buttons);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(10,10,10,10 );
                    ll.addView(myButton, lp);
                }

                //разместим вертикальный список анализов на экран
                ListView listView = root.findViewById(R.id.analiz_lstview);
                AnalizArrayAdapter analizArrayAdapter = new AnalizArrayAdapter(getContext(),R.layout.analiz_product_card,products);
                listView.setAdapter(analizArrayAdapter);

            }
        });

       //получим новости от сервера и добавим их на экран
        analizViewModel.getNews().observe(getViewLifecycleOwner(), new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                NewsAdapter newsAdapter = new NewsAdapter(news);
                viewPagerNews.setAdapter(newsAdapter);

            }
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}