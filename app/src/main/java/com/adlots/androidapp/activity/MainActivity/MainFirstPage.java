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
import android.widget.Toast;

import com.adlots.android.R;
import com.adlots.androidapp.rest.RestClient;
import com.google.gson.JsonElement;
import com.igaworks.IgawCommon;
import com.tnkfactory.ad.AdListView;
import com.tnkfactory.ad.TnkSession;
import com.tnkfactory.ad.TnkStyle;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.view.LayoutInflater.from;

/**
 * Created by baekkyoungin on 2016. 1. 14..
 */
public class MainFirstPage extends Fragment {

    Button btn_tnkad, btn_adpopcorn;
    TextView txt_tnkad, txt_adpopcorn;
    String strColor;
    String tnkpoint;

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
        final TextView main1_latest_signin = (TextView) mainfirstView.findViewById(R.id.main1_latest_signin);

        /*
        ViewGroup tnkad = (LinearLayout) mainfirstView.findViewById(R.id.main1_tnkad);
        ViewGroup adpopcorn = (LinearLayout) mainfirstView.findViewById(R.id.main1_adpopcorn);
        ViewGroup nas = (LinearLayout) mainfirstView.findViewById(R.id.main1_nas);
        ViewGroup adsync = (LinearLayout) mainfirstView.findViewById(R.id.main1_adsync);

        btn_tnkad = (Button) mainfirstView.findViewById(R.id.main1_btn_tnkad);
        btn_adpopcorn = (Button) mainfirstView.findViewById(R.id.main1_btn_adpopcorn);
        txt_tnkad = (TextView) mainfirstView.findViewById(R.id.main1_txt_tnkad);
        txt_adpopcorn = (TextView) mainfirstView.findViewById(R.id.main1_txt_adpopcorn);


        strColor = "#fc6e51";
        btn_tnkad.setBackgroundResource(R.drawable.gift_click);
        txt_tnkad.setTextColor(Color.parseColor(strColor));
        */

        SharedPreferences pref = getActivity().getSharedPreferences("pref", mainfirstContext.MODE_PRIVATE);
        String pref_userid = pref.getString("userid", "");

        // 매일매일 로그인 이벤트 - 20포인트 제공
        HashMap<String, String> data = new HashMap<>();
        data.put("userid", pref_userid);
        RestClient.AdlotsService service = RestClient.getService();
        service.latest_signin(data, new Callback<JsonElement>() {
            @Override
            public void success(JsonElement jsonElement, Response response) {
                String condition = jsonElement.getAsJsonObject().get("response").getAsString();
                String latest_signin = jsonElement.getAsJsonObject().get("latest_signin").getAsString();
                main1_latest_signin.setText(latest_signin.substring(0,10));

                switch (condition) {
                    case "signin_point":
                        Toast.makeText(mainfirstContext, "매일매일 로그인으로 20랏츠가 지급되었습니다.", Toast.LENGTH_SHORT).show();
                        break;
                    case "signin_already":
                        Toast.makeText(mainfirstContext, "이미 매일매일 로그인 20랏츠가 지급되었습니다. 내일 또 방문해주세요^^", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mainfirstContext, "오류가 발생했습니다. crowdit@naver.com으로 문의해주세요.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(mainfirstContext, "오류가 발생했습니다. crowdit@naver.com으로 문의해주세요.", Toast.LENGTH_SHORT).show();
            }
        });

        // tnk 포인트 충전소
        TnkSession.setUserName(mainfirstContext, pref_userid); //userid를 tnk유저 식별자로 사용
        TnkStyle.AdWall.Header.height = 0;

        // fragment 첫화면을 tnk로 설정
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
