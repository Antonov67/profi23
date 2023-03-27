package com.example.profi23.ui.analiz;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.profi23.R;
import com.example.profi23.controller.NewsAdapter;
import com.example.profi23.databinding.FragmentAnalizBinding;
import com.example.profi23.model.News;
import com.example.profi23.model.Product;

import java.util.List;


public class AnalizFragment extends Fragment {

    private FragmentAnalizBinding binding;

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
                Button myButton;
                for (Product pr : products) {
                    myButton = new Button(getContext());
                    myButton.setText(pr.category);
                    Log.d("777", pr.category);
                    LinearLayout ll = root.findViewById(R.id.product_buttons);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    ll.addView(myButton, lp);
                }

            }
        });

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