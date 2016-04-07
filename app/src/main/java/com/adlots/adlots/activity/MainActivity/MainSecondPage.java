package com.adlots.adlots.activity.MainActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.adlots.adlots.R;
import com.adlots.adlots.activity.MainActivity.MainSecondFragment.MainSecondDeadline;
import com.adlots.adlots.activity.MainActivity.MainSecondFragment.MainSecondDelivery;
import com.adlots.adlots.activity.MainActivity.MainSecondFragment.MainSecondGiftcon;

import static android.view.LayoutInflater.from;

/**
 * Created by baekkyoungin on 2016. 1. 14..
 */
public class MainSecondPage extends Fragment {

    private Context mainsecondContext = null;
    private View mainsecondView = null;

    public static MainSecondPage newProduction (int position) {
        MainSecondPage mpage = new MainSecondPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainsecondContext = container.getContext();
        mainsecondView = (View) from(mainsecondContext).inflate(
                R.layout.activity_main_second_page, container, false);

        final Fragment fragment1 = new MainSecondGiftcon();
        final Fragment fragment2 = new MainSecondDelivery();
        final Fragment fragment3 = new MainSecondDeadline();
        final FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.main2_fragment, fragment1).commit(); //처음 화면


        ViewGroup giftcon = (LinearLayout) mainsecondView.findViewById(R.id.giftcon);
        giftcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.main2_fragment, fragment1).commit();
            }
        });

        ViewGroup delivery = (LinearLayout) mainsecondView.findViewById(R.id.delivery);
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.main2_fragment, fragment2).commit();
            }
        });

        ViewGroup deadline = (LinearLayout) mainsecondView.findViewById(R.id.deadline);
        deadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.main2_fragment, fragment3).commit();
            }
        });

        return mainsecondView;
    }
}

