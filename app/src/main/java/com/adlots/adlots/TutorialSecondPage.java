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
public class TutorialSecondPage extends Fragment {
    private Context tutorialsecondcontext = null;
    private View tutorialsecondview = null;

    public static TutorialSecondPage newProduction (int position) {
        TutorialSecondPage mpage = new TutorialSecondPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstnaceState) {
        tutorialsecondcontext = container.getContext();

        tutorialsecondview = (View) from(tutorialsecondcontext).inflate(
                R.layout.activity_tutorial_second_page, container, false);
        Util.setGlobalFont(tutorialsecondcontext,tutorialsecondview);
        return tutorialsecondview;
    }

}
