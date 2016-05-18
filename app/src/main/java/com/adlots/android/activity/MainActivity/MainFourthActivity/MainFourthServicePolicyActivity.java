package com.adlots.android.activity.MainActivity.MainFourthActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

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
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}