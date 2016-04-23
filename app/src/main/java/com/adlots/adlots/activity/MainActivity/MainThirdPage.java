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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.adlots.adlots.R;
import com.adlots.adlots.activity.MainActivity.MainSecondFragment.MainSecondListAdapter;
import com.adlots.adlots.activity.MainActivity.MainThridActivity.MainThirdUseritem;
import com.adlots.adlots.activity.SigninActivity;
import com.adlots.adlots.rest.RestClient;
import com.adlots.adlots.rest.model.MainThirdItem;
import com.google.gson.JsonElement;

import java.util.ArrayList;
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

    ListView useritemList;
    MainSecondListAdapter useritemAdapter;
    public ArrayList<MainThirdItem> useritemArray = new ArrayList<MainThirdItem>();

    public static MainThirdPage newProduction (int position) {
        MainThirdPage mpage = new MainThirdPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainthirdContext = container.getContext();
        mainthirdView = (View) from(mainthirdContext).inflate(
                R.layout.activity_main_third_page, container, false);

        SharedPreferences pref = getActivity().getSharedPreferences("pref", mainthirdContext.MODE_PRIVATE);
        String pref_email = pref.getString("email", "");
        String pref_password = pref.getString("password", "");
        String pref_nickname = pref.getString("nickname", "");

        TextView txt_nickname = (TextView) mainthirdView.findViewById(R.id.main3_nickname);
        txt_nickname.setText(pref_nickname);
        TextView txt_email = (TextView) mainthirdView.findViewById(R.id.main3_email);
        txt_email.setText(pref_email);
        final TextView txt_point = (TextView) mainthirdView.findViewById(R.id.main3_point);

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

        final ViewGroup userinfochange = (LinearLayout) mainthirdView.findViewById(R.id.userinfochange);
        userinfochange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getActivity().getLayoutInflater(); //Dialog에서 보여줄 입력화면 View 객체 생성 작업
                final View dialogView = inflater.inflate(R.layout.popup_main_third_infochange, null); //Dialog의 listener에서 사용하기 위해 final로 참조변수 선언

                // 기존 비밀번호 확인, 어떤 정보를 변경하려고 하는가, 비밀번호 변경 시에는 비밀번호 확인 필수

                AlertDialog.Builder buider = new AlertDialog.Builder(getActivity()); //AlertDialog.Builder 객체 생성
                buider.setView(dialogView); //위에서 inflater가 만든 dialogView 객체 세팅
                buider.setTitle("개인정보 변경");

                AlertDialog dialog = buider.create(); //설정한 값으로 AlertDialog 객체 생성
                dialog.setCanceledOnTouchOutside(true); //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                dialog.show(); //Dialog 보이기

                Button btn_infochange = (Button) dialogView.findViewById(R.id.btn_infochange);
                btn_infochange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences pref = getActivity().getSharedPreferences("pref", mainthirdContext.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("login", "no");
                        editor.commit();

                        Toast.makeText(mainthirdContext, "개인정보가 변경되었습니다. 다시 로그인해 주세요.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mainthirdContext, SigninActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
            }
        });

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

        final Fragment userinfofragment = new MainThirdUseritem();
        final FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.main3_fragment, userinfofragment).commit(); //처음 화면

        return mainthirdView;
    }

}
