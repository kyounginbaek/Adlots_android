package com.adlots.adlots;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SignupActivity extends AppCompatActivity {

    Button btn_signup_back;
    Button btn_signup;
    EditText edt_signup_email;
    EditText edt_signup_phone;
    EditText edt_signup_password;
    EditText edt_signup_passcheck;
    EditText edt_signup_nickname;

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edt_signup_email = (EditText)findViewById(R.id.edt_signup_email);
        edt_signup_email.setNextFocusDownId(R.id.edt_signup_password);
        edt_signup_phone = (EditText)findViewById(R.id.edt_signup_phone);
        edt_signup_password = (EditText)findViewById(R.id.edt_signup_password);
        edt_signup_passcheck = (EditText)findViewById(R.id.edt_signup_passcheck);
        edt_signup_nickname = (EditText)findViewById(R.id.edt_signup_nickname);
        btn_signup = (Button)findViewById(R.id.btn_signup);
        btn_signup_back = (Button)findViewById(R.id.btn_signup_back);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_signup_email.getText().toString();
                String phone = getPhoneNumber();
                edt_signup_phone.setText(phone);
                String password = edt_signup_password.getText().toString();
                String passcheck = edt_signup_passcheck.getText().toString();
                String nickname = edt_signup_nickname.getText().toString();

                if(!email.equals("")&&!password.equals("")) {
                    if(!email.equals("")&&!password.equals("")&&!passcheck.equals("")&&!nickname.equals("")){
                        if(password.equals(passcheck)) {

                            adlotsSignup(email, phone, password, nickname);

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

    public String getPhoneNumber()
    {
        TelephonyManager mgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        return mgr.getLine1Number();
    }

    public void adlotsSignup(String email, String phone, String password, String nickname) {
        final String send_email= email;
        final String send_phone = phone;
        final String send_password = password;
        final String send_nickname = nickname;

        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {

                try {
                    String newemail = URLEncoder.encode(send_email, "UTF-8");
                    String newphone = URLEncoder.encode(send_phone, "UTF-8");
                    String newpassword = URLEncoder.encode(send_password,"UTF-8");
                    String newnickname = URLEncoder.encode(send_nickname, "UTF-8");

                    String address = "http://adlots.com/adlots_join.php?email="+newemail+"&phone="+newphone+
                            "&password="+newpassword+"&nickname="+newnickname;
                    URL url = new URL(address);
                    URLConnection conn = url.openConnection();
                    conn.connect();
                    InputStream iStream = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(iStream, "UTF-8"));
                    final String data = br.readLine();
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        task.execute();
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        backPressCloseHandler.onBackPressed();
    }
}
