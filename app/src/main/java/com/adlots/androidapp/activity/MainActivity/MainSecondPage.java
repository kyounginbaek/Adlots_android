package com.adlots.androidapp.activity.MainActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adlots.android.R;
import com.adlots.androidapp.activity.MainActivity.MainSecondFragment.MainSecondDeadline;
import com.adlots.androidapp.activity.MainActivity.MainSecondFragment.MainSecondDelivery;
import com.adlots.androidapp.activity.MainActivity.MainSecondFragment.MainSecondGiftcon;

import static android.view.LayoutInflater.from;

/**
 * Created by baekkyoungin on 2016. 1. 14..
 */
public class MainSecondPage extends Fragment {

    private Context mainsecondContext = null;
    private View mainsecondView = null;
    public static MainSecondPage staticvar;
    public Button btn_giftcon, btn_delivery, btn_deadline;
    public TextView txt_giftcon, txt_delivery, txt_deadline;
    public String strColor;

    public static MainSecondPage newProduction (int position) {
        MainSecondPage mpage = new MainSecondPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        staticvar = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainsecondContext = container.getContext();
        mainsecondView = (View) from(mainsecondContext).inflate(
                R.layout.activity_main_second_page, container, false);

        ViewGroup giftcon = (LinearLayout) mainsecondView.findViewById(R.id.main2_giftcon);
        ViewGroup delivery = (LinearLayout) mainsecondView.findViewById(R.id.main2_delivery);
        ViewGroup deadline = (LinearLayout) mainsecondView.findViewById(R.id.main2_deadline);
        final TextView itemfilter = (TextView) mainsecondView.findViewById(R.id.main2_itemfilter);

        btn_giftcon = (Button) mainsecondView.findViewById(R.id.main2_btn_giftcon);
        btn_delivery = (Button) mainsecondView.findViewById(R.id.main2_btn_delivery);
        btn_deadline = (Button) mainsecondView.findViewById(R.id.main2_btn_deadline);

        txt_giftcon = (TextView) mainsecondView.findViewById(R.id.main2_txt_giftcon);
        txt_delivery = (TextView) mainsecondView.findViewById(R.id.main2_txt_delivery);
        txt_deadline = (TextView) mainsecondView.findViewById(R.id.main2_txt_deadline);

        strColor = "#fc6e51";

        final Fragment fragment1 = new MainSecondGiftcon();
        final Fragment fragment2 = new MainSecondDelivery();
        final Fragment fragment3 = new MainSecondDeadline();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.main2_fragment, fragment1).commit(); //처음 화면
        btn_giftcon.setBackgroundResource(R.drawable.gift_click);
        txt_giftcon.setTextColor(Color.parseColor(strColor));

        itemfilter.setVisibility(View.GONE);

        giftcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.main2_fragment, fragment1).commit();
                btn_original();
                txt_original();
                btn_giftcon.setBackgroundResource(R.drawable.gift_click);
                txt_giftcon.setTextColor(Color.parseColor(strColor));

                itemfilter.setVisibility(View.GONE);
            }
        });

        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.main2_fragment, fragment2).commit();
                btn_original();
                txt_original();
                btn_delivery.setBackgroundResource(R.drawable.shipping_click);
                txt_delivery.setTextColor(Color.parseColor(strColor));

                itemfilter.setVisibility(View.GONE);
            }
        });

        deadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.main2_fragment, fragment3).commit();
                btn_original();
                txt_original();
                btn_deadline.setBackgroundResource(R.drawable.time_click);
                txt_deadline.setTextColor(Color.parseColor(strColor));

                itemfilter.setVisibility(View.VISIBLE);
                itemfilter.setText("(마감 5일 이내)");
            }
        });

        // 새로고침 버튼 클릭 시 listview 화면 새로고침
        TextView refresh = (TextView) mainsecondView.findViewById(R.id.main2_txtbtn_refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                Fragment currentFragment = getChildFragmentManager().findFragmentById(R.id.main2_fragment);
                transaction.detach(currentFragment);
                transaction.attach(currentFragment);
                transaction.commit();
            }
        });

        return mainsecondView;
    }

    public void btn_original() {
        btn_giftcon.setBackgroundResource(R.drawable.gift);
        btn_delivery.setBackgroundResource(R.drawable.shipping);
        btn_deadline.setBackgroundResource(R.drawable.time);
    }

    public void txt_original() {
        String strColor = "#4d4d4d";
        txt_giftcon.setTextColor(Color.parseColor(strColor));
        txt_delivery.setTextColor(Color.parseColor(strColor));
        txt_deadline.setTextColor(Color.parseColor(strColor));
    }
}

