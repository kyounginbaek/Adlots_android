package com.adlots.android.activity.MainActivity.MainFourthActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.adlots.android.R;

/**
 * Created by baekkyoungin on 16. 5. 17..
 */
public class MainFourthUserPolicyActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_main_fourth_userpolicy);

        ImageView btn_x = (ImageView) findViewById(R.id.main4_userpolicy_btn_x);
        btn_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ViewGroup userpolicy1 = (LinearLayout) findViewById(R.id.main4_userpolicy1);
        ViewGroup userpolicy2 = (LinearLayout) findViewById(R.id.main4_userpolicy2);
        ViewGroup userpolicy3 = (LinearLayout) findViewById(R.id.main4_userpolicy3);
        ViewGroup userpolicy4 = (LinearLayout) findViewById(R.id.main4_userpolicy4);
        ViewGroup userpolicy5 = (LinearLayout) findViewById(R.id.main4_userpolicy5);
        ViewGroup userpolicy6 = (LinearLayout) findViewById(R.id.main4_userpolicy6);
        ViewGroup userpolicy7 = (LinearLayout) findViewById(R.id.main4_userpolicy7);
        ViewGroup userpolicy8 = (LinearLayout) findViewById(R.id.main4_userpolicy8);
        ViewGroup userpolicy9 = (LinearLayout) findViewById(R.id.main4_userpolicy9);
        ViewGroup userpolicy10 = (LinearLayout) findViewById(R.id.main4_userpolicy10);
        ViewGroup userpolicy11 = (LinearLayout) findViewById(R.id.main4_userpolicy11);

        final ViewGroup userpolicy1_answer = (LinearLayout) findViewById(R.id.main4_userpolicy1_answer);
        final ViewGroup userpolicy2_answer = (LinearLayout) findViewById(R.id.main4_userpolicy2_answer);
        final ViewGroup userpolicy3_answer = (LinearLayout) findViewById(R.id.main4_userpolicy3_answer);
        final ViewGroup userpolicy4_answer = (LinearLayout) findViewById(R.id.main4_userpolicy4_answer);
        final ViewGroup userpolicy5_answer = (LinearLayout) findViewById(R.id.main4_userpolicy5_answer);
        final ViewGroup userpolicy6_answer = (LinearLayout) findViewById(R.id.main4_userpolicy6_answer);
        final ViewGroup userpolicy7_answer = (LinearLayout) findViewById(R.id.main4_userpolicy7_answer);
        final ViewGroup userpolicy8_answer = (LinearLayout) findViewById(R.id.main4_userpolicy8_answer);
        final ViewGroup userpolicy9_answer = (LinearLayout) findViewById(R.id.main4_userpolicy9_answer);
        final ViewGroup userpolicy10_answer = (LinearLayout) findViewById(R.id.main4_userpolicy10_answer);
        final ViewGroup userpolicy11_answer = (LinearLayout) findViewById(R.id.main4_userpolicy11_answer);

        userpolicy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = userpolicy1_answer.getVisibility();
                if (result == View.VISIBLE) {
                    userpolicy1_answer.setVisibility(View.GONE);
                } else {
                    userpolicy1_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        userpolicy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = userpolicy2_answer.getVisibility();
                if (result == View.VISIBLE) {
                    userpolicy2_answer.setVisibility(View.GONE);
                } else {
                    userpolicy2_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        userpolicy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = userpolicy3_answer.getVisibility();
                if (result == View.VISIBLE) {
                    userpolicy3_answer.setVisibility(View.GONE);
                } else {
                    userpolicy3_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        userpolicy4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = userpolicy4_answer.getVisibility();
                if (result == View.VISIBLE) {
                    userpolicy4_answer.setVisibility(View.GONE);
                } else {
                    userpolicy4_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        userpolicy5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = userpolicy5_answer.getVisibility();
                if (result == View.VISIBLE) {
                    userpolicy5_answer.setVisibility(View.GONE);
                } else {
                    userpolicy5_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        userpolicy6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = userpolicy6_answer.getVisibility();
                if (result == View.VISIBLE) {
                    userpolicy6_answer.setVisibility(View.GONE);
                } else {
                    userpolicy6_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        userpolicy7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = userpolicy7_answer.getVisibility();
                if (result == View.VISIBLE) {
                    userpolicy7_answer.setVisibility(View.GONE);
                } else {
                    userpolicy7_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        userpolicy8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = userpolicy8_answer.getVisibility();
                if (result == View.VISIBLE) {
                    userpolicy8_answer.setVisibility(View.GONE);
                } else {
                    userpolicy8_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        userpolicy9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = userpolicy9_answer.getVisibility();
                if (result == View.VISIBLE) {
                    userpolicy9_answer.setVisibility(View.GONE);
                } else {
                    userpolicy9_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        userpolicy10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = userpolicy10_answer.getVisibility();
                if (result == View.VISIBLE) {
                    userpolicy10_answer.setVisibility(View.GONE);
                } else {
                    userpolicy10_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        userpolicy11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = userpolicy11_answer.getVisibility();
                if (result == View.VISIBLE) {
                    userpolicy11_answer.setVisibility(View.GONE);
                } else {
                    userpolicy11_answer.setVisibility(View.VISIBLE);
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}