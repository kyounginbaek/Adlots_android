package com.adlots.adlots.activity.MainActivity.MainSecondFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adlots.adlots.R;

/**
 * Created by baekkyoungin on 16. 3. 22..
 */
public class MainSecondNewitem extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.fragment_main_second_newitem,
                container, false);

        return view ;
    }
}
