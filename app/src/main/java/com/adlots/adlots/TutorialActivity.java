package com.adlots.adlots;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class TutorialActivity extends FragmentActivity {

    ViewPager TutorialPager;
    PageIndicator mIndicator;

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        TutorialPager = (ViewPager)findViewById(R.id.tutorial_pager);
        TutorialPager.setAdapter(new TutorialFragmentPagerAdapter(getSupportFragmentManager()));

        mIndicator = (CirclePageIndicator)findViewById(R.id.tutorial_indicator);
        mIndicator.setViewPager(TutorialPager);

        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        backPressCloseHandler.onBackPressed();
    }
}
