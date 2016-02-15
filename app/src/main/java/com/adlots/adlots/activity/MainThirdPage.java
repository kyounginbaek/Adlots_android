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
public class MainThirdPage extends Fragment {

    private Context mainthirdcontext = null;
    private View mainthirdview = null;

    public static MainThirdPage newProduction (int position) {
        MainThirdPage mpage = new MainThirdPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainthirdcontext = container.getContext();
        mainthirdview = (View) from(mainthirdcontext).inflate(
                R.layout.activity_main_third_page, container, false);
        Util.setGlobalFont(mainthirdcontext, mainthirdview);
        return mainthirdview;
    }
}
