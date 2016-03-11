package com.adlots.adlots.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.adlots.adlots.R;
import com.adlots.adlots.helper.BackPressCloseHandler;
import com.adlots.adlots.helper.UnderlinePageIndicator;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    ViewPager MainPager;
    public Button btn_mainpage1, btn_mainpage2, btn_mainpage3, btn_mainpage4;
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

        btn_mainpage1 = (Button)findViewById(R.id.btn_mainpage1);
        btn_mainpage2 = (Button)findViewById(R.id.btn_mainpage2);
        btn_mainpage3 = (Button)findViewById(R.id.btn_mainpage3);
        btn_mainpage4 = (Button)findViewById(R.id.btn_mainpage4);

        btn_mainpage1.setOnClickListener(this);
        btn_mainpage2.setOnClickListener(this);
        btn_mainpage3.setOnClickListener(this);
        btn_mainpage4.setOnClickListener(this);

        btn_change(0);
        MainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                switch(position){
                    case 0:
                        btn_change(0);
                        break;
                    case 1:
                        btn_change(1);
                        break;
                    case 2:
                        btn_change(2);
                        break;
                    case 3:
                        btn_change(3);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    @Override
    public void onClick (View v) {
        switch(v.getId()){
            case R.id.btn_mainpage1:
                MainPager.setCurrentItem(0, true);
                break;
            case R.id.btn_mainpage2:
                MainPager.setCurrentItem(1, true);
                break;
            case R.id.btn_mainpage3:
                MainPager.setCurrentItem(2, true);
                break;
            case R.id.btn_mainpage4:
                MainPager.setCurrentItem(3, true);
                break;
        }
    }

    public void btn_original() {
        btn_mainpage1.setBackgroundResource(R.drawable.home);
        btn_mainpage2.setBackgroundResource(R.drawable.cart);
        btn_mainpage3.setBackgroundResource(R.drawable.person);
        btn_mainpage4.setBackgroundResource(R.drawable.setting);
    }

    public void btn_change(int mPageNumber) {
        switch (mPageNumber) {
            case 0:
                btn_original();
                btn_mainpage1.setBackgroundResource(R.drawable.home_blue);
                break;
            case 1:
                btn_original();
                btn_mainpage2.setBackgroundResource(R.drawable.cart_blue);
                break;
            case 2:
                btn_original();
                btn_mainpage3.setBackgroundResource(R.drawable.person_blue);
                break;
            case 3:
                btn_original();
                btn_mainpage4.setBackgroundResource(R.drawable.setting_blue);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        backPressCloseHandler.onBackPressed();
    }

    public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

        public MainFragmentPagerAdapter(FragmentManager fm) {super(fm);}

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
