package com.adlots.androidapp.activity.TutorialActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.adlots.android.R;
import com.adlots.androidapp.activity.SigninActivity;

import static android.view.LayoutInflater.from;

/**
 * Created by baekkyoungin on 2015. 11. 18..
 */
public class TutorialSixthPage extends Fragment {
    private Context tutorialsixthContext = null;
    private View tutorialsixthView = null;

    Button btn_goto_signin;

    public static TutorialSixthPage newProduction (int position) {
        TutorialSixthPage mpage = new TutorialSixthPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tutorialsixthContext = container.getContext();
        tutorialsixthView = (View) from(tutorialsixthContext).inflate(
                R.layout.activity_tutorial_sixth_page, container, false);

        btn_goto_signin = (Button)tutorialsixthView.findViewById(R.id.btn_goto_signin);
        btn_goto_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("pref", tutorialsixthContext.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                String isLogged = pref.getString("islogged", "");
                String login = pref.getString("login", "");

                editor.putString("islogged", "yes");
                editor.commit();

                if (login.equals("yes")){
                    getActivity().finish();
                } else {
                    Intent intent = new Intent(tutorialsixthContext, SigninActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });

        return tutorialsixthView;
    }
}
