package com.adlots.adlots;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        initialize();
    }

    private void initialize() {
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                String isLogin = pref.getString("islogin", "");
                String email = pref.getString("email", "");
                if(isLogin.equals("yes")) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), TutorialActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        handler.sendEmptyMessageDelayed(0, 2000);
    }
}
