package com.adlots.androidapp.activity.MainActivity.MainFourthActivity;

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
public class MainFourthFaqActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_main_fourth_faq);

        ImageView btn_x = (ImageView) findViewById(R.id.main4_faq_btn_x);
        btn_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ViewGroup faq1 = (LinearLayout) findViewById(R.id.main4_faq1);
        ViewGroup faq2 = (LinearLayout) findViewById(R.id.main4_faq2);
        ViewGroup faq3 = (LinearLayout) findViewById(R.id.main4_faq3);
        ViewGroup faq4 = (LinearLayout) findViewById(R.id.main4_faq4);
        ViewGroup faq5 = (LinearLayout) findViewById(R.id.main4_faq5);
        ViewGroup faq6 = (LinearLayout) findViewById(R.id.main4_faq6);
        ViewGroup faq7 = (LinearLayout) findViewById(R.id.main4_faq7);
        ViewGroup faq8 = (LinearLayout) findViewById(R.id.main4_faq8);
        ViewGroup faq9 = (LinearLayout) findViewById(R.id.main4_faq9);

        final ViewGroup faq1_answer = (LinearLayout) findViewById(R.id.main4_faq1_answer);
        final ViewGroup faq2_answer = (LinearLayout) findViewById(R.id.main4_faq2_answer);
        final ViewGroup faq3_answer = (LinearLayout) findViewById(R.id.main4_faq3_answer);
        final ViewGroup faq4_answer = (LinearLayout) findViewById(R.id.main4_faq4_answer);
        final ViewGroup faq5_answer = (LinearLayout) findViewById(R.id.main4_faq5_answer);
        final ViewGroup faq6_answer = (LinearLayout) findViewById(R.id.main4_faq6_answer);
        final ViewGroup faq7_answer = (LinearLayout) findViewById(R.id.main4_faq7_answer);
        final ViewGroup faq8_answer = (LinearLayout) findViewById(R.id.main4_faq8_answer);
        final ViewGroup faq9_answer = (LinearLayout) findViewById(R.id.main4_faq9_answer);

        faq1_answer.setVisibility(View.GONE);
        faq2_answer.setVisibility(View.GONE);
        faq3_answer.setVisibility(View.GONE);
        faq4_answer.setVisibility(View.GONE);
        faq5_answer.setVisibility(View.GONE);
        faq6_answer.setVisibility(View.GONE);
        faq7_answer.setVisibility(View.GONE);
        faq8_answer.setVisibility(View.GONE);
        faq9_answer.setVisibility(View.GONE);

        faq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = faq1_answer.getVisibility();
                if (result == View.VISIBLE) {
                    faq1_answer.setVisibility(View.GONE);
                } else {
                    faq1_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        faq2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = faq2_answer.getVisibility();
                if (result == View.VISIBLE) {
                    faq2_answer.setVisibility(View.GONE);
                } else {
                    faq2_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        faq3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = faq3_answer.getVisibility();
                if (result == View.VISIBLE) {
                    faq3_answer.setVisibility(View.GONE);
                } else {
                    faq3_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        faq4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = faq4_answer.getVisibility();
                if (result == View.VISIBLE) {
                    faq4_answer.setVisibility(View.GONE);
                } else {
                    faq4_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        faq5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = faq5_answer.getVisibility();
                if (result == View.VISIBLE) {
                    faq5_answer.setVisibility(View.GONE);
                } else {
                    faq5_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        faq6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = faq6_answer.getVisibility();
                if (result == View.VISIBLE) {
                    faq6_answer.setVisibility(View.GONE);
                } else {
                    faq6_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        faq6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = faq6_answer.getVisibility();
                if (result == View.VISIBLE) {
                    faq6_answer.setVisibility(View.GONE);
                } else {
                    faq6_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        faq7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = faq7_answer.getVisibility();
                if (result == View.VISIBLE) {
                    faq7_answer.setVisibility(View.GONE);
                } else {
                    faq7_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        faq8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = faq8_answer.getVisibility();
                if (result == View.VISIBLE) {
                    faq8_answer.setVisibility(View.GONE);
                } else {
                    faq8_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        faq9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = faq9_answer.getVisibility();
                if (result == View.VISIBLE) {
                    faq9_answer.setVisibility(View.GONE);
                } else {
                    faq9_answer.setVisibility(View.VISIBLE);
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