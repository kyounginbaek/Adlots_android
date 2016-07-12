package com.adlots.androidapp.activity.MainActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adlots.android.R;
import com.adlots.androidapp.activity.MainActivity.MainFourthActivity.MainFourthFaqActivity;
import com.adlots.androidapp.activity.MainActivity.MainFourthActivity.MainFourthServicePolicyActivity;
import com.adlots.androidapp.activity.MainActivity.MainFourthActivity.MainFourthUserPolicyActivity;
import com.adlots.androidapp.activity.MainActivity.MainFourthActivity.MainFourthWinnerActivity;
import com.adlots.androidapp.activity.TutorialActivity.TutorialActivity;
import com.adlots.androidapp.rest.RestClient;
import com.google.gson.JsonElement;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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

        SharedPreferences pref = getActivity().getSharedPreferences("pref", mainfourthContext.MODE_PRIVATE);
        final String pref_email = pref.getString("email", "");
        final String pref_nickname = pref.getString("nickname", "");

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
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"crowdit@naver.com"});
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

        // 추천인 등록하기
        ViewGroup recommend = (LinearLayout) mainfourthView.findViewById(R.id.main4_recommend);
        recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getActivity().getLayoutInflater(); //Dialog에서 보여줄 입력화면 View 객체 생성 작업
                final View dialogView= inflater.inflate(R.layout.popup_main_fourth_recommend, null); //Dialog의 listener에서 사용하기 위해 final로 참조변수 선언

                AlertDialog.Builder buider= new AlertDialog.Builder(getActivity()); //AlertDialog.Builder 객체 생성
                buider.setView(dialogView); //위에서 inflater가 만든 dialogView 객체 세팅
                buider.setTitle("추천인 등록하기");

                final EditText recommend = (EditText) dialogView.findViewById(R.id.main4_popup_recommend);
                final TextView recommend_number = (TextView) dialogView.findViewById(R.id.main4_popup_recommend_number);
                final TextView recommend_point = (TextView) dialogView.findViewById(R.id.main4_popup_recommend_point);
                final Button btn_recommend = (Button) dialogView.findViewById(R.id.main4_popup_btn_recommend);

                HashMap<String, String> data = new HashMap<>();
                data.put("nickname", pref_nickname);

                //추천 정보 가져오기
                RestClient.AdlotsService service = RestClient.getService();
                service.recommend("getinfo", data, new Callback<JsonElement>() {
                    @Override
                    public void success(JsonElement jsonElement, Response response) {
                        String str_recommend = jsonElement.getAsJsonObject().get("recommend").getAsString();
                        if (!str_recommend.equals("")) {
                            recommend.setText(str_recommend);
                            recommend.setClickable(false);
                            recommend.setEnabled(false);
                            recommend.setFocusable(false);

                            btn_recommend.setText("중복 등록 불가");
                            btn_recommend.setClickable(false);
                            btn_recommend.setEnabled(false);
                            btn_recommend.setFocusable(false);
                        }
                        recommend_number.setText(jsonElement.getAsJsonObject().get("recommend_number").getAsString());
                        recommend_point.setText(jsonElement.getAsJsonObject().get("recommend_point").getAsString());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    }
                });

                final AlertDialog dialog=buider.create(); //설정한 값으로 AlertDialog 객체 생성
                dialog.setCanceledOnTouchOutside(true); //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                dialog.show(); //Dialog 보이기

                //추천인 등록하기 버튼 클릭 시
                btn_recommend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btn_recommend.setClickable(false); // 버튼이 2번 눌리는 것을 방지
                        String str_recommend = recommend.getText().toString();
                        if (str_recommend.equals("")) {
                            Toast.makeText(mainfourthContext, "추천인을 입력해주세요.", Toast.LENGTH_SHORT).show();
                        } else if (str_recommend.equals(pref_nickname)) {
                            Toast.makeText(mainfourthContext, "본인을 제외한 추천인을 입력해주세요.", Toast.LENGTH_SHORT).show();
                        } else {
                            HashMap<String, String> data = new HashMap<>();
                            data.put("nickname", pref_nickname);
                            data.put("str_recommend", str_recommend);

                            //추천 정보 입력하기
                            RestClient.AdlotsService service = RestClient.getService();
                            service.recommend("sendinfo", data, new Callback<JsonElement>() {
                                @Override
                                public void success(JsonElement jsonElement, Response response) {
                                    String condition = jsonElement.getAsJsonObject().get("response").getAsString();
                                    switch (condition) {
                                        case "no_user":
                                            btn_recommend.setClickable(true); // 버튼이 2번 눌리는 것을 방지
                                            Toast.makeText(mainfourthContext, "추천인이 존재하지 않습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                                            break;
                                        case "success":
                                            main3_refresh(pref_email);
                                            Toast.makeText(mainfourthContext, "추천인이 등록되었습니다. 랏츠가 지급되었습니다.", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                            break;
                                        default:
                                            Toast.makeText(mainfourthContext, "오류가 발생했습니다. crowdit@naver.com으로 문의해주세요.", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    Toast.makeText(mainfourthContext, "오류가 발생했습니다. crowdit@naver.com으로 문의해주세요.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });

        // 블로그 소식보기
        ViewGroup blog = (LinearLayout) mainfourthView.findViewById(R.id.main4_blog);
        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("http://m.blog.naver.com/crowdit");
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

    //main3 Fragment 새로고침
    public void main3_refresh(String pref_email) {
        final TextView txt_point = (TextView) MainThirdPage.staticvar.getActivity().findViewById(R.id.main3_userpoint);

        // 유저 포인트 가져오기
        HashMap<String, String> data = new HashMap<>();
        data.put("email", pref_email);
        RestClient.AdlotsService service = RestClient.getService();
        service.getuserPoint(data, new Callback<JsonElement>() {
            @Override
            public void success(JsonElement jsonElement, Response response) {
                String userpoint = jsonElement.getAsJsonObject().get("response").getAsString();
                txt_point.setText(userpoint);
            }
            @Override
            public void failure(RetrofitError error) {
            }
        });

        FragmentTransaction transaction = MainThirdPage.staticvar.getChildFragmentManager().beginTransaction();
        Fragment currentFragment = MainThirdPage.staticvar.getChildFragmentManager().findFragmentById(R.id.main3_useritemfragment);
        transaction.detach(currentFragment);
        transaction.attach(currentFragment);
        transaction.commit();
    }
}
