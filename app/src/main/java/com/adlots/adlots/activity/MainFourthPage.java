package com.adlots.adlots.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adlots.adlots.R;
import com.adlots.adlots.helper.Util;

import static android.view.LayoutInflater.from;

/**
 * Created by baekkyoungin on 2016. 1. 14..
 */
public class MainFourthPage extends Fragment {

    private Context mainfourthcontext = null;
    private View mainfourthview = null;

    public static MainFourthPage newProduction (int position) {
        MainFourthPage mpage = new MainFourthPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainfourthcontext = container.getContext();
        mainfourthview = (View) from(mainfourthcontext).inflate(
                R.layout.activity_main_fourth_page, container, false);
        Util.setGlobalFont(mainfourthcontext, mainfourthview);
        return mainfourthview;
    }
}
