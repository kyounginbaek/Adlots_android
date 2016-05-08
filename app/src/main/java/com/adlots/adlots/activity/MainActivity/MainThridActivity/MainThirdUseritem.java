package com.adlots.adlots.activity.MainActivity.MainThridActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adlots.adlots.R;

/**
 * Created by baekkyoungin on 16. 3. 24..
 */
public class MainThirdUseritem extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.fragment_main_third_user_lots,
                container, false);

        return view ;
    }

}
