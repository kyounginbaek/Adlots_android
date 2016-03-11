package com.adlots.adlots.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.adlots.adlots.R;

import static android.view.LayoutInflater.from;

/**
 * Created by baekkyoungin on 2016. 1. 14..
 */
public class MainFourthPage extends Fragment {

    private Context mainfourthcontext = null;
    private View mainfourthview = null;

    public static MainFourthPage newProduction (int position) {
        MainFourthPage mpage = new MainFourthPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainfourthcontext = container.getContext();
        mainfourthview = (View) from(mainfourthcontext).inflate(
                R.layout.activity_main_fourth_page, container, false);

        ViewGroup blog = (LinearLayout) mainfourthview.findViewById(R.id.blog);
        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.naver.com/adlots"));
                startActivity(intent);
            }
        });

        ViewGroup email = (LinearLayout) mainfourthview.findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"adlots@naver.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "제목을 입력해주세요.");
                email.putExtra(Intent.EXTRA_TEXT, "어떤 문의사항이신가요? 빠른 시일내에 답장해드리겠습니다.");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });

        ViewGroup tutorial = (LinearLayout) mainfourthview.findViewById(R.id.tutorial);
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainfourthcontext, TutorialActivity.class);
                startActivity(intent);
            }
        });

        ViewGroup homepage = (LinearLayout) mainfourthview.findViewById(R.id.homepage);
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://adlots.co.kr"));
                startActivity(intent);
            }
        });

        ViewGroup facebook = (LinearLayout) mainfourthview.findViewById(R.id.facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://facebook.com/adlots"));
                startActivity(intent);
            }
        });

        ViewGroup developers = (LinearLayout) mainfourthview.findViewById(R.id.developers);
        developers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 개발자 보여주는 코드
            }
        });

        ViewGroup agreement = (LinearLayout) mainfourthview.findViewById(R.id.agreement);
        agreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 개인정보이용약관 보여주는 코드
            }
        });

        return mainfourthview;
    }
}
