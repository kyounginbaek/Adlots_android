package com.adlots.adlots.activity.MainActivity.MainSecondFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adlots.adlots.R;
import com.adlots.adlots.activity.MainActivity.MainSecondPage;
import com.adlots.adlots.activity.MainActivity.MainThirdPage;
import com.adlots.adlots.helper.ImageLoadTask;
import com.adlots.adlots.rest.RestClient;
import com.adlots.adlots.rest.model.MainSecondItem;
import com.google.gson.JsonElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by baekkyoungin on 16. 3. 31..
 */
public class MainSecondListAdapter extends ArrayAdapter<MainSecondItem> {
    private Context context;
    private ArrayList<MainSecondItem> items;
    int layoutResource;
    String userpoint;

    public MainSecondListAdapter(Context context, int resource, ArrayList<MainSecondItem> items) {
        super(context, resource, items);
        this.layoutResource = resource;
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View v = convertView;
        final MainSecondItem adlotsItem = items.get(position);

        SharedPreferences pref = context.getSharedPreferences("pref", context.MODE_PRIVATE);
        final String pref_nickname = pref.getString("nickname", "");
        final String pref_email = pref.getString("email", "");
        final String pref_password = pref.getString("password", "");

        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = dayTime.format(new Date(time));

        // 유저 포인트 가져오기
        HashMap<String, String> data = new HashMap<>();
        data.put("email", pref_email);
        data.put("password", pref_password);
        RestClient.AdlotsService service = RestClient.getService();
        service.getuserPoint(data, new Callback<JsonElement>() {
            @Override
            public void success(JsonElement jsonElement, Response response) {
                userpoint = jsonElement.getAsJsonObject().get("response").getAsString();
            }
            @Override
            public void failure(RetrofitError error) {
            }
        });

        ListHolder holder = null;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(layoutResource, parent, false);

            holder = new ListHolder();
            holder.imagelink = (ImageView) v.findViewById(R.id.main2_imagelink);
            holder.endtime = (TextView) v.findViewById(R.id.main2_endtime);
            holder.category = (TextView) v.findViewById(R.id.main2_category);
            holder.brand = (TextView) v.findViewById(R.id.main2_brand);
            holder.itemname = (TextView) v.findViewById(R.id.main2_itemname);
            holder.endpoint = (TextView) v.findViewById(R.id.main2_endpoint);
            holder.nowpoint = (TextView) v.findViewById(R.id.main2_nowpoint);
            holder.lotspeople = (TextView) v.findViewById(R.id.main2_lotspeople);

            holder.layoutnull1 = (FrameLayout) v.findViewById(R.id.main2_layoutnull1);
            holder.layoutnull2 = (LinearLayout) v.findViewById(R.id.main2_layoutnull2);
            holder.layoutnull3 = (LinearLayout) v.findViewById(R.id.main2_layoutnull3);
            holder.layoutnull4 = (LinearLayout) v.findViewById(R.id.main2_layoutnull4);
            holder.layoutnull5 = (LinearLayout) v.findViewById(R.id.main2_layoutnull5);
            holder.layoutnull6 = (LinearLayout) v.findViewById(R.id.main2_layoutnull6);

            // 응모하기 버튼 클릭 이벤트
            holder.textbtn_lots = (TextView) v.findViewById(R.id.main2_textbtn_lots);
            holder.textbtn_lots.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //Dialog에서 보여줄 입력화면 View 객체 생성 작업
                    final View dialogView = inflater.inflate(R.layout.popup_main_second_itemlots, null); //Dialog의 listener에서 사용하기 위해 final로 참조변수 선언

                    AlertDialog.Builder buider = new AlertDialog.Builder(context); //AlertDialog.Builder 객체 생성
                    buider.setView(dialogView);
                    buider.setTitle("몇 랏츠를 응모하시겠습니까?");

                    final TextView howmuchlots = (TextView) dialogView.findViewById(R.id.main2_popup_howmuchlots);
                    final TextView mypoint = (TextView) dialogView.findViewById(R.id.main2_popup_mypoint);
                    final TextView lotspeople = (TextView) dialogView.findViewById(R.id.main2_popup_lotspeople);
                    final TextView remainpoint = (TextView) dialogView.findViewById(R.id.main2_popup_remainpoint);

                    // 한개 아이템 정보 가져오기
                    HashMap<String, String> data = new HashMap<>();
                    data.put("id", adlotsItem.id);
                    RestClient.AdlotsService service = RestClient.getService();
                    service.getoneItem(data, new Callback<JsonElement>() {
                        @Override
                        public void success(JsonElement jsonElement, Response response) {
                            mypoint.setText(userpoint);
                            lotspeople.setText(jsonElement.getAsJsonObject().get("lotspeople").getAsString());
                            int endpoint = Integer.parseInt(jsonElement.getAsJsonObject().get("endpoint").getAsString());
                            int nowpoint = Integer.parseInt(jsonElement.getAsJsonObject().get("nowpoint").getAsString());
                            int remain = endpoint-nowpoint;
                            remainpoint.setText(String.valueOf(remain));
                        }
                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });

                    final AlertDialog dialog = buider.create(); //설정한 값으로 AlertDialog 객체 생성
                    dialog.setCanceledOnTouchOutside(true); //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                    dialog.show(); //Dialog 보이기

                    // 응모하기 완료 버튼 클릭 이벤트
                    Button btn_lots = (Button) dialogView.findViewById(R.id.main2_popup_btn_lots);
                    btn_lots.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            long time = System.currentTimeMillis();
                            SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            String date = dayTime.format(new Date(time));
                            String userlotspoint = howmuchlots.getText().toString();

                            // 당첨자 추첨을 위한 랜덤 함수
                            Random rand = new Random();
                            int random = rand.nextInt(Integer.parseInt(adlotsItem.endpoint))+1; // 1에서 endpoint까지
                            String winorlose = String.valueOf(random);

                            // 총 14개 데이터 전송
                            HashMap<String, String> data = new HashMap<>();
                            data.put("nickname", pref_nickname);
                            data.put("howtobuy", "lots");
                            data.put("itemid", adlotsItem.id);
                            data.put("type", adlotsItem.type);
                            data.put("category", adlotsItem.category);
                            data.put("brand", adlotsItem.brand);
                            data.put("itemname", adlotsItem.itemname);
                            data.put("imagelink", adlotsItem.imagelink);
                            data.put("referlink", adlotsItem.referlink);
                            data.put("endpoint", adlotsItem.endpoint);
                            data.put("starttime", adlotsItem.startime);
                            data.put("endtime", adlotsItem.endtime);
                            data.put("userlotspoint", userlotspoint); // 유저가 입력한 응모 포인트
                            data.put("when", date);
                            data.put("winorlose", winorlose);

                            // 유저가 입력한 응모 포인트 blank, number 체크
                            if(CheckNumber(userlotspoint)){
                                double double_userlotspoint = Double.parseDouble(userlotspoint);
                                int int_userpoint = Integer.parseInt(userpoint);
                                if ((double_userlotspoint <= 0) || (double_userlotspoint % 10 != 0)) {
                                    // 유저가 입력한 포인트가 0 혹은 음수이거나, 10 단위가 아닌 경우
                                    Toast.makeText(context, "10 랏츠 단위로 응모해주세요. (예: 10,100,1000,10000)", Toast.LENGTH_SHORT).show();
                                } else if (double_userlotspoint > int_userpoint) {
                                    // 유저가 입력한 응모 포인트 & 유저가 보유한 포인트 비교
                                    Toast.makeText(context, "입력하신 응모 랏츠가 회원님의 보유 랏츠보다 많습니다. 다시 한번 입력해주세요.", Toast.LENGTH_SHORT).show();
                                } else {
                                    RestClient.AdlotsService service = RestClient.getService();
                                    service.itemhowtoBuy("lots", data, new Callback<JsonElement>() {
                                        @Override
                                        public void success(JsonElement jsonElement, Response response) {
                                            String condition = jsonElement.getAsJsonObject().get("response").getAsString();
                                            switch(condition){
                                                case "overpoint":
                                                    Toast.makeText(context, "남은 응모 랏츠를 확인해주세요.", Toast.LENGTH_SHORT).show();
                                                    break;
                                                case "success":
                                                    main2_refresh();
                                                    main3_refresh(pref_email, pref_password);
                                                    Toast.makeText(context, "응모가 완료되었습니다. 나의 응모/구입 목록을 확인해주세요.", Toast.LENGTH_SHORT).show();
                                                    dialog.dismiss();
                                                    break;
                                                case "pointdone":
                                                    main2_refresh();
                                                    main3_refresh(pref_email, pref_password);
                                                    Toast.makeText(context, "응모가 마무리되었습니다. 나의 당첨 유무를 확인해주세요.", Toast.LENGTH_SHORT).show();
                                                    dialog.dismiss();
                                                    break;
                                            }
                                        }
                                        @Override
                                        public void failure(RetrofitError error) {
                                            Toast.makeText(context, "오류가 발생했습니다. adlots@naver.com으로 문의해주세요.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            } else {
                                Toast.makeText(context, "입력 값을 확인해주세요. 10랏츠 단위 숫자를 입력해주셔야 합니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });

            // 바로구입 버튼 클릭 이벤트
            holder.textbtn_purchase = (TextView) v.findViewById(R.id.main2_textbtn_purchase);
            holder.textbtn_purchase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder buider = new AlertDialog.Builder(context); //AlertDialog.Builder 객체 생성
                    buider.setTitle("바로구입 확인")
                            .setMessage("바로구입 하시겠습니까?\n\n구입하신 포인트만큼 랏츠가 차감됩니다.")
                            .setCancelable(true)
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                // 확인 버튼 클릭시 설정
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    long time = System.currentTimeMillis();
                                    SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                    String date = dayTime.format(new Date(time));

                                    // 총 11개 데이터 전송 (nowpoint, startime, point 제외)
                                    HashMap<String, String> data = new HashMap<>();
                                    data.put("nickname", pref_nickname);
                                    data.put("howtobuy", "purchase");
                                    data.put("itemid", adlotsItem.id);
                                    data.put("type", adlotsItem.type);
                                    data.put("category", adlotsItem.category);
                                    data.put("brand", adlotsItem.brand);
                                    data.put("itemname", adlotsItem.itemname);
                                    data.put("imagelink", adlotsItem.imagelink);
                                    data.put("referlink", adlotsItem.referlink);
                                    data.put("endpoint", adlotsItem.endpoint);
                                    data.put("when", date);

                                    int int_endpoint = Integer.parseInt(adlotsItem.endpoint);
                                    int int_userpoint = Integer.parseInt(userpoint);
                                    if (int_endpoint > int_userpoint) {
                                        // 유저가 바로 구입할 물품 포인트 & 유저가 보유한 포인트 비교
                                        Toast.makeText(context, "구입하시고자 하는 물품 랏츠가 회원님의 보유 랏츠보다 많습니다. 다시 한번 확인해주세요.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        RestClient.AdlotsService service = RestClient.getService();
                                        service.itemhowtoBuy("purchase", data, new Callback<JsonElement>() {
                                            @Override
                                            public void success(JsonElement jsonElement, Response response) {
                                                Toast.makeText(context, "바로구입 되었습니다. 나의 응모/구입 목록을 확인해주세요.", Toast.LENGTH_SHORT).show();
                                                main2_refresh();
                                                main3_refresh(pref_email, pref_password);
                                            }
                                            @Override
                                            public void failure(RetrofitError error) {
                                                Toast.makeText(context, "오류가 발생했습니다. adlots@naver.com으로 문의해주세요.", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                // 취소 버튼 클릭시 설정
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog dialog = buider.create(); //설정한 값으로 AlertDialog 객체 생성
                    dialog.setCanceledOnTouchOutside(true); //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                    dialog.show(); //Dialog 보이기
                }
            });
            v.setTag(holder);
        } else {
            holder = (ListHolder) v.getTag();
        }

        // 리스트에 아이템 값 넣기
        if (adlotsItem != null) {
            // 만약 응모 & 구입 사실이 없을 경우
            if(items.get(0).id.equals("null")) {
                if(items.get(0).type.equals("giftcon")){
                    holder.itemname.setText("\n응모할 아이템이 없습니다.\n\n곧 새아이템이 올라올 예정입니다!\n");
                    holder.itemname.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
                } else if(items.get(0).type.equals("delivery")) {
                    holder.itemname.setText("\n구입할 아이템이 없습니다.\n\n곧 새아이템이 올라올 예정입니다!\n");
                    holder.itemname.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
                } else {
                    holder.itemname.setText("\n마감 임박한 아이템이 없습니다.\n\n마감 10일 전부터 표시됩니다!\n");
                    holder.itemname.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
                }
                holder.layoutnull1.setVisibility(View.GONE);
                holder.layoutnull2.setVisibility(View.GONE);
                holder.layoutnull3.setVisibility(View.GONE);
                holder.layoutnull4.setVisibility(View.GONE);
                holder.layoutnull5.setVisibility(View.GONE);
                holder.layoutnull6.setVisibility(View.GONE);

            } else {
                new ImageLoadTask(adlotsItem.imagelink, holder.imagelink).execute();
                holder.imagelink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 이미지 클릭 시 참고링크 인터넷 화면 표시
                        WebView webview = new WebView(context);
                        webview.getSettings().setLoadWithOverviewMode(true);
                        webview.getSettings().setUseWideViewPort(true);
                        webview.getSettings().setBuiltInZoomControls(true);
                        webview.loadUrl(adlotsItem.referlink);

                        AlertDialog.Builder buider = new AlertDialog.Builder(context); //AlertDialog.Builder 객체 생성
                        buider.setView(webview) //위에서 inflater가 만든 dialogView 객체 세팅
                                .setTitle("아이템 자세한 정보")
                                .setNegativeButton("창 닫기", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        dialog.dismiss();
                                    }
                                });

                        AlertDialog dialog = buider.create(); //설정한 값으로 AlertDialog 객체 생성
                        dialog.setCanceledOnTouchOutside(true); //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                        dialog.show(); //Dialog 보이기
                    }
                });

                holder.endtime.setText(String.valueOf(diffOfDate(date,adlotsItem.endtime))+"일");
                holder.category.setText(adlotsItem.category);
                holder.brand.setText(adlotsItem.brand);
                holder.itemname.setText(adlotsItem.itemname);
                holder.endpoint.setText(adlotsItem.endpoint);
                holder.nowpoint.setText(adlotsItem.nowpoint);
                holder.lotspeople.setText(adlotsItem.lotspeople);
            }
        }
        return v;
    }

    static class ListHolder {
        TextView category, brand, itemname;
        ImageView imagelink;
        TextView endtime;
        TextView endpoint, nowpoint, lotspeople;
        TextView textbtn_lots, textbtn_purchase;

        FrameLayout layoutnull1;
        LinearLayout layoutnull2, layoutnull3, layoutnull4, layoutnull5, layoutnull6;
    }

    public boolean CheckNumber(String str) {
        char check;
        if (str.equals("")) {
            //문자열이 공백인지 확인
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            check = str.charAt(i);
            if (check < 48 || check > 58) {
                //해당 char값이 숫자가 아닐 경우
                return false;
            }
        }
        return true;
    }

    public void main2_refresh() {
        FragmentTransaction transaction = MainSecondPage.staticvar.getChildFragmentManager().beginTransaction();
        Fragment currentFragment = MainSecondPage.staticvar.getChildFragmentManager().findFragmentById(R.id.main2_fragment);
        transaction.detach(currentFragment);
        transaction.attach(currentFragment);
        transaction.commit();
    }

    public void main3_refresh(String pref_email, String pref_password) {
        final TextView txt_point = (TextView) MainThirdPage.staticvar.getActivity().findViewById(R.id.main3_userpoint);

        // 유저 포인트 가져오기
        HashMap<String, String> data = new HashMap<>();
        data.put("email", pref_email);
        data.put("password", pref_password);
        RestClient.AdlotsService service = RestClient.getService();
        service.getuserPoint(data, new Callback<JsonElement>() {
            @Override
            public void success(JsonElement jsonElement, Response response) {
                String userpoint = jsonElement.getAsJsonObject().get("response").getAsString();
                txt_point.setText(userpoint);
            }
            @Override
            public void failure(RetrofitError error) {
            }
        });

        FragmentTransaction transaction = MainThirdPage.staticvar.getChildFragmentManager().beginTransaction();
        Fragment currentFragment = MainThirdPage.staticvar.getChildFragmentManager().findFragmentById(R.id.main3_fragment);
        transaction.detach(currentFragment);
        transaction.attach(currentFragment);
        transaction.commit();
    }

    public int diffOfDate(String start, String end) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        startDate.set(Integer.parseInt(start.substring(0,4)), Integer.parseInt(start.substring(5,7))-1, Integer.parseInt(start.substring(8,10)));
        endDate.set(Integer.parseInt(end.substring(0,4)), Integer.parseInt(end.substring(5,7))-1, Integer.parseInt(end.substring(8,10)));

        long diffMillis = endDate.getTimeInMillis() - startDate.getTimeInMillis();
        int diffDays = (int)(diffMillis / (24 * 60 * 60 * 1000));

        return diffDays;
    }
}