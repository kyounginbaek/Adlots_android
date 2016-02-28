package com.adlots.adlots.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adlots.adlots.R;
import com.adlots.adlots.helper.DataHolder;

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

        TextView nickname = (TextView) mainthirdview.findViewById(R.id.nickname);
        nickname.setText(DataHolder.nickname);

        TextView email = (TextView) mainthirdview.findViewById(R.id.email);
        email.setText(DataHolder.email);

        ViewGroup userinfochange = (LinearLayout) mainthirdview.findViewById(R.id.userinfochange);
        userinfochange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainthirdcontext, MainThirdInfoPopup.class);
                startActivity(intent);
            }
        });

        ViewGroup logout = (LinearLayout) mainthirdview.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder.login = false;
                Intent intent = new Intent(mainthirdcontext, SigninActivity.class);
                startActivity(intent);
            }
        });

        return mainthirdview;
    }

}
