package com.adlots.adlots.activity.MainActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adlots.adlots.R;
import com.adlots.adlots.activity.MainActivity.MainThirdFragment.MainThirdUserItem;
import com.adlots.adlots.activity.SigninActivity;
import com.adlots.adlots.rest.RestClient;
import com.google.gson.JsonElement;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.view.LayoutInflater.from;

/**
 * Created by baekkyoungin on 2016. 1. 14..
 */
public class MainThirdPage extends Fragment {

    private Context mainthirdContext = null;
    private View mainthirdView = null;
    public static MainThirdPage staticvar;

    public static MainThirdPage newProduction (int position) {
        MainThirdPage mpage = new MainThirdPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        staticvar = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainthirdContext = container.getContext();
        mainthirdView = (View) from(mainthirdContext).inflate(
                R.layout.activity_main_third_page, container, false);

        SharedPreferences pref = getActivity().getSharedPreferences("pref", mainthirdContext.MODE_PRIVATE);
        final String pref_email = pref.getString("email", "");
        final String pref_password = pref.getString("password", "");
        String pref_nickname = pref.getString("nickname", "");

        TextView txt_nickname = (TextView) mainthirdView.findViewById(R.id.main3_nickname);
        txt_nickname.setText(pref_nickname);
        TextView txt_email = (TextView) mainthirdView.findViewById(R.id.main3_email);
        txt_email.setText(pref_email);
        final TextView txt_point = (TextView) mainthirdView.findViewById(R.id.main3_userpoint);

        // 나의 포인트 가져오기
        HashMap<String, String> data = new HashMap<>();
        data.put("email", pref_email);
        data.put("password", pref_password);
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

        // 개인정보 변경 클릭 이벤트
        final ViewGroup userinfochange = (LinearLayout) mainthirdView.findViewById(R.id.userinfochange);
        userinfochange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getActivity().getLayoutInflater(); //Dialog에서 보여줄 입력화면 View 객체 생성 작업
                final View dialogView = inflater.inflate(R.layout.popup_main_third_infochange, null); //Dialog의 listener에서 사용하기 위해 final로 참조변수 선언
                final TextView infochange_email = (TextView) dialogView.findViewById(R.id.main3_infochange_email);
                final TextView infochange_password = (TextView) dialogView.findViewById(R.id.main3_infochange_password);
                final TextView infochange_passcheck = (TextView) dialogView.findViewById(R.id.main3_infochange_passcheck);
                final TextView infochange_originalpass = (TextView) dialogView.findViewById(R.id.main3_infochange_originalpass);

                AlertDialog.Builder buider = new AlertDialog.Builder(getActivity()); //AlertDialog.Builder 객체 생성
                buider.setView(dialogView); //위에서 inflater가 만든 dialogView 객체 세팅
                buider.setTitle("개인정보 변경");

                AlertDialog dialog = buider.create(); //설정한 값으로 AlertDialog 객체 생성
                dialog.setCanceledOnTouchOutside(true); //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                dialog.show(); //Dialog 보이기

                // 개인정보 변경 버튼 클릭 이벤트
                Button btn_infochange = (Button) dialogView.findViewById(R.id.main3_btn_infochange);
                btn_infochange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // (1) 개인정보 변경시 기존 비밀번호 입력 필수, (2) 비밀번호 변경 시에는 비밀번호 확인 필수
                        String info_email = infochange_email.getText().toString();
                        String info_password = infochange_password.getText().toString();
                        String info_passcheck = infochange_passcheck.getText().toString();
                        String info_originalpass = infochange_originalpass.getText().toString();

                        if (info_email.equals("") && info_password.equals("")) {
                            Toast.makeText(mainthirdContext, "변경하실 이메일 혹은 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                        } else {
                            if (info_originalpass.equals("")) {
                                Toast.makeText(mainthirdContext, "개인정보 변경을 위해 기존 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                            } else {
                                if (info_originalpass.equals(pref_password)) {
                                    if (info_password.equals("")) {
                                        if(info_email.equals(pref_email)){
                                            Toast.makeText(mainthirdContext, "기존 이메일과 동일합니다. 다른 이메일을 사용해주세요.", Toast.LENGTH_SHORT).show();
                                        } else {
                                            HashMap<String, String> data = new HashMap<>();
                                            data.put("newemail", info_email);
                                            data.put("originalemail", pref_email);
                                            data.put("originalpass", pref_password);
                                            RestClient.AdlotsService service = RestClient.getService();
                                            service.userinfoChange("newemail", data, new Callback<JsonElement>() {
                                                @Override
                                                public void success(JsonElement jsonElement, Response response) {
                                                    String condition = jsonElement.getAsJsonObject().get("response").getAsString();
                                                    if(condition.equals("email_exists")){
                                                        Toast.makeText(mainthirdContext, "다른 유저가 사용하는 이메일입니다. 다른 이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        SharedPreferences pref = getActivity().getSharedPreferences("pref", mainthirdContext.MODE_PRIVATE);
                                                        SharedPreferences.Editor editor = pref.edit();
                                                        editor.putString("login", "no");
                                                        editor.commit();

                                                        Toast.makeText(mainthirdContext, "이메일이 변경되었습니다. 다시 로그인해 주세요.", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(mainthirdContext, SigninActivity.class);
                                                        startActivity(intent);
                                                        getActivity().finish();
                                                    }
                                                }
                                                @Override
                                                public void failure(RetrofitError error) {
                                                    Toast.makeText(mainthirdContext, "오류가 발생했습니다. adlots@naver.com으로 문의해주세요.", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    } else if (info_email.equals("")) {
                                        if (info_password.equals(info_passcheck)) {
                                            if(info_password.equals(pref_password)){
                                                Toast.makeText(mainthirdContext, "기존 비밀번호와 동일합니다. 다른 비밀번호를 사용해주세요.", Toast.LENGTH_SHORT).show();
                                            } else {
                                                HashMap<String, String> data = new HashMap<>();
                                                data.put("newpassword", info_password);
                                                data.put("originalemail", pref_email);
                                                data.put("originalpass", pref_password);
                                                RestClient.AdlotsService service = RestClient.getService();
                                                service.userinfoChange("newpassword", data, new Callback<JsonElement>() {
                                                    @Override
                                                    public void success(JsonElement jsonElement, Response response) {
                                                        // 기존 이메일 중복 여부 체크 불필요
                                                        SharedPreferences pref = getActivity().getSharedPreferences("pref", mainthirdContext.MODE_PRIVATE);
                                                        SharedPreferences.Editor editor = pref.edit();
                                                        editor.putString("login", "no");
                                                        editor.commit();

                                                        Toast.makeText(mainthirdContext, "비밀번호가 변경되었습니다. 다시 로그인해 주세요.", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(mainthirdContext, SigninActivity.class);
                                                        startActivity(intent);
                                                        getActivity().finish();
                                                    }
                                                    @Override
                                                    public void failure(RetrofitError error) {
                                                        Toast.makeText(mainthirdContext, "오류가 발생했습니다. adlots@naver.com으로 문의해주세요.", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }
                                        } else {
                                            Toast.makeText(mainthirdContext, "변경 비밀번호와 재입력 비밀번호가 일치하지 않습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        if (info_password.equals(info_passcheck)) {
                                            if(info_email.equals(pref_email)){
                                                Toast.makeText(mainthirdContext, "기존 이메일과 동일합니다. 다른 이메일을 사용해주세요.", Toast.LENGTH_SHORT).show();
                                            } else if(info_password.equals(pref_password)){
                                                Toast.makeText(mainthirdContext, "기존 비밀번호와 동일합니다. 다른 비밀번호를 사용해주세요.", Toast.LENGTH_SHORT).show();
                                            } else {
                                                HashMap<String, String> data = new HashMap<>();
                                                data.put("newemail", info_email);
                                                data.put("newpassword", info_password);
                                                data.put("originalemail", pref_email);
                                                data.put("originalpass", pref_password);
                                                RestClient.AdlotsService service = RestClient.getService();
                                                service.userinfoChange("newboth", data, new Callback<JsonElement>() {
                                                    @Override
                                                    public void success(JsonElement jsonElement, Response response) {
                                                        String condition = jsonElement.getAsJsonObject().get("response").getAsString();
                                                        if (condition.equals("email_exists")) {
                                                            Toast.makeText(mainthirdContext, "다른 유저가 사용하는 이메일입니다. 다른 이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            SharedPreferences pref = getActivity().getSharedPreferences("pref", mainthirdContext.MODE_PRIVATE);
                                                            SharedPreferences.Editor editor = pref.edit();
                                                            editor.putString("login", "no");
                                                            editor.commit();

                                                            Toast.makeText(mainthirdContext, "비밀번호가 변경되었습니다. 다시 로그인해 주세요.", Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(mainthirdContext, SigninActivity.class);
                                                            startActivity(intent);
                                                            getActivity().finish();
                                                        }
                                                    }
                                                    @Override
                                                    public void failure(RetrofitError error) {
                                                        Toast.makeText(mainthirdContext, "오류가 발생했습니다. adlots@naver.com으로 문의해주세요.", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }
                                        } else {
                                            Toast.makeText(mainthirdContext, "변경 비밀번호와 비밀번호 재입력이 일치하지 않습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } else {
                                    Toast.makeText(mainthirdContext, "기존 비밀번호가 일치하지 않습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
            }
        });

        // 로그아웃 클릭 이벤트
        ViewGroup logout = (LinearLayout) mainthirdView.findViewById(R.id.logout);
        logout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buider = new AlertDialog.Builder(getActivity()); //AlertDialog.Builder 객체 생성
                buider.setTitle("로그아웃 확인")
                        .setMessage("로그아웃 하시겠습니까?")
                        .setCancelable(true)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            // 확인 버튼 클릭시 설정
                            public void onClick(DialogInterface dialog, int whichButton) {
                                SharedPreferences pref = getActivity().getSharedPreferences("pref", mainthirdContext.MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("login", "no");
                                editor.commit();

                                Toast.makeText(mainthirdContext, "로그아웃 되었습니다. 다시 와주실꺼죠?", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mainthirdContext, SigninActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            // 취소 버튼 클릭시 설정
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog = buider.create(); //설정한 값으로 AlertDialog 객체 생성
                dialog.setCanceledOnTouchOutside(true); //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                dialog.show(); //Dialog 보이기
            }
        });

        // 나의 응목 & 구입 목록 가져오기
        final Fragment userinfofragment = new MainThirdUserItem();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.main3_fragment, userinfofragment).commit(); //처음 화면

        return mainthirdView;
    }
}
