package com.adlots.androidapp.activity.MainActivity.MainThirdFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import com.adlots.android.R;
import com.adlots.androidapp.activity.MainActivity.MainSecondPage;
import com.adlots.androidapp.activity.MainActivity.MainThirdPage;
import com.adlots.androidapp.rest.RestClient;
import com.adlots.androidapp.rest.model.MainThirdItem;
import com.google.gson.JsonElement;
import com.squareup.picasso.Picasso;

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
public class MainThirdListAdapter extends ArrayAdapter<MainThirdItem> {
    private Context context;
    private ArrayList<MainThirdItem> items;
    int layoutResource;
    String userpoint;

    public MainThirdListAdapter(Context context, int resource, ArrayList<MainThirdItem> items) {
        super(context, resource, items);
        this.layoutResource = resource;
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View v = convertView;
        final MainThirdItem adlotsItem = items.get(position);

        // SharedPreferences
        SharedPreferences pref = context.getSharedPreferences("pref", context.MODE_PRIVATE);
        final String pref_nickname = pref.getString("nickname", "");
        final String pref_email = pref.getString("email", "");
        final String pref_phone = pref.getString("phone", "");

        // 현재 시간 가져오기
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = dayTime.format(new Date(time));

        // 유저 포인트 가져오기
        HashMap<String, String> data = new HashMap<>();
        data.put("email", pref_email);
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

        // null 설정
        ImageView imagelink = null;
        TextView txt_endtime = null, endtime = null, type = null, category = null, brand = null, itemname = null, userlotspoint = null;
        TextView txt_userlotspoint = null, txt_when = null, when = null, info = null, status = null, lotsquota1 = null, lotsquota2 = null, txt_lotsquota = null;
        LinearLayout layout_lotsquota = null;
        FrameLayout layoutnull1 = null;
        LinearLayout layoutnull2 = null, layoutnull3 = null, layoutnull4 = null, layoutnull5 = null, layoutnull6 = null;
        ListHolder holder = null;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(layoutResource, parent, false);

            // View 초기 설정
            imagelink = (ImageView) v.findViewById(R.id.main3_imagelink);
            txt_endtime = (TextView) v.findViewById(R.id.main3_txt_endtime);
            endtime = (TextView) v.findViewById(R.id.main3_endtime);
            type = (TextView) v.findViewById(R.id.main3_type);
            category = (TextView) v.findViewById(R.id.main3_category);
            brand = (TextView) v.findViewById(R.id.main3_brand);
            itemname = (TextView) v.findViewById(R.id.main3_itemname);
            userlotspoint = (TextView) v.findViewById(R.id.main3_userlotspoint);
            txt_userlotspoint = (TextView) v.findViewById(R.id.main3_txt_userlotspoint);
            txt_when = (TextView) v.findViewById(R.id.main3_txt_when);
            when = (TextView) v.findViewById(R.id.main3_when);
            info = (TextView) v.findViewById(R.id.main3_info);
            status = (TextView) v.findViewById(R.id.main3_status);
            lotsquota1 = (TextView) v.findViewById(R.id.main3_lotsquota1);
            lotsquota2 = (TextView) v.findViewById(R.id.main3_lotsquota2);
            txt_lotsquota = (TextView) v.findViewById(R.id.main3_txt_lotsquota);
            layout_lotsquota = (LinearLayout) v.findViewById(R.id.main3_layout_lotsquota);
            layoutnull1 = (FrameLayout) v.findViewById(R.id.main3_layoutnull1);
            layoutnull2 = (LinearLayout) v.findViewById(R.id.main3_layoutnull2);
            layoutnull3 = (LinearLayout) v.findViewById(R.id.main3_layoutnull3);
            layoutnull4 = (LinearLayout) v.findViewById(R.id.main3_layoutnull4);
            layoutnull5 = (LinearLayout) v.findViewById(R.id.main3_layoutnull5);
            layoutnull6 = (LinearLayout) v.findViewById(R.id.main3_layoutnull6);

            // holder 생성 및 Tag로 등록
            holder = new ListHolder();
            holder.imagelink = imagelink;
            holder.txt_endtime = txt_endtime;
            holder.endtime = endtime;
            holder.type = type;
            holder.category = category;
            holder.brand = brand;
            holder.itemname = itemname;
            holder.userlotspoint = userlotspoint;
            holder.txt_userlotspoint = txt_userlotspoint;
            holder.txt_when = txt_when;
            holder.when = when;
            holder.info = info;
            holder.status = status;
            holder.lotsquota1 = lotsquota1;
            holder.lotsquota2 = lotsquota2;
            holder.txt_lotsquota = txt_lotsquota;
            holder.layout_lotsquota = layout_lotsquota;
            holder.layoutnull1 = layoutnull1;
            holder.layoutnull2 = layoutnull2;
            holder.layoutnull3 = layoutnull3;
            holder.layoutnull4 = layoutnull4;
            holder.layoutnull5 = layoutnull5;
            holder.layoutnull6 = layoutnull6;
            v.setTag(holder);

        } else {
            holder = (ListHolder) v.getTag();
            imagelink = holder.imagelink;
            txt_endtime = holder.txt_endtime;
            endtime = holder.endtime;
            type = holder.type;
            category = holder.category;
            brand = holder.brand;
            itemname = holder.itemname;
            userlotspoint = holder.userlotspoint;
            txt_userlotspoint = holder.txt_userlotspoint;
            txt_when = holder.txt_when;
            when = holder.when;
            info = holder.info;
            status = holder.status;
            lotsquota1 = holder.lotsquota1;
            lotsquota2 = holder.lotsquota2;
            txt_lotsquota = holder.txt_lotsquota;
            layout_lotsquota = holder.layout_lotsquota;
            layoutnull1 = holder.layoutnull1;
            layoutnull2 = holder.layoutnull2;
            layoutnull3 = holder.layoutnull3;
            layoutnull4 = holder.layoutnull4;
            layoutnull5 = holder.layoutnull5;
            layoutnull6 = holder.layoutnull6;
        }

        // 리스트에 아이템 값 넣기
        if (adlotsItem != null) {
            // 만약 응모 & 구입 사실이 없을 경우
            if(items.get(0).id.equals("null")) {
                if(items.get(0).howtobuy.equals("lots")){
                    itemname.setText("\n응모하신 내역이 없습니다.\n\n포인트를 경품에 응모해보세요!\n");
                    itemname.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
                } else {
                    itemname.setText("\n바로구입하신 내역이 없습니다.\n\n포인트로 상품을 구입해보세요!\n");
                    itemname.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
                }
                layoutnull1.setVisibility(View.GONE);
                layoutnull2.setVisibility(View.GONE);
                layoutnull3.setVisibility(View.GONE);
                layoutnull4.setVisibility(View.GONE);
                layoutnull5.setVisibility(View.GONE);
                layoutnull6.setVisibility(View.GONE);
                txt_lotsquota.setVisibility(View.GONE);
                layout_lotsquota.setVisibility(View.GONE);

            } else {
                Picasso.with(context).load(adlotsItem.imagelink).into(imagelink);
                imagelink.setOnClickListener(new View.OnClickListener() {
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

                if(adlotsItem.type.equals("giftcon")){
                    type.setText("기프티콘");
                } else if(adlotsItem.type.equals("delivery")){
                    type.setText("배송물품");
                }
                category.setText(adlotsItem.category);
                brand.setText(adlotsItem.brand);
                itemname.setText(adlotsItem.itemname);
                endtime.setText(String.valueOf(diffOfDate(date, adlotsItem.endtime)) + "일");

                if(adlotsItem.howtobuy.equals("lots")) { // 응모하기 탭
                    userlotspoint.setText(adlotsItem.userlotspoint);
                    txt_when.setText("응모 일시");

                    lotsquota1.setText(adlotsItem.lotsquota1);
                    lotsquota2.setText(adlotsItem.lotsquota2);

                    if(adlotsItem.winorlose.equals("win")){
                        txt_endtime.setText("[응모 마감]"); // 마감 표시
                        endtime.setVisibility(View.GONE);

                        status.setBackgroundResource(R.drawable.win);
                        status.setText("당첨");
                        String win = "#01a1ff";
                        status.setTextColor(Color.parseColor(win));

                        txt_lotsquota.setVisibility(View.GONE);
                        layout_lotsquota.setVisibility(View.GONE);

                        if(adlotsItem.type.equals("giftcon")){
                            if(adlotsItem.finish.equals("")){
                                info.setText("2일 이내 지급 예정입니다.");
                            } else {
                                info.setText("지급 완료");
                            }

                        } else if(adlotsItem.type.equals("delivery")){
                            if(adlotsItem.address.equals("")){
                                info.setText("[클릭] 배송 받을 주소를 입력해주세요.");
                                info.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View v) {
                                        getaddress(adlotsItem.itemid, pref_nickname, adlotsItem.itemname, adlotsItem.type);
                                    }
                                });
                            } else {
                                if(adlotsItem.finish.equals("")){
                                    info.setText("2일 이내 배송 예정입니다.");
                                } else {
                                    info.setText("배송 완료되었습니다.");
                                }
                            }
                        }
                    } else if(adlotsItem.winorlose.equals("lose")) {
                        txt_endtime.setText("[응모 마감]"); // 마감 표시
                        endtime.setVisibility(View.GONE);

                        status.setBackgroundResource(R.drawable.lose);
                        status.setText("미당첨");
                        String lose = "#fc6e51";
                        status.setTextColor(Color.parseColor(lose));
                        info.setText("다음 기회를 기대해주세요.");

                        txt_lotsquota.setVisibility(View.GONE);
                        layout_lotsquota.setVisibility(View.GONE);

                    } else if(adlotsItem.timedone.equals("yes")) {
                        txt_endtime.setText("[시간 마감]"); // 마감 표시
                        endtime.setVisibility(View.GONE);

                        txt_lotsquota.setVisibility(View.GONE);
                        layout_lotsquota.setVisibility(View.GONE);

                        if(adlotsItem.refund.equals("yes")){
                            status.setBackgroundResource(R.drawable.win);
                            status.setText("환불 완료");
                            String win = "#01a1ff";
                            status.setTextColor(Color.parseColor(win));
                            info.setText("응모하신 랏츠의 환불이 완료되었습니다.");

                        } else {
                            status.setBackgroundResource(R.drawable.lose);
                            status.setText("환불 진행중");
                            String lose = "#fc6e51";
                            status.setTextColor(Color.parseColor(lose));
                            info.setText("[클릭] 응모하신 랏츠를 환불해드리겠습니다.");

                            info.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //Dialog에서 보여줄 입력화면 View 객체 생성 작업
                                    final View dialogView = inflater.inflate(R.layout.popup_main_third_refund, null); //Dialog의 listener에서 사용하기 위해 final로 참조변수 선언

                                    AlertDialog.Builder buider = new AlertDialog.Builder(context); //AlertDialog.Builder 객체 생성
                                    buider.setView(dialogView);
                                    buider.setTitle("아이템 정보 및 추가응모");

                                    final TextView refundlots = (TextView) dialogView.findViewById(R.id.main3_popup_refundlots);
                                    final TextView refundremain = (TextView) dialogView.findViewById(R.id.main3_popup_refundremain);

                                    // 한개 아이템 정보 가져오기
                                    HashMap<String, String> data = new HashMap<>();
                                    data.put("nickname", pref_nickname);
                                    data.put("howtobuy", "lots");
                                    data.put("itemid", adlotsItem.itemid); // 조심하기
                                    data.put("userlotspoint", adlotsItem.userlotspoint);

                                    RestClient.AdlotsService service = RestClient.getService();
                                    service.getuserRefund("getrefunditem", data, new Callback<JsonElement>() {
                                        @Override
                                        public void success(JsonElement jsonElement, Response response) {
                                            refundlots.setText(jsonElement.getAsJsonObject().get("userlotspoint").getAsString());
                                            String endpoint = jsonElement.getAsJsonObject().get("endpoint").getAsString();
                                            String nowpoint = jsonElement.getAsJsonObject().get("nowpoint").getAsString();
                                            int num_refundremain = Integer.parseInt(endpoint)-Integer.parseInt(nowpoint);
                                            refundremain.setText(String.valueOf(num_refundremain));
                                        }

                                        @Override
                                        public void failure(RetrofitError error) {
                                            Toast.makeText(context, "오류가 발생했습니다. adlots@naver.com으로 문의해주세요.", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    final AlertDialog dialog = buider.create(); //설정한 값으로 AlertDialog 객체 생성
                                    dialog.setCanceledOnTouchOutside(true); //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                                    dialog.show(); //Dialog 보이기

                                    // 환불받기 버튼 클릭 이벤트
                                    Button btn_refund = (Button) dialogView.findViewById(R.id.main3_popup_btn_refund);
                                    btn_refund.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            // 총 14개 데이터 전송
                                            HashMap<String, String> data = new HashMap<>();
                                            data.put("nickname", pref_nickname);
                                            data.put("howtobuy", "lots");
                                            data.put("itemid", adlotsItem.itemid); // 조심하기
                                            data.put("userlotspoint", adlotsItem.userlotspoint);

                                            RestClient.AdlotsService service = RestClient.getService();
                                            service.getuserRefund("refund", data, new Callback<JsonElement>() {
                                                @Override
                                                public void success(JsonElement jsonElement, Response response) {
                                                    Toast.makeText(context, "응모하신 랏츠가 환불되었습니다.", Toast.LENGTH_SHORT).show();
                                                    main3_refresh(pref_email);
                                                    dialog.dismiss();
                                                }

                                                @Override
                                                public void failure(RetrofitError error) {
                                                    Toast.makeText(context, "오류가 발생했습니다. adlots@naver.com으로 문의해주세요.", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        }

                    } else {
                        status.setText("현재 응모 진행중");
                        info.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //Dialog에서 보여줄 입력화면 View 객체 생성 작업
                                final View dialogView = inflater.inflate(R.layout.popup_main_third_iteminfo, null); //Dialog의 listener에서 사용하기 위해 final로 참조변수 선언

                                AlertDialog.Builder buider = new AlertDialog.Builder(context); //AlertDialog.Builder 객체 생성
                                buider.setView(dialogView);
                                buider.setTitle("아이템 정보 및 추가응모");

                                final TextView howmuchlots = (TextView) dialogView.findViewById(R.id.main3_popup_howmuchlots);
                                final TextView mypoint = (TextView) dialogView.findViewById(R.id.main3_popup_mypoint);
                                final TextView lotspeople = (TextView) dialogView.findViewById(R.id.main3_popup_lotspeople);
                                final TextView remainpoint = (TextView) dialogView.findViewById(R.id.main3_popup_remainpoint);

                                // 한개 아이템 정보 가져오기
                                HashMap<String, String> data = new HashMap<>();
                                data.put("id", adlotsItem.itemid); // 조심하기
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
                                        Toast.makeText(context, "오류가 발생했습니다. adlots@naver.com으로 문의해주세요.", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                final AlertDialog dialog = buider.create(); //설정한 값으로 AlertDialog 객체 생성
                                dialog.setCanceledOnTouchOutside(true); //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                                dialog.show(); //Dialog 보이기

                                // 추가 응모하기 버튼 클릭 이벤트
                                Button btn_lots = (Button) dialogView.findViewById(R.id.main3_popup_btn_lots);
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
                                        data.put("phone", pref_phone);
                                        data.put("howtobuy", "lots");
                                        data.put("itemid", adlotsItem.itemid); // 조심하기
                                        data.put("type", adlotsItem.type);
                                        data.put("category", adlotsItem.category);
                                        data.put("brand", adlotsItem.brand);
                                        data.put("itemname", adlotsItem.itemname);
                                        data.put("imagelink", adlotsItem.imagelink);
                                        data.put("referlink", adlotsItem.referlink);
                                        data.put("endpoint", adlotsItem.endpoint);
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
                                                                main3_refresh(pref_email);
                                                                Toast.makeText(context, "응모가 완료되었습니다. 나의 응모/구입 목록을 확인해주세요.", Toast.LENGTH_SHORT).show();
                                                                dialog.dismiss();
                                                                break;
                                                            case "pointdone":
                                                                main2_refresh();
                                                                main3_refresh(pref_email);
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
                    }

                } else if(adlotsItem.howtobuy.equals("purchase")){ // 구입하기 탭
                    txt_endtime.setText("[구입 완료]");
                    endtime.setVisibility(View.GONE);

                    userlotspoint.setText(adlotsItem.endpoint); // 구입 랏츠
                    txt_userlotspoint.setText("구입 랏츠");
                    txt_when.setText("구입 일시");
                    status.setText("구입 완료");
                    status.setBackgroundResource(R.drawable.win);
                    String win = "#01a1ff";
                    status.setTextColor(Color.parseColor(win));

                    txt_lotsquota.setVisibility(View.GONE);
                    layout_lotsquota.setVisibility(View.GONE);

                    if(adlotsItem.type.equals("giftcon")){
                        if(adlotsItem.finish.equals("")){
                            info.setText("2일 이내 지급 예정입니다.");
                        } else {
                            info.setText("지급 완료되었습니다.");
                        }
                    } else if(adlotsItem.type.equals("delivery")){
                        if(adlotsItem.address.equals("")){
                            info.setText("[클릭] 배송 받을 주소를 입력해주세요.");
                            info.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View v) {
                                    getaddress(adlotsItem.itemid, pref_nickname, adlotsItem.itemname, adlotsItem.type);
                                }
                            });
                        } else {
                            if(adlotsItem.finish.equals("")){
                                info.setText("2일 이내 배송 예정입니다.");
                            } else {
                                info.setText("배송 완료되었습니다.");
                            }
                        }
                    }
                }
                // 구입 일시
                when.setText(adlotsItem.when.substring(5,7)+"월 "+adlotsItem.when.substring(8,10)+"일 "+adlotsItem.when.substring(11,13)+"시");
            }
        }
        return v;
    }

    static class ListHolder {
        ImageView imagelink;
        TextView txt_endtime, endtime, type, category, brand, itemname, userlotspoint;
        TextView txt_userlotspoint, txt_when, when, info, status, lotsquota1, lotsquota2, txt_lotsquota;
        LinearLayout layout_lotsquota;
        FrameLayout layoutnull1;
        LinearLayout layoutnull2, layoutnull3, layoutnull4, layoutnull5, layoutnull6;
    }

    public void getaddress(final String itemid, final String nickname, String itemname, final String type) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //Dialog에서 보여줄 입력화면 View 객체 생성 작업
        final View dialogView = inflater.inflate(R.layout.popup_main_third_address, null); //Dialog의 listener에서 사용하기 위해 final로 참조변수 선언

        AlertDialog.Builder buider = new AlertDialog.Builder(context); //AlertDialog.Builder 객체 생성
        buider.setView(dialogView);
        buider.setTitle("배송받을 주소 입력");

        final TextView txt_address = (TextView) dialogView.findViewById(R.id.main3_popup_address);
        TextView txt_itemname = (TextView) dialogView.findViewById(R.id.main3_popup_itemname);
        txt_itemname.setText(itemname);
        Button btn_address = (Button) dialogView.findViewById(R.id.main3_popup_btn_address);

        final AlertDialog dialog = buider.create(); //설정한 값으로 AlertDialog 객체 생성
        dialog.setCanceledOnTouchOutside(true); //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정

        btn_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = txt_address.getText().toString();
                if(address.equals("")){
                    Toast.makeText(context, "배송 받을 주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    HashMap<String, String> data = new HashMap<>();
                    data.put("itemid", itemid);
                    data.put("nickname", nickname);
                    data.put("address", address);
                    data.put("type", type);

                    RestClient.AdlotsService service = RestClient.getService();
                    service.getuserAddress(data, new Callback<JsonElement>() {
                        @Override
                        public void success(JsonElement jsonElement, Response response) {
                            Toast.makeText(context, "입력하신 주소로 2일 내에 배송해드리겠습니다.", Toast.LENGTH_SHORT).show();
                            main3_refresh();
                            dialog.dismiss();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Toast.makeText(context, "오류가 발생했습니다. adlots@naver.com으로 문의해주세요.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        dialog.show(); //Dialog 보이기
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

    public void main3_refresh(String pref_email) {
        final TextView txt_point = (TextView) MainThirdPage.staticvar.getActivity().findViewById(R.id.main3_userpoint);

        // 유저 포인트 가져오기
        HashMap<String, String> data = new HashMap<>();
        data.put("email", pref_email);
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

        main3_refresh();
    }

    public void main3_refresh() {
        FragmentTransaction transaction = MainThirdPage.staticvar.getChildFragmentManager().beginTransaction();
        Fragment currentFragment = MainThirdPage.staticvar.getChildFragmentManager().findFragmentById(R.id.main3_useritemfragment);
        transaction.detach(currentFragment);
        transaction.attach(currentFragment);
        transaction.commit();
    }

    public int diffOfDate(String start, String end) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        startDate.set(Integer.parseInt(start.substring(0, 4)), Integer.parseInt(start.substring(5, 7)) - 1, Integer.parseInt(start.substring(8, 10)));
        endDate.set(Integer.parseInt(end.substring(0, 4)), Integer.parseInt(end.substring(5, 7)) - 1, Integer.parseInt(end.substring(8, 10)));

        long diffMillis = endDate.getTimeInMillis() - startDate.getTimeInMillis();
        int diffDays = (int)(diffMillis / (24 * 60 * 60 * 1000));

        return diffDays;
    }
}
