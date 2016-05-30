package com.adlots.android.activity.MainActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.adlots.android.R;
import com.tnkfactory.ad.TnkSession;

import static android.view.LayoutInflater.from;

/**
 * Created by baekkyoungin on 2016. 1. 14..
 */
public class MainFirstPage extends Fragment {

    private Context mainfirstContext = null;
    private View mainfirstView = null;

    public static MainFirstPage newProduction (int position) {
        MainFirstPage mpage = new MainFirstPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainfirstContext = container.getContext();
        mainfirstView = (View) from(mainfirstContext).inflate(
                R.layout.activity_main_first_page, container, false);

        ViewGroup tnk = (LinearLayout) mainfirstView.findViewById(R.id.main1_tnk);
        ViewGroup adpopcorn = (LinearLayout) mainfirstView.findViewById(R.id.main1_adpopcorn);
        ViewGroup nas = (LinearLayout) mainfirstView.findViewById(R.id.main1_nas);
        ViewGroup adsync = (LinearLayout) mainfirstView.findViewById(R.id.main1_adsync);

        /*
        AdListView adlistView = TnkSession.createAdListView(mainfirstContext, true);
        adlistView.setTitle("랏츠 포인트 충천소");

        ViewGroup viewGroup = (ViewGroup) mainfirstView.findViewById(R.id.main1_fragment);
        viewGroup.addView(adlistView);

        adlistView.loadAdList();
        */

        SharedPreferences pref = getActivity().getSharedPreferences("pref", mainfirstContext.MODE_PRIVATE);
        String pref_nickname = pref.getString("nickname", "");
        TnkSession.setUserName(mainfirstContext, pref_nickname);
        tnk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TnkSession.showAdList(mainfirstContext, "랏츠 포인트 충전소1");
            }
        });

        adpopcorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TnkSession.showAdList(mainfirstContext, "Your title here");
            }
        });

        return mainfirstView;
    }
}
