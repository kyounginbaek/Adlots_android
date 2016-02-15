package com.adlots.adlots.helper;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by baekkyoungin on 2015. 12. 31..
 */
public class BackPressCloseHandler {
    private long backKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;


    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            programShutdown();
            toast.cancel();
        }
    }

    private void showGuide() {
        toast = Toast.makeText(activity, "뒤로 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void programShutdown() {
        activity .moveTaskToBack(true);
        activity .finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
