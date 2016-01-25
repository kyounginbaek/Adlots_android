package com.adlots.adlots;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends Activity {

    Button btn_signup_back;
    Button btn_signup;
    EditText edt_signup_email;
    EditText edt_signup_phone;
    EditText edt_signup_password;
    EditText edt_signup_passcheck;
    EditText edt_signup_nickname;

    String email, password, passcheck, nickname;
    String phone = getPhoneNumber();

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edt_signup_email = (EditText)findViewById(R.id.edt_signup_email);
        edt_signup_email.setNextFocusDownId(R.id.edt_signup_password);
        edt_signup_phone = (EditText)findViewById(R.id.edt_signup_phone);
        edt_signup_phone.setText(phone); // 자동으로 핸드폰번호 가져오기

        edt_signup_password = (EditText)findViewById(R.id.edt_signup_password);
        edt_signup_passcheck = (EditText)findViewById(R.id.edt_signup_passcheck);
        edt_signup_nickname = (EditText)findViewById(R.id.edt_signup_nickname);
        btn_signup = (Button)findViewById(R.id.btn_signup);
        btn_signup_back = (Button)findViewById(R.id.btn_signup_back);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edt_signup_email.getText().toString();
                password = edt_signup_password.getText().toString();
                passcheck = edt_signup_passcheck.getText().toString();
                nickname = edt_signup_nickname.getText().toString();

                if(!email.equals("")&&!password.equals("")) {
                    if(!email.equals("")&&!password.equals("")&&!passcheck.equals("")&&!nickname.equals("")){
                        if(password.equals(passcheck)) {
                            Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                            startActivity(intent);
                        } else {
                            Toast toast = Toast.makeText(SignupActivity.this,
                                    "비밀번호가 불일치합니다.",
                                    Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    } else {
                        Toast toast = Toast.makeText(SignupActivity.this,
                                "정보를 모두 입력해 주십시오.",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                } else {
                    Toast toast = Toast.makeText(SignupActivity.this,
                            "이미 등록된 핸드폰번호입니다. 포인트 중복혜택을 막기위함이니 adlots@gmail.com으로 문의해주세요.",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

        btn_signup_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });

        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    public String getPhoneNumber() {
        TelephonyManager mgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        return mgr.getLine1Number();
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        backPressCloseHandler.onBackPressed();
    }
}
