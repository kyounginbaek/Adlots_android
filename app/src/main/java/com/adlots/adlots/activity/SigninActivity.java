package com.adlots.adlots.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adlots.adlots.R;
import com.adlots.adlots.helper.BackPressCloseHandler;
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

/**
 * Created by baekkyoungin on 2016. 1. 11..
 */
public class SigninActivity extends Activity {

    Button btn_start;
    Button btn_goto_signup;
    EditText edt_signin_email;
    EditText edt_signin_password;
    String email,password;

    private static final String URL = DataHolder.signinURL;
    private RequestQueue requestQueue;
    private StringRequest request;
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

        requestQueue = Volley.newRequestQueue(this);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edt_signin_email.getText().toString();
                password = edt_signin_password.getText().toString();

                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.names().get(0).equals("success")){
                                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("login", "yes");
                                editor.putString("email", jsonObject.getString("email"));
                                editor.putString("nickname", jsonObject.getString("nickname"));
                                editor.commit();

                                Toast.makeText(getApplicationContext(),"로그인 되었습니다.",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            } else if (jsonObject.names().get(0).equals("empty")){
                                Toast.makeText(getApplicationContext(),"정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                            } else {
                                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("login", "yes");
                                editor.putString("email", jsonObject.getString("email"));
                                editor.putString("nickname", jsonObject.getString("nickname"));
                                editor.commit();

                                Toast.makeText(getApplicationContext(),"로그인 되었습니다.",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                                /* Toast.makeText(getApplicationContext(),"아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show(); */
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

        btn_goto_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        backPressCloseHandler.onBackPressed();
    }
}
