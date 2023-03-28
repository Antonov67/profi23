package com.example.profi23.controller;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.profi23.R;
import com.example.profi23.model.Product;

public class AnalizArrayAdapter extends ArrayAdapter<Product> {

    private Product[] products;
    private Context context;


    public AnalizArrayAdapter(@NonNull Context context, int resource, @NonNull Product[] products) {
        super(context, resource, products);
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.analiz_product_card,parent,false);

        return super.getView(position, convertView, parent);

    }
}
