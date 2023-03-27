package com.example.profi23.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.profi23.R;
import com.example.profi23.model.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.OnboardingViewHolder> {
    private List<News> onboardingItems;

    public NewsAdapter(List<News> onboardingItems) {
        this.onboardingItems = onboardingItems;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.news_layout2, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.setOnboardingData(onboardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardingItems.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder {
        private TextView textZagolovok;
        private TextView textOpisanie;
        private TextView textPrice;
        private ImageView image;

        OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            textZagolovok = itemView.findViewById(R.id.text_zagolovok);
            textOpisanie = itemView.findViewById(R.id.text_opisanie);
            textPrice = itemView.findViewById(R.id.text_price);
            image = itemView.findViewById(R.id.image_news);
        }

        void setOnboardingData(News news) {
            textZagolovok.setText(news.name);
            textOpisanie.setText(news.description);
            textPrice.setText(news.price + " " + '\u20BD');
            //здесь нужна библиотека Picasso для загрузки картинки по url в наш imageView
            Picasso.get().load(news.image).into(image);
        }
    }
}