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
import com.adlots.adlots.activity.MainActivity.MainSecondFragment.MainSecondCloseitem;
import com.adlots.adlots.activity.MainActivity.MainSecondFragment.MainSecondGiftcon;
import com.adlots.adlots.activity.MainActivity.MainSecondFragment.MainSecondNewitem;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainsecondcontext = container.getContext();
        mainsecondview = (View) from(mainsecondcontext).inflate(
                R.layout.activity_main_second_page, container, false);

        final Fragment fragment1 = new MainSecondGiftcon();
        final Fragment fragment2 = new MainSecondNewitem();
        final Fragment fragment3 = new MainSecondCloseitem();
        final FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.child_fragment, fragment1).commit(); //처음 화면

        ViewGroup giftcon = (LinearLayout) mainsecondview.findViewById(R.id.giftcon);
        giftcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.main2_fragment, fragment1).commit();
            }
        });

        ViewGroup newitem = (LinearLayout) mainsecondview.findViewById(R.id.newitem);
        newitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.child_fragment, fragment2).commit();
            }
        });

        ViewGroup closeitem = (LinearLayout) mainsecondview.findViewById(R.id.closeitem);
        closeitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.child_fragment, fragment3).commit();
            }
        });

        return mainsecondview;
    }
}

