package com.adlots.adlots.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by baekkyoungin on 2015. 11. 18..
 */
public class Util {
    public static void setGlobalFont(Context context, View view) {
        if (view != null) {
            if (view instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) view;
                int len = vg.getChildCount();
                for (int i = 0; i < len; i++) {
                    View v = vg.getChildAt(i);
                    if (v instanceof TextView) {
                        ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(),"NanumBarunGothic.ttf"));
                    }
                    setGlobalFont(context, v);
                }
            }
        } else {
            Log.e("setGlobalFont", "This is null  ");
        }
    }
}