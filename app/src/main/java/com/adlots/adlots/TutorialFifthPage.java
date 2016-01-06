package com.adlots.adlots;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static android.view.LayoutInflater.from;

/**
 * Created by baekkyoungin on 2015. 11. 18..
 */
public class TutorialFifthPage extends Fragment {
    private Context tutorialfifthcontext = null;
    private View tutorialfifthview = null;

    Button btn_start;
    Button btn_signup;
    EditText edt_email;
    EditText edt_password;

    public static TutorialFifthPage newProduction (int position) {
        TutorialFifthPage mpage = new TutorialFifthPage();
        return mpage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstnaceState) {
        tutorialfifthcontext = container.getContext();
        tutorialfifthview = (View) from(tutorialfifthcontext).inflate(
                R.layout.activity_tutorial_fifth_page, container, false);
        Util.setGlobalFont(tutorialfifthcontext, tutorialfifthview);

        LinearLayout mainLayout = (LinearLayout) tutorialfifthview.findViewById(R.id.linear);
        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) tutorialfifthcontext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(tutorialfifthview.getWindowToken(), 0);
            }
        });

        btn_start = (Button)tutorialfifthview.findViewById(R.id.btn_adlots_start);
        edt_email =  (EditText)tutorialfifthview.findViewById(R.id.edt_tutorial_email);
        edt_email.setNextFocusDownId(R.id.edt_tutorial_password);
        edt_password = (EditText)tutorialfifthview.findViewById(R.id.edt_tutorial_password);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();
                if(!email.equals("")&&!password.equals("")) {
                    SharedPreferences pref = tutorialfifthcontext.getSharedPreferences("pref", tutorialfifthcontext.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("islogin", "yes");
                    editor.putString("email", email);
                    editor.putString("password", password);
                    editor.commit();
                    adlotsJoin(email, password);

                    Intent intent = new Intent(tutorialfifthcontext, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(tutorialfifthcontext,
                            "정보를 모두 입력해 주십시오.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });
        return tutorialfifthview;
    }

    public void adlotsJoin(String email, String password) {
        final String send_email= email;
        final String send_password = password;

        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {

                // String address = "http://ezbrother.com/insert_ssm_written2.php?word_id=aa&word=aa&ssm=aa&author=aa&email=aa&when=aa&open=aa";
                try {
                    String newemail = URLEncoder.encode(send_email,"UTF-8");
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
}
