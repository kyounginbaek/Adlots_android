package com.adlots.adlots.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import com.adlots.adlots.R;
import com.adlots.adlots.helper.BackPressCloseHandler;
import com.adlots.adlots.helper.UnderlinePageIndicator;


public class MainActivity extends FragmentActivity {

    ViewPager MainPager;
    ViewPager BottomPager;

    Button btn_main1view, btn_main2view, btn_main3view, btn_main4view;
    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainPager = (ViewPager)findViewById(R.id.main_pager);
        MainPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager()));
        MainPager.setOffscreenPageLimit(3);

        UnderlinePageIndicator mIndicator = (UnderlinePageIndicator)findViewById(R.id.main_indicator);
        mIndicator.setViewPager(MainPager);
        mIndicator.setFades(false);

        /*
        BottomPager = (ViewPager) findViewById(R.id.main_bottom_menu_tab);
        BottomPager.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        */

        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        backPressCloseHandler.onBackPressed();
    }

    public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

        public MainFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            try{
                switch(position){
                    case 0:
                        return MainFirstPage.newProduction(position);
                    case 1:
                        return MainSecondPage.newProduction(position);
                    case 2:
                        return MainThirdPage.newProduction(position);
                    case 3:
                        return MainFourthPage.newProduction(position);
                    default:
                        return null;
                }
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
        @Override
        public int getCount() {
            return 4;
        }
    }
}
