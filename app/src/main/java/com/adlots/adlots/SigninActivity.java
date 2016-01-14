package com.adlots.adlots;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
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

/**
 * Created by baekkyoungin on 2016. 1. 11..
 */
public class SigninActivity extends Activity {

    Button btn_start;
    Button btn_goto_signup;
    EditText edt_signin_email;
    EditText edt_signin_password;
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

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_signin_email.getText().toString();
                String password = edt_signin_password.getText().toString();
                if(!email.equals("")&&!password.equals("")) {
                    SharedPreferences pref = getSharedPreferences("mpref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("islogin", "yes");
                    editor.putString("email", email);
                    editor.putString("password", password);
                    editor.commit();

                    adlotsSignin(email, password);

                    Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(SigninActivity.this,
                            "정보를 모두 입력해 주십시오.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
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

    public void adlotsSignin(String email, String password) {
        final String send_email= email;
        final String send_password = password;

        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {

                try {
                    String newemail = URLEncoder.encode(send_email, "UTF-8");
                    String newpassword = URLEncoder.encode(send_password,"UTF-8");

                    String address = "http://adlots.com/adlots_join.php?email="+newemail+"&password="+newpassword;
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
