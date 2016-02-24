package com.adlots.adlots.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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

        TextView email = (TextView) mainthirdview.findViewById(R.id.email);
        email.setText(DataHolder.email);

        TextView userinfochange = (TextView) mainthirdview.findViewById(R.id.userinfochange);
        userinfochange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //새로운 창이 떠서 개인정보를 바꿀 수 있도록
            }
        });
        userinfochange.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe06b6b6b, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

        TextView logout = (TextView) mainthirdview.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder.login = false;
                Intent intent = new Intent(mainthirdcontext, SigninActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe06b6b6b, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

        return mainthirdview;
    }
}
