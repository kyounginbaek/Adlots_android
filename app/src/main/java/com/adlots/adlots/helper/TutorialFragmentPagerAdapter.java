package com.adlots.adlots.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.adlots.adlots.activity.TutorialFifthPage;
import com.adlots.adlots.activity.TutorialFirstPage;
import com.adlots.adlots.activity.TutorialFourthPage;
import com.adlots.adlots.activity.TutorialSecondPage;
import com.adlots.adlots.activity.TutorialThirdPage;

/**
 * Created by baekkyoungin on 2015. 11. 18..
 */
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