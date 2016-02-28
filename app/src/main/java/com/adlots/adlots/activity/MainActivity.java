package com.adlots.adlots.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;

import com.adlots.adlots.R;
import com.adlots.adlots.helper.BackPressCloseHandler;
import com.adlots.adlots.helper.MainFragmentPagerAdapter;
import com.adlots.adlots.helper.PageIndicator;
import com.adlots.adlots.helper.UnderlinePageIndicator;


public class MainActivity extends FragmentActivity {

    ViewPager MainPager;
    PageIndicator mIndicator;

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainPager = (ViewPager)findViewById(R.id.main_pager);
        MainPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager()));

        UnderlinePageIndicator mIndicator = (UnderlinePageIndicator)findViewById(R.id.main_indicator);
        mIndicator.setViewPager(MainPager);
        mIndicator.setFades(false);

        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        backPressCloseHandler.onBackPressed();
    }

    public void popupWindow() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.7f;
        getWindow().setAttributes(layoutParams);
        setContentView(R.layout.main_third_info_popup);
    }
}
