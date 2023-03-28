package com.example.profi23.controller;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.profi23.R;
import com.example.profi23.model.Product;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class AnalizArrayAdapter extends ArrayAdapter<Product> {

    private List<Product> products;
    private Context context;


    public AnalizArrayAdapter(Context context, int resource, @NonNull List<Product> products) {
        super(context, resource, products);
        products = new ArrayList<>();
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.analiz_product_card,parent,false);
        TextView textOpis = view.findViewById(R.id.text_view_analiz_opis);
        TextView textPrice = view.findViewById(R.id.text_view_price);
        TextView textDays = view.findViewById(R.id.text_view_days);
        MaterialButton button = view.findViewById(R.id.button_add_to_card);

        textOpis.setText(products.get(position).description);
        textPrice.setText(products.get(position).price + '\u20BD');
        textDays.setText(products.get(position).timeResult);

        return view;

    }
}
