package com.adlots.android.activity.MainActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adlots.android.R;

import static android.view.LayoutInflater.from;

/**
 * Created by baekkyoungin on 2016. 1. 14..
 */
public class MainFirstPage extends Fragment {

    private Context mainfirstContext = null;
    private View mainfirstView = null;

    public static MainFirstPage newProduction (int position) {
        MainFirstPage mpage = new MainFirstPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainfirstContext = container.getContext();
        mainfirstView = (View) from(mainfirstContext).inflate(
                R.layout.activity_main_first_page, container, false);

        return mainfirstView;
    }
}
