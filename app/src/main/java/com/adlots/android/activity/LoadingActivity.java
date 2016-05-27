package com.adlots.android.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.adlots.android.R;
import com.adlots.android.activity.MainActivity.MainActivity;
import com.adlots.android.activity.TutorialActivity.TutorialActivity;
import com.adlots.android.rest.RestClient;
import com.google.gson.JsonElement;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        ConnectivityManager cManager;
        NetworkInfo mobile;
        NetworkInfo wifi;

        cManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        mobile = cManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        wifi = cManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if(!mobile.isConnected() && !wifi.isConnected())
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoadingActivity.this);
            builder.setTitle("네트워크 오류");
            builder.setMessage("네트워크 상태를 확인해주세요.")
                    .setCancelable(false)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        } else {
            RestClient.AdlotsService service = RestClient.getService();
            service.getVersion(new Callback<JsonElement>() {
                @Override
                public void success(JsonElement jsonElement, Response response) {
                    final String server_version = jsonElement.getAsJsonObject().get("response").getAsString();
                    String device_version = getVersionName(getApplicationContext());

                    if (server_version.charAt(0) == device_version.charAt(0) && server_version.charAt(2) == device_version.charAt(2)) {
                        if (server_version.charAt(4) == device_version.charAt(4)) {
                            Handler mHandler = new Handler(Looper.getMainLooper());
                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    initialize();
                                }
                            }, 1000);
                        } else {
                            SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                            String checked_update = pref.getString("checked_update", "");
                            if (checked_update.equals(server_version)) {
                                initialize();
                            } else {
                                Handler mHandler = new Handler(Looper.getMainLooper());
                                mHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        select_Dialog_alert(server_version);
                                    }
                                }, 1000);
                            }
                        }

                    } else {
                        Handler mHandler = new Handler(Looper.getMainLooper());
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                force_Dialog_alert();
                            }
                        }, 1000);
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                }
            });
        }
    }

    private void initialize() {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String isLogged = pref.getString("islogged", "");
        String login = pref.getString("login", "");

        if(login.equals("yes")) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        } else if(isLogged.equals("yes")) {
            Intent intent = new Intent(getApplicationContext(), SigninActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(getApplicationContext(), TutorialActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public String getVersionName(Context context) {
        try {
            PackageInfo pi= context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    private void force_Dialog_alert() {
        final CharSequence[] items = {
                "업데이트 하러 가기", "취소[종료]"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(LoadingActivity.this);
        builder.setTitle("'애드랏츠' 필수 업데이트가 있습니다. 업데이트 부탁드립니다. :)");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                if (item == 0) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=" + getPackageName()));
                    startActivity(intent);
                    finish();
                } else {
                    moveTaskToBack(true);
                    finish();
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(0);
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void select_Dialog_alert(final String server_version) {
        final CharSequence[] items = {
                "업데이트 하러 가기", "나중에 하기"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(LoadingActivity.this);
        builder.setTitle("새로운 '애드랏츠' 업데이트가 있습니다.");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                if (item == 0) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=" + getPackageName()));
                    startActivity(intent);
                    finish();
                } else {
                    SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("checked_update", server_version);
                    editor.commit();
                    initialize();
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
