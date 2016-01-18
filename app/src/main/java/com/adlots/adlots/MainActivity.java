package com.adlots.adlots;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

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
}
