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
public class MainFourthServicePolicyActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_main_fourth_servicepolicy);

        ImageView btn_x = (ImageView) findViewById(R.id.main4_servicepolicy_btn_x);
        btn_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ViewGroup servicepolicy1 = (LinearLayout) findViewById(R.id.main4_servicepolicy1);
        ViewGroup servicepolicy2 = (LinearLayout) findViewById(R.id.main4_servicepolicy2);
        ViewGroup servicepolicy3 = (LinearLayout) findViewById(R.id.main4_servicepolicy3);
        ViewGroup servicepolicy4 = (LinearLayout) findViewById(R.id.main4_servicepolicy4);
        ViewGroup servicepolicy5 = (LinearLayout) findViewById(R.id.main4_servicepolicy5);
        ViewGroup servicepolicy6 = (LinearLayout) findViewById(R.id.main4_servicepolicy6);
        ViewGroup servicepolicy7 = (LinearLayout) findViewById(R.id.main4_servicepolicy7);
        ViewGroup servicepolicy8 = (LinearLayout) findViewById(R.id.main4_servicepolicy8);
        ViewGroup servicepolicy9 = (LinearLayout) findViewById(R.id.main4_servicepolicy9);
        ViewGroup servicepolicy10 = (LinearLayout) findViewById(R.id.main4_servicepolicy10);
        ViewGroup servicepolicy11 = (LinearLayout) findViewById(R.id.main4_servicepolicy11);
        ViewGroup servicepolicy12 = (LinearLayout) findViewById(R.id.main4_servicepolicy12);
        ViewGroup servicepolicy13 = (LinearLayout) findViewById(R.id.main4_servicepolicy13);
        ViewGroup servicepolicy14 = (LinearLayout) findViewById(R.id.main4_servicepolicy14);
        ViewGroup servicepolicy15 = (LinearLayout) findViewById(R.id.main4_servicepolicy15);
        ViewGroup servicepolicy16 = (LinearLayout) findViewById(R.id.main4_servicepolicy16);
        ViewGroup servicepolicy17 = (LinearLayout) findViewById(R.id.main4_servicepolicy17);
        ViewGroup servicepolicy18 = (LinearLayout) findViewById(R.id.main4_servicepolicy18);
        ViewGroup servicepolicy19 = (LinearLayout) findViewById(R.id.main4_servicepolicy19);
        ViewGroup servicepolicy20 = (LinearLayout) findViewById(R.id.main4_servicepolicy20);

        final ViewGroup servicepolicy1_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy1_answer);
        final ViewGroup servicepolicy2_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy2_answer);
        final ViewGroup servicepolicy3_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy3_answer);
        final ViewGroup servicepolicy4_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy4_answer);
        final ViewGroup servicepolicy5_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy5_answer);
        final ViewGroup servicepolicy6_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy6_answer);
        final ViewGroup servicepolicy7_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy7_answer);
        final ViewGroup servicepolicy8_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy8_answer);
        final ViewGroup servicepolicy9_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy9_answer);
        final ViewGroup servicepolicy10_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy10_answer);
        final ViewGroup servicepolicy11_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy11_answer);
        final ViewGroup servicepolicy12_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy12_answer);
        final ViewGroup servicepolicy13_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy13_answer);
        final ViewGroup servicepolicy14_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy14_answer);
        final ViewGroup servicepolicy15_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy15_answer);
        final ViewGroup servicepolicy16_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy16_answer);
        final ViewGroup servicepolicy17_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy17_answer);
        final ViewGroup servicepolicy18_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy18_answer);
        final ViewGroup servicepolicy19_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy19_answer);
        final ViewGroup servicepolicy20_answer = (LinearLayout) findViewById(R.id.main4_servicepolicy20_answer);

        servicepolicy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy1_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy1_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy1_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        servicepolicy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy2_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy2_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy2_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        servicepolicy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy3_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy3_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy3_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        servicepolicy4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy4_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy4_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy4_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        servicepolicy5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy5_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy5_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy5_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        servicepolicy6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy6_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy6_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy6_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        servicepolicy7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy7_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy7_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy7_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        servicepolicy8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy8_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy8_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy8_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        servicepolicy9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy9_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy9_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy9_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        servicepolicy10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy10_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy10_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy10_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        servicepolicy11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy11_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy11_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy11_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        servicepolicy12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy12_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy12_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy12_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        servicepolicy13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy13_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy13_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy13_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        servicepolicy14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy14_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy14_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy14_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        servicepolicy15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy15_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy15_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy15_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        servicepolicy16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy16_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy16_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy16_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        servicepolicy17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy17_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy17_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy17_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        servicepolicy18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy18_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy18_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy18_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        servicepolicy19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy19_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy19_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy19_answer.setVisibility(View.VISIBLE);
                }
            }
        });

        servicepolicy20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = servicepolicy20_answer.getVisibility();
                if (result == View.VISIBLE) {
                    servicepolicy20_answer.setVisibility(View.GONE);
                } else {
                    servicepolicy20_answer.setVisibility(View.VISIBLE);
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