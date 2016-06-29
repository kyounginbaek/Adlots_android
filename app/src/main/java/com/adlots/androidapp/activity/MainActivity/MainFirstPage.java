package com.adlots.androidapp.activity.MainActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.adlots.android.R;
import com.igaworks.IgawCommon;
import com.tnkfactory.ad.AdListView;
import com.tnkfactory.ad.TnkSession;
import com.tnkfactory.ad.TnkStyle;

import static android.view.LayoutInflater.from;

/**
 * Created by baekkyoungin on 2016. 1. 14..
 */
public class MainFirstPage extends Fragment {

    Button btn_tnkad, btn_adpopcorn;
    TextView txt_tnkad, txt_adpopcorn;
    public String strColor;

    private Context mainfirstContext = null;
    private View mainfirstView = null;

    public static MainFirstPage newProduction (int position) {
        MainFirstPage mpage = new MainFirstPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IgawCommon.startApplication(mainfirstContext);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainfirstContext = container.getContext();
        mainfirstView = (View) from(mainfirstContext).inflate(
                R.layout.activity_main_first_page, container, false);

        final ViewGroup main1_fragment = (ViewGroup) mainfirstView.findViewById(R.id.main1_fragment);
        //ViewGroup tnkad = (LinearLayout) mainfirstView.findViewById(R.id.main1_tnkad);
        //ViewGroup adpopcorn = (LinearLayout) mainfirstView.findViewById(R.id.main1_adpopcorn);
        //ViewGroup nas = (LinearLayout) mainfirstView.findViewById(R.id.main1_nas);
        //ViewGroup adsync = (LinearLayout) mainfirstView.findViewById(R.id.main1_adsync);

        //btn_tnkad = (Button) mainfirstView.findViewById(R.id.main1_btn_tnkad);
        //btn_adpopcorn = (Button) mainfirstView.findViewById(R.id.main1_btn_adpopcorn);
        //txt_tnkad = (TextView) mainfirstView.findViewById(R.id.main1_txt_tnkad);
        //txt_adpopcorn = (TextView) mainfirstView.findViewById(R.id.main1_txt_adpopcorn);

        /*
        strColor = "#fc6e51";
        btn_tnkad.setBackgroundResource(R.drawable.gift_click);
        txt_tnkad.setTextColor(Color.parseColor(strColor));
        */

        // tnk 포인트 충전소
        SharedPreferences pref = getActivity().getSharedPreferences("pref", mainfirstContext.MODE_PRIVATE);
        String pref_userid = pref.getString("userid", "");
        TnkSession.setUserName(mainfirstContext, pref_userid);
        TnkStyle.AdWall.Header.height = 0;

        // 처음 화면 tnk 설정
        AdListView adlistView = TnkSession.createAdListView(mainfirstContext, false);
        main1_fragment.addView(adlistView);
        adlistView.loadAdList();

        /*
        // 애드팝콘(IGAWorks) 포인트 충전소
        IgawCommon.setUserId(pref_userid);

        adpopcorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IgawAdpopcorn.openOfferWall(mainfirstContext);
            }
        });
        */

        return mainfirstView;
    }
}
