package com.adlots.adlots.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adlots.adlots.R;

import static android.view.LayoutInflater.from;

/**
 * Created by baekkyoungin on 2016. 1. 14..
 */
public class MainSecondPage extends Fragment {

    private Context mainsecondcontext = null;
    private View mainsecondview = null;

    public static MainSecondPage newProduction (int position) {
        MainSecondPage mpage = new MainSecondPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainsecondcontext = container.getContext();
        mainsecondview = (View) from(mainsecondcontext).inflate(
                R.layout.activity_main_second_page, container, false);

        TextView main2_point = (TextView) mainsecondview.findViewById(R.id.main2_point);

        ViewGroup giftcon = (LinearLayout) mainsecondview.findViewById(R.id.giftcon);
        giftcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // giftcon 프래그먼트 출력
            }
        });

        ViewGroup newdelivery = (LinearLayout) mainsecondview.findViewById(R.id.newdelivery);
        newdelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // newdelivery 프래그먼트 출력
            }
        });

        ViewGroup closedelivery = (LinearLayout) mainsecondview.findViewById(R.id.closedelivery);
        closedelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // closedelivery 프래그먼트 출력
            }
        });

        return mainsecondview;
    }
}

