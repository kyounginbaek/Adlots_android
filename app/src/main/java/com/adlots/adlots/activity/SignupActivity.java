package com.adlots.adlots.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adlots.adlots.R;
import com.adlots.adlots.helper.DataHolder;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends Activity {

    Button btn_signup_back;
    Button btn_signup;
    EditText edt_signup_email;
    EditText edt_signup_phone;
    EditText edt_signup_password;
    EditText edt_signup_passcheck;
    EditText edt_signup_nickname;
    String email, password, passcheck, phone, nickname;

    private static final String URL = DataHolder.signupURL;
    private RequestQueue requestQueue;
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edt_signup_email = (EditText)findViewById(R.id.edt_signup_email);
        edt_signup_email.setNextFocusDownId(R.id.edt_signup_password);
        edt_signup_phone = (EditText)findViewById(R.id.edt_signup_phone);
        phone = getPhoneNumber();
        edt_signup_phone.setText(phone); // 자동으로 핸드폰번호 가져오기

        edt_signup_password = (EditText)findViewById(R.id.edt_signup_password);
        edt_signup_passcheck = (EditText)findViewById(R.id.edt_signup_passcheck);
        edt_signup_nickname = (EditText)findViewById(R.id.edt_signup_nickname);
        btn_signup = (Button)findViewById(R.id.btn_signup);
        btn_signup_back = (Button)findViewById(R.id.btn_signup_back);

        requestQueue = Volley.newRequestQueue(this);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edt_signup_email.getText().toString();
                password = edt_signup_password.getText().toString();
                passcheck = edt_signup_passcheck.getText().toString();
                nickname = edt_signup_nickname.getText().toString();

                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(password.equals(passcheck)) {
                                if(jsonObject.names().get(0).equals("phone")){
                                    Toast.makeText(getApplicationContext(),"이미 등록된 핸드폰 번호입니다. 포인트 중복혜택을 방지하기 위함이니 adlots@naver.com으로 문의해주세요.", Toast.LENGTH_SHORT).show();
                                } else if(jsonObject.names().get(0).equals("success")){
                                    Toast.makeText(getApplicationContext(),"회원가입 되었습니다.",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), SigninActivity.class));
                                } else if(jsonObject.names().get(0).equals("empty")){
                                    Toast.makeText(getApplicationContext(),"정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                                } else if(jsonObject.names().get(0).equals("email")){
                                    Toast.makeText(getApplicationContext(),"이미 등록된 이메일입니다. 다른 이메일을 사용해주세요.", Toast.LENGTH_SHORT).show();
                                } else if(jsonObject.names().get(0).equals("nickname")){
                                    Toast.makeText(getApplicationContext(),"이미 등록된 닉네임입니다. 다른 닉네임을 사용해주세요.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(),"오류가 발생했습니다. adlots@naver.com으로 문의해주세요.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(),"비밀번호와 비밀번호확인이 불일치합니다. 다시 한번 입력해주세요.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String,String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<String, String>();
                        hashMap.put("email",email);
                        hashMap.put("password",password);
                        hashMap.put("phone",phone);
                        hashMap.put("nickname",nickname);

                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });

        btn_signup_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
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
        super.onBackPressed();
    }
}
