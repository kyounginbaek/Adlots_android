package com.adlots.adlots.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.adlots.adlots.R;
import com.adlots.adlots.helper.BackPressCloseHandler;
import com.adlots.adlots.helper.CirclePageIndicator;
import com.adlots.adlots.helper.DataHolder;
import com.adlots.adlots.helper.PageIndicator;
import com.adlots.adlots.helper.TutorialFragmentPagerAdapter;

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
        if (DataHolder.login){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else {
            // TODO Auto-generated method stub
            backPressCloseHandler.onBackPressed();
        }
    }
}
