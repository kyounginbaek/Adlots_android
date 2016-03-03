package com.adlots.adlots.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adlots.adlots.R;

import static android.view.LayoutInflater.from;

/**
 * Created by baekkyoungin on 2016. 1. 14..
 */
public class MainThirdPage extends Fragment {

    private Context mainthirdcontext = null;
    private View mainthirdview = null;

    public static MainThirdPage newProduction (int position) {
        MainThirdPage mpage = new MainThirdPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainthirdcontext = container.getContext();
        mainthirdview = (View) from(mainthirdcontext).inflate(
                R.layout.activity_main_third_page, container, false);

        SharedPreferences pref = getActivity().getSharedPreferences("pref", mainthirdcontext.MODE_PRIVATE);
        String pref_email = pref.getString("email", "");
        String pref_nickname = pref.getString("nickname", "");

        TextView nickname = (TextView) mainthirdview.findViewById(R.id.nickname);
        nickname.setText(pref_nickname);
        TextView email = (TextView) mainthirdview.findViewById(R.id.email);
        email.setText(pref_email);

        final ViewGroup userinfochange = (LinearLayout) mainthirdview.findViewById(R.id.userinfochange);
        userinfochange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getActivity().getLayoutInflater(); //Dialog에서 보여줄 입력화면 View 객체 생성 작업
                final View dialogView= inflater.inflate(R.layout.main_third_info_popup, null); //Dialog의 listener에서 사용하기 위해 final로 참조변수 선언

                AlertDialog.Builder buider= new AlertDialog.Builder(getActivity()); //AlertDialog.Builder 객체 생성
                buider.setView(dialogView); //위에서 inflater가 만든 dialogView 객체 세팅
                buider.setTitle("개인정보 변경");

                AlertDialog dialog=buider.create(); //설정한 값으로 AlertDialog 객체 생성
                dialog.setCanceledOnTouchOutside(true); //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                dialog.show(); //Dialog 보이기

                Button btn_infochange = (Button) dialogView.findViewById(R.id.btn_infochange);
                btn_infochange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences pref = getActivity().getSharedPreferences("pref", mainthirdcontext.MODE_PRIVATE);
                        String login = pref.getString("login", "");
                        Toast.makeText(mainthirdcontext, "개인정보가 변경되었습니다. 다시 로그인해 주세요.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mainthirdcontext, SigninActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

        ViewGroup logout = (LinearLayout) mainthirdview.findViewById(R.id.logout);
        logout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("pref", mainthirdcontext.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("login", "no");
                editor.commit();

                Intent intent = new Intent(mainthirdcontext, SigninActivity.class);
                startActivity(intent);
                Toast.makeText(mainthirdcontext, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

        return mainthirdview;
    }

}
