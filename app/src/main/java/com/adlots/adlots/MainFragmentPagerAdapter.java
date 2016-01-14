package com.adlots.adlots;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by baekkyoungin on 2016. 1. 14..
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
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
