package com.example.profi23.ui.analiz;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class AnalizFragment extends Fragment {

    private FragmentAnalizBinding binding;
    private static String categoryToSet; // выбранная категория
    AnalizArrayAdapter analizArrayAdapter;
    List<Product> productList = new ArrayList<>(); // товары по категориям
    List<Product> allProductList = new ArrayList<>(); // здесь сохраним товары по категориям

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



        //разместим вертикальный список анализов на экран
        ListView listView = root.findViewById(R.id.analiz_listview);
        listView.setDivider(null); // уберем разделитель
        listView.setDividerHeight(0); // уберем разделитель


        ViewPager2 viewPagerNews = root.findViewById(R.id.view_pager_news_fr);

        //свайп для скрытия/показа новостей
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    viewPagerNews.setVisibility(View.VISIBLE);
                    root.findViewById(R.id.textView13_fr).setVisibility(View.VISIBLE);
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    viewPagerNews.setVisibility(View.GONE);
                    root.findViewById(R.id.textView13_fr).setVisibility(View.GONE);
                }
                return true;
            }
        });

        //создадим кнопки товаров динамически
        analizViewModel.getCategories().observe(getViewLifecycleOwner(), new Observer<Set<String>>() {
                    @Override
                    public void onChanged(Set<String> category) {

                        MyMaterialButton myButton;
                        //создадим кнопки с названиями категорий
                        LinearLayout ll = root.findViewById(R.id.product_buttons);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        lp.setMargins(10,10,10,10 );

                        //создадим список кнопок, чтобы менять их цвет в зависмости от выбранной категории
                        List<MyMaterialButton> buttonList = new ArrayList<>();

                        for (String str : category) {
                            myButton = new MyMaterialButton(getContext());
                            myButton.setText(str);
                            myButton.setAllCaps(false);
                            myButton.setCornerRadius(10);
                            myButton.category = str;
                            myButton.setTextColor(Color.parseColor("#FFFFFF"));
                            myButton.setBackgroundColor(Color.parseColor("#1A6FEE"));
                            buttonList.add(myButton); // добавим кнопку в список
                            myButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    categoryToSet = ((MyMaterialButton)view).getText().toString();
                                    productList.clear();
                                    productList.addAll(allProductList);
                                    Log.d("777", categoryToSet);

                                    Iterator<Product> productIterator = productList.listIterator();//создаем итератор
                                    while(productIterator.hasNext()) {//до тех пор, пока в списке есть элементы

                                        Product product = productIterator.next();//получаем следующий элемент
                                        if (!product.category.equals(categoryToSet)) {
                                            productIterator.remove();//удаляем элемент
                                        }
                                    }
                                    Log.d("777", productList.toString());
                                    analizArrayAdapter.notifyDataSetChanged();

                                    //поменяем цвета кнопок
                                    for (MyMaterialButton mButton: buttonList) {
                                        if (!mButton.category.equals(categoryToSet)){
                                            mButton.setTextColor(Color.parseColor("#7E7E9A"));
                                            mButton.setBackgroundColor(Color.parseColor("#F5F5F9"));
                                        }
                                        else {
                                            mButton.setTextColor(Color.parseColor("#FFFFFF"));
                                            mButton.setBackgroundColor(Color.parseColor("#1A6FEE"));
                                        }
                                    }

                                }
                            });
//                    myButton.setBackgroundColor(Color.parseColor("#1A6FEE"));
                            Log.d("777", str);
                            ll.addView(myButton, lp);
                        }


                    }
                });

        //все товары на экран
        analizViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                allProductList.addAll(products); //сохраним в полный список
                productList.addAll(products); // сохарним в рабочий список
                analizArrayAdapter = new AnalizArrayAdapter(getContext(),R.layout.analiz_product_card, productList);
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



    // для удобства сделаем внутреннний класс кнопки с дополнительными полями
    class MyMaterialButton extends MaterialButton {

        private String category; // категория, выбираемая кнопкой

        public MyMaterialButton(@NonNull Context context) {
            super(context);
        }


    }
}