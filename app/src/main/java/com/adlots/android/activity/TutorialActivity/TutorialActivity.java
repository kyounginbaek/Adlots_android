package com.adlots.android.activity.TutorialActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.adlots.android.R;
import com.adlots.android.helper.BackPressCloseHandler;
import com.adlots.android.helper.CirclePageIndicator;
import com.adlots.android.helper.PageIndicator;

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
        TutorialPager.setOffscreenPageLimit(3);

        mIndicator = (CirclePageIndicator)findViewById(R.id.tutorial_indicator);
        mIndicator.setViewPager(TutorialPager);

        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    @Override
    public void onBackPressed() {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String login = pref.getString("login", "");

        if (login.equals("yes")){
            this.finish();
        } else {
            // TODO Auto-generated method stub
            backPressCloseHandler.onBackPressed();
        }
    }

    public class TutorialFragmentPagerAdapter extends FragmentPagerAdapter {

        public TutorialFragmentPagerAdapter(FragmentManager fm) {super(fm);}

        @Override
        public Fragment getItem(int position) {
            try{
                switch(position){
                    case 0:
                        return TutorialFirstPage.newProduction(position);
                    case 1:
                        return TutorialSecondPage.newProduction(position);
                    case 2:
                        return TutorialThirdPage.newProduction(position);
                    case 3:
                        return TutorialFourthPage.newProduction(position);
                    case 4:
                        return TutorialFifthPage.newProduction(position);
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
            return 5;
        }
    }
}
