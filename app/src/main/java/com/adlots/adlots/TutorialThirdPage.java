package com.adlots.adlots;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.view.LayoutInflater.from;

/**
 * Created by baekkyoungin on 2015. 11. 18..
 */
public class TutorialThirdPage extends Fragment {
    private Context tutorialthirdcontext = null;
    private View tutorialthirdview = null;

    public static TutorialThirdPage newProduction (int position) {
        TutorialThirdPage mpage = new TutorialThirdPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstnaceState) {
        tutorialthirdcontext = container.getContext();
        tutorialthirdview = (View) from(tutorialthirdcontext).inflate(
                R.layout.activity_tutorial_third_page, container, false);
        Util.setGlobalFont(tutorialthirdcontext,tutorialthirdview);
        return tutorialthirdview;
    }
}
