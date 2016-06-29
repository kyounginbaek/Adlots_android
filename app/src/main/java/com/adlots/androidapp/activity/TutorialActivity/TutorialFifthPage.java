package com.adlots.androidapp.activity.TutorialActivity;

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
public class TutorialFifthPage extends Fragment {
    private Context tutorialfifthContext = null;
    private View tutorialfifthView = null;

    public static TutorialFifthPage newProduction (int position) {
        TutorialFifthPage mpage = new TutorialFifthPage();
        return mpage;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tutorialfifthContext = container.getContext();

        tutorialfifthView = (View) from(tutorialfifthContext).inflate(
                R.layout.activity_tutorial_fifth_page, container, false);
        return tutorialfifthView;
    }
}
