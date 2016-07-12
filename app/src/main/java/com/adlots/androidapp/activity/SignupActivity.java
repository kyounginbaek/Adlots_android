package com.adlots.androidapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adlots.android.R;
import com.adlots.androidapp.activity.MainActivity.MainActivity;
import com.adlots.androidapp.activity.MainActivity.MainFourthActivity.MainFourthServicePolicyActivity;
import com.adlots.androidapp.activity.MainActivity.MainFourthActivity.MainFourthUserPolicyActivity;
import com.adlots.androidapp.rest.RestClient;
import com.google.gson.JsonElement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignupActivity extends Activity {

    Button btn_signup;
    EditText edt_signup_email;
    EditText edt_signup_phone;
    EditText edt_signup_password;
    EditText edt_signup_passcheck;
    EditText edt_signup_nickname;
    EditText edt_signup_recommend;
    String email, password, passcheck, phone, nickname, date, recommend;
    CheckBox signup_checkbox;
    TextView txt_signup_servicepolicy, txt_signup_userpolicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edt_signup_email = (EditText)findViewById(R.id.edt_signup_email);
        edt_signup_phone = (EditText)findViewById(R.id.edt_signup_phone);
        edt_signup_password = (EditText)findViewById(R.id.edt_signup_password);
        edt_signup_passcheck = (EditText)findViewById(R.id.edt_signup_passcheck);
        edt_signup_nickname = (EditText)findViewById(R.id.edt_signup_nickname);
        btn_signup = (Button)findViewById(R.id.btn_signup);
        ImageView btn_signup_back = (ImageView)findViewById(R.id.signup_btn_x);
        signup_checkbox = (CheckBox)findViewById(R.id.signup_checkBox);
        txt_signup_servicepolicy = (TextView)findViewById(R.id.signup_servicepolicy);
        txt_signup_userpolicy = (TextView)findViewById(R.id.signup_userpolicy);
        edt_signup_recommend = (EditText)findViewById(R.id.edt_signup_recommend);

        txt_signup_servicepolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(SignupActivity.this, MainFourthServicePolicyActivity.class));
            }
        });

        txt_signup_userpolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(SignupActivity.this, MainFourthUserPolicyActivity.class));
            }
        });

        edt_signup_email.setNextFocusDownId(R.id.edt_signup_password);
        phone = getPhoneNumber();
        if(phone.equals("")){
            edt_signup_phone.setText("번호가 없습니다.");
            btn_signup.setText("회원가입 불가능");
            btn_signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Toast.makeText(getApplicationContext(), "핸드폰 번호가 없어 가입이 불가능합니다.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            edt_signup_phone.setText(phone); // 자동으로 핸드폰번호 가져오기
            if(signup_checkbox.isChecked()){ // 서비스 이용약관, 개인정보 취급방침 체크 필요
                btn_signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        email = edt_signup_email.getText().toString();
                        password = edt_signup_password.getText().toString();
                        passcheck = edt_signup_passcheck.getText().toString();
                        nickname = edt_signup_nickname.getText().toString();
                        recommend = edt_signup_recommend.getText().toString();

                        long time = System.currentTimeMillis();
                        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        date = dayTime.format(new Date(time));

                        HashMap<String, String> data = new HashMap<>();
                        data.put("email", email);
                        data.put("phone", phone);
                        data.put("password", password);
                        data.put("nickname", nickname);
                        data.put("when", date);
                        data.put("recommend", recommend);

                        // (1)빈칸 체크, (2)비밀번호 확인 체크, (3)서버 통신 - 폰, 이메일 닉네임 체크
                        if (!email.equals("") && !password.equals("") && !passcheck.equals("") && !nickname.equals("")) {
                            if (password.equals(passcheck)) {
                                if(recommend.equals(nickname)){
                                    Toast.makeText(getApplicationContext(), "본인을 제외한 추천인을 입력해주세요.", Toast.LENGTH_SHORT).show();
                                } else {
                                    RestClient.AdlotsService service = RestClient.getService();
                                    service.signup(data, new Callback<JsonElement>() {
                                        @Override
                                        public void success(JsonElement jsonElement, Response response) {
                                            String condition = jsonElement.getAsJsonObject().get("response").getAsString();
                                            switch (condition) {
                                                case "phone_exists":
                                                    Toast.makeText(getApplicationContext(), "이미 등록된 핸드폰 번호입니다. 포인트 중복혜택을 방지하기 위함이니 adlots@naver.com으로 문의해주세요.", Toast.LENGTH_SHORT).show();
                                                    break;
                                                case "email_exists":
                                                    Toast.makeText(getApplicationContext(), "이미 등록된 이메일입니다. 다른 이메일을 사용해주세요.", Toast.LENGTH_SHORT).show();
                                                    break;
                                                case "nickname_exists":
                                                    Toast.makeText(getApplicationContext(), "이미 등록된 닉네임입니다. 다른 닉네임을 사용해주세요.", Toast.LENGTH_SHORT).show();
                                                    break;
                                                case "no_recommend":
                                                    Toast.makeText(getApplicationContext(), "추천인이 존재하지 않습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                                                    break;
                                                case "success":
                                                    SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                                                    SharedPreferences.Editor editor = pref.edit();
                                                    editor.putString("login", "yes");
                                                    editor.putString("email", email);
                                                    editor.putString("password", password);
                                                    editor.putString("phone", phone);
                                                    editor.putString("nickname", nickname);
                                                    editor.putString("userid", jsonElement.getAsJsonObject().get("id").getAsString());
                                                    editor.commit();

                                                    Toast.makeText(getApplicationContext(), "회원가입 되었습니다. 즐거운 시간되세요!", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                                    finish();
                                                    break;
                                                default:
                                                    Toast.makeText(getApplicationContext(), "오류가 발생했습니다. crowdit@naver.com으로 문의해주세요.", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void failure(RetrofitError error) {
                                            Toast.makeText(getApplicationContext(), "오류가 발생했습니다. crowdit@naver.com으로 문의해주세요.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "비밀번호와 비밀번호확인이 불일치합니다. 다시 한번 입력해주세요.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                btn_signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        Toast.makeText(getApplicationContext(), "서비스 이용약관과 개인정보 취급방침에 동의해주세요.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        btn_signup_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public String getPhoneNumber() {
        TelephonyManager mgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        return mgr.getLine1Number();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
