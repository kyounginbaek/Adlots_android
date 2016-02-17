package com.adlots.adlots.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adlots.adlots.R;

import static android.view.LayoutInflater.from;

/**
 * Created by baekkyoungin on 2015. 11. 18..
 */
public class TutorialFirstPage extends Fragment {
    private Context tutorialfirstcontext = null;
    private View tutorialfirstview = null;

    public static TutorialFirstPage newProduction (int position) {
        TutorialFirstPage mpage = new TutorialFirstPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tutorialfirstcontext = container.getContext();
        tutorialfirstview = (View) from(tutorialfirstcontext).inflate(
                R.layout.activity_tutorial_first_page, container, false);
        return tutorialfirstview;
    }
}
