package com.adlots.adlots.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adlots.adlots.R;
import com.adlots.adlots.helper.BackPressCloseHandler;
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

public class SignupActivity extends AppCompatActivity {

    Button btn_signup_back;
    Button btn_signup;
    EditText edt_signup_email;
    EditText edt_signup_phone;
    EditText edt_signup_password;
    EditText edt_signup_passcheck;
    EditText edt_signup_nickname;
    String email, password, passcheck, nickname;
    String phone = getPhoneNumber();

    private static final String URL = "http://adlots.co.kr/android_php/signup.php";
    private RequestQueue requestQueue;
    private StringRequest request;
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
                            if(jsonObject.names().get(0).equals("success")){
                                Toast.makeText(getApplicationContext(),"로그인 되었습니다."+jsonObject.getString("success"),Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(),"아이디와 비밀번호를 확인해주세요." +jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
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
