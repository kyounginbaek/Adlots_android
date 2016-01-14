package com.adlots.adlots;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.view.LayoutInflater.from;

/**
 * Created by baekkyoungin on 2016. 1. 14..
 */
public class MainFirstPage extends Fragment {

    private Context mainfirstcontext = null;
    private View mainfirstview = null;

    public static MainFirstPage newProduction (int position) {
        MainFirstPage mpage = new MainFirstPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainfirstcontext = container.getContext();
        mainfirstview = (View) from(mainfirstcontext).inflate(
                R.layout.activity_main_first_page, container, false);
        Util.setGlobalFont(mainfirstcontext,mainfirstview);
        return mainfirstview;
    }
}
