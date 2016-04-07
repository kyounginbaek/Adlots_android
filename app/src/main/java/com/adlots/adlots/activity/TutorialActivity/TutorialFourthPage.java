package com.adlots.adlots.activity.TutorialActivity;

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
public class TutorialFourthPage extends Fragment {
    private Context tutorialfourthContext = null;
    private View tutorialfourthView = null;

    public static TutorialFourthPage newProduction (int position) {
        TutorialFourthPage mpage = new TutorialFourthPage();
        return mpage;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tutorialfourthContext = container.getContext();

        tutorialfourthView = (View) from(tutorialfourthContext).inflate(
                R.layout.activity_tutorial_fourth_page, container, false);
        return tutorialfourthView;
    }
}
