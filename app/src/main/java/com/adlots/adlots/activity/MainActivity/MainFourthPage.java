package com.adlots.adlots.activity.MainActivity;

import android.app.AlertDialog;
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
import com.adlots.adlots.activity.TutorialActivity.TutorialActivity;

import static android.view.LayoutInflater.from;

/**
 * Created by baekkyoungin on 2016. 1. 14..
 */
public class MainFourthPage extends Fragment {

    private Context mainfourthContext = null;
    private View mainfourthView = null;

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
        mainfourthContext = container.getContext();
        mainfourthView = (View) from(mainfourthContext).inflate(
                R.layout.activity_main_fourth_page, container, false);

        // 페이스북 페이지
        ViewGroup facebook = (LinearLayout) mainfourthView.findViewById(R.id.facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://facebook.com/adlots"));
                startActivity(intent);
            }
        });

        // 당첨자 목록보기
        ViewGroup winnerlist = (LinearLayout) mainfourthView.findViewById(R.id.winnerlist);
        winnerlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Intent intent = new Intent(mainfourthcontext, WinnerlistActivity.class);
                startActivity(intent); */
            }
        });

        // 이용 및 제휴문의
        ViewGroup email = (LinearLayout) mainfourthView.findViewById(R.id.email);
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

        // 애드랏츠 홈페이지
        ViewGroup homepage = (LinearLayout) mainfourthView.findViewById(R.id.homepage);
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://adlots.co.kr"));
                startActivity(intent);
            }
        });

        // 푸시 알람
        ViewGroup pushalarm = (LinearLayout) mainfourthView.findViewById(R.id.pushalarm);
        pushalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // 자주 묻는 질문 FAQ
        ViewGroup faq = (LinearLayout) mainfourthView.findViewById(R.id.faq);
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getActivity().getLayoutInflater(); //Dialog에서 보여줄 입력화면 View 객체 생성 작업
                final View dialogView= inflater.inflate(R.layout.popup_main_fourth_agreement, null); //Dialog의 listener에서 사용하기 위해 final로 참조변수 선언

                AlertDialog.Builder buider= new AlertDialog.Builder(getActivity()); //AlertDialog.Builder 객체 생성
                buider.setView(dialogView); //위에서 inflater가 만든 dialogView 객체 세팅
                buider.setTitle("자주 묻는 질문 FAQ");

                AlertDialog dialog=buider.create(); //설정한 값으로 AlertDialog 객체 생성
                dialog.setCanceledOnTouchOutside(true); //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                dialog.show(); //Dialog 보이기
            }
        });

        // 블로그 소식보기
        ViewGroup blog = (LinearLayout) mainfourthView.findViewById(R.id.blog);
        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.naver.com/adlots"));
                startActivity(intent);
            }
        });

        // 튜토리얼 다시보기
        ViewGroup tutorial = (LinearLayout) mainfourthView.findViewById(R.id.tutorial);
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainfourthContext, TutorialActivity.class);
                startActivity(intent);
            }
        });

        // 개발자 정보
        ViewGroup developers = (LinearLayout) mainfourthView.findViewById(R.id.developers);
        developers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getActivity().getLayoutInflater(); //Dialog에서 보여줄 입력화면 View 객체 생성 작업
                final View dialogView= inflater.inflate(R.layout.popup_main_fourth_developers, null); //Dialog의 listener에서 사용하기 위해 final로 참조변수 선언

                AlertDialog.Builder buider= new AlertDialog.Builder(getActivity()); //AlertDialog.Builder 객체 생성
                buider.setView(dialogView); //위에서 inflater가 만든 dialogView 객체 세팅
                buider.setTitle("개발자 정보");

                AlertDialog dialog=buider.create(); //설정한 값으로 AlertDialog 객체 생성
                dialog.setCanceledOnTouchOutside(true); //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                dialog.show(); //Dialog 보이기
            }
        });

        // 개인정보 이용약관
        ViewGroup agreement = (LinearLayout) mainfourthView.findViewById(R.id.agreement);
        agreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getActivity().getLayoutInflater(); //Dialog에서 보여줄 입력화면 View 객체 생성 작업
                final View dialogView= inflater.inflate(R.layout.popup_main_fourth_agreement, null); //Dialog의 listener에서 사용하기 위해 final로 참조변수 선언

                AlertDialog.Builder buider= new AlertDialog.Builder(getActivity()); //AlertDialog.Builder 객체 생성
                buider.setView(dialogView); //위에서 inflater가 만든 dialogView 객체 세팅
                buider.setTitle("개인정보 이용약관");

                AlertDialog dialog=buider.create(); //설정한 값으로 AlertDialog 객체 생성
                dialog.setCanceledOnTouchOutside(true); //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                dialog.show(); //Dialog 보이기
            }
        });

        return mainfourthView;
    }
}
