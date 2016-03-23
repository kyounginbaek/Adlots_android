package com.adlots.adlots.activity.TutorialActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.adlots.adlots.R;
import com.adlots.adlots.activity.SigninActivity;

import static android.view.LayoutInflater.from;

/**
 * Created by baekkyoungin on 2015. 11. 18..
 */
public class TutorialFifthPage extends Fragment {
    private Context tutorialfifthcontext = null;
    private View tutorialfifthview = null;

    Button btn_goto_signin;

    public static TutorialFifthPage newProduction (int position) {
        TutorialFifthPage mpage = new TutorialFifthPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tutorialfifthcontext = container.getContext();
        tutorialfifthview = (View) from(tutorialfifthcontext).inflate(
                R.layout.activity_tutorial_fifth_page, container, false);

        btn_goto_signin = (Button)tutorialfifthview.findViewById(R.id.btn_goto_signin);
        btn_goto_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("pref", tutorialfifthcontext.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                String isLogged = pref.getString("islogged", "");
                String login = pref.getString("login", "");

                editor.putString("islogged", "yes");
                editor.commit();

                if (login.equals("yes")){
                    getActivity().finish();
                } else {
                    Intent intent = new Intent(tutorialfifthcontext, SigninActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });

        return tutorialfifthview;
    }
}