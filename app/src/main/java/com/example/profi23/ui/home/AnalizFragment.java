package com.example.profi23.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.profi23.R;
import com.example.profi23.controller.NewsAdapter;
import com.example.profi23.databinding.FragmentAnalizBinding;
import com.example.profi23.model.News;

import java.util.List;


public class AnalizFragment extends Fragment {

    private FragmentAnalizBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        AnalizViewModel homeViewModel =
                new ViewModelProvider(this).get(AnalizViewModel.class);

        binding = FragmentAnalizBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ViewPager2 viewPagerNews = root.findViewById(R.id.view_pager_news_fr);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<List<News>>() {
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