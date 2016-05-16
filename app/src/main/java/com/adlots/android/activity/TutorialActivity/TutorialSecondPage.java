package com.adlots.android.activity.TutorialActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adlots.android.R;

import static android.view.LayoutInflater.from;

/**
 * Created by baekkyoungin on 2015. 11. 18..
 */
public class TutorialSecondPage extends Fragment {
    private Context tutorialsecondContext = null;
    private View tutorialsecondView = null;

    public static TutorialSecondPage newProduction (int position) {
        TutorialSecondPage mpage = new TutorialSecondPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tutorialsecondContext = container.getContext();

        tutorialsecondView = (View) from(tutorialsecondContext).inflate(
                R.layout.activity_tutorial_second_page, container, false);
        return tutorialsecondView;
    }

}
