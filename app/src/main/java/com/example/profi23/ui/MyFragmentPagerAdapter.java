package com.example.profi23.ui;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    IntPager intPager;
    public MyFragmentPagerAdapter(@NonNull FragmentManager fm, IntPager intPager) {
        super(fm);
        this.intPager = intPager;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ZastavkaFragment();
        } else if (position == 1) {
            return new BoardFragment1(intPager);
        } else if (position == 2) {
            return new BoardFragment2(intPager);
        } else {
            return new BoardFragment3();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
