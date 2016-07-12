package com.adlots.androidapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adlots.android.R;
import com.adlots.androidapp.activity.MainActivity.MainActivity;
import com.adlots.androidapp.helper.BackPressCloseHandler;
import com.adlots.androidapp.rest.RestClient;
import com.google.gson.JsonElement;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by baekkyoungin on 2016. 1. 11..
 */
public class SigninActivity extends Activity {

    Button btn_start;
    Button btn_goto_signup;
    EditText edt_signin_email;
    EditText edt_signin_password;
    String email,password,phone;

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        edt_signin_email =  (EditText)findViewById(R.id.edt_signin_email);
        edt_signin_email.setNextFocusDownId(R.id.edt_signin_password);
        edt_signin_password = (EditText)findViewById(R.id.edt_signin_password);
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_goto_signup = (Button)findViewById(R.id.btn_goto_signup);

        phone = getPhoneNumber();
        if(phone.equals("")){
            btn_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "핸드폰 번호가 없어 로그인이 불가능합니다.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            btn_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    email = edt_signin_email.getText().toString();
                    password = edt_signin_password.getText().toString();

                    HashMap<String, String> data = new HashMap<>();
                    data.put("email", email);
                    data.put("password", password);
                    data.put("phone", phone);

                    // (1)빈칸 체크, (2)등록 여부
                    if(!email.equals("")&&!password.equals("")){
                        RestClient.AdlotsService service = RestClient.getService();
                        service.signin(data, new Callback<JsonElement>() {
                            @Override
                            public void success(JsonElement jsonElement, Response response) {
                                String condition = jsonElement.getAsJsonObject().get("response").getAsString();
                                switch (condition) {
                                    case "no_email":
                                        Toast.makeText(getApplicationContext(), "가입된 아이디가 없습니다. 회원가입을 해주세요.", Toast.LENGTH_SHORT).show();
                                        break;
                                    case "wrong_password":
                                        Toast.makeText(getApplicationContext(), "비밀번호가 틀렸습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                                        break;
                                    case "different_phone":
                                        Toast.makeText(getApplicationContext(), "핸드폰 번호와 이메일이 일치하지 않습니다. 핸드폰 번호를 바꾸셨나요? adlots@naver.com으로 문의해주세요.", Toast.LENGTH_SHORT).show();
                                        break;
                                    case "success":
                                        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = pref.edit();
                                        editor.putString("login", "yes");
                                        editor.putString("email", email);
                                        editor.putString("password", password);
                                        editor.putString("phone", phone);
                                        editor.putString("nickname", jsonElement.getAsJsonObject().get("nickname").getAsString());
                                        editor.putString("userid", jsonElement.getAsJsonObject().get("id").getAsString());
                                        editor.commit();

                                        Toast.makeText(getApplicationContext(), "로그인 되었습니다. 환영합니다.", Toast.LENGTH_SHORT).show();
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
                    } else {
                        Toast.makeText(getApplicationContext(),"정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        btn_goto_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        backPressCloseHandler.onBackPressed();
    }

    public String getPhoneNumber() {
        TelephonyManager mgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        return mgr.getLine1Number();
    }
}
