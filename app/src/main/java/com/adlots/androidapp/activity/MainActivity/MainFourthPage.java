package com.adlots.androidapp.activity.MainActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adlots.android.R;
import com.adlots.androidapp.activity.MainActivity.MainFourthActivity.MainFourthFaqActivity;
import com.adlots.androidapp.activity.MainActivity.MainFourthActivity.MainFourthServicePolicyActivity;
import com.adlots.androidapp.activity.MainActivity.MainFourthActivity.MainFourthUserPolicyActivity;
import com.adlots.androidapp.activity.MainActivity.MainFourthActivity.MainFourthWinnerActivity;
import com.adlots.androidapp.activity.TutorialActivity.TutorialActivity;

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
        ViewGroup facebook = (LinearLayout) mainfourthView.findViewById(R.id.main4_facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("http://m.facebook.com/adlots");
                intent.setData(uri);
                startActivity(intent);
            }
        });

        // 당첨자 목록보기
        ViewGroup winnerlist = (LinearLayout) mainfourthView.findViewById(R.id.main4_winnerlist);
        winnerlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainfourthContext, MainFourthWinnerActivity.class));
            }
        });

        // 이용 및 제휴문의
        ViewGroup email = (LinearLayout) mainfourthView.findViewById(R.id.main4_email);
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
        ViewGroup homepage = (LinearLayout) mainfourthView.findViewById(R.id.main4_homepage);
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("http://adlots.co.kr");
                intent.setData(uri);
                startActivity(intent);
            }
        });

        // 자주 묻는 질문 FAQ
        ViewGroup faq = (LinearLayout) mainfourthView.findViewById(R.id.main4_faq);
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainfourthContext, MainFourthFaqActivity.class));
            }
        });

        // 블로그 소식보기
        ViewGroup blog = (LinearLayout) mainfourthView.findViewById(R.id.main4_blog);
        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("http://m.blog.naver.com/adlots");
                intent.setData(uri);
                startActivity(intent);
            }
        });

        // 튜토리얼 다시보기
        ViewGroup tutorial = (LinearLayout) mainfourthView.findViewById(R.id.main4_tutorial);
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainfourthContext, TutorialActivity.class));
            }
        });

        // 개발자 정보
        ViewGroup developers = (LinearLayout) mainfourthView.findViewById(R.id.main4_developers);
        developers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getActivity().getLayoutInflater(); //Dialog에서 보여줄 입력화면 View 객체 생성 작업
                final View dialogView= inflater.inflate(R.layout.popup_main_fourth_developers, null); //Dialog의 listener에서 사용하기 위해 final로 참조변수 선언

                AlertDialog.Builder buider= new AlertDialog.Builder(getActivity()); //AlertDialog.Builder 객체 생성
                buider.setView(dialogView); //위에서 inflater가 만든 dialogView 객체 세팅
                buider.setTitle("개발자 정보");

                TextView version = (TextView) dialogView.findViewById(R.id.main4_devinfo_version);
                version.setText(getVersionName(mainfourthContext));

                AlertDialog dialog=buider.create(); //설정한 값으로 AlertDialog 객체 생성
                dialog.setCanceledOnTouchOutside(true); //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                dialog.show(); //Dialog 보이기
            }
        });

        // 서비스 이용약관
        ViewGroup servicepolicy = (LinearLayout) mainfourthView.findViewById(R.id.main4_servicepolicy);
        servicepolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainfourthContext, MainFourthServicePolicyActivity.class));
            }
        });

        // 개인정보 취급방침
        ViewGroup userpolicy = (LinearLayout) mainfourthView.findViewById(R.id.main4_userpolicy);
        userpolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainfourthContext, MainFourthUserPolicyActivity.class));
            }
        });

        return mainfourthView;
    }

    public String getVersionName(Context context) {
        try {
            PackageInfo pi= context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }
}
