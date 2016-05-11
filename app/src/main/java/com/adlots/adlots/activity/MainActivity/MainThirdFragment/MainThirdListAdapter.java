package com.adlots.adlots.activity.MainActivity.MainThirdFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.adlots.adlots.activity.MainActivity.MainThirdPage;
import com.adlots.adlots.helper.ImageLoadTask;
import com.adlots.adlots.rest.RestClient;
import com.adlots.adlots.rest.model.MainThirdItem;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

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
    ListHolder holder;

    public MainThirdListAdapter(Context context, int resource, ArrayList<MainThirdItem> items) {
        super(context, resource, items);
        this.layoutResource = resource;
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View v = convertView;
        final MainThirdItem adlotsItem = items.get(position);

        SharedPreferences pref = context.getSharedPreferences("pref", context.MODE_PRIVATE);
        final String pref_nickname = pref.getString("nickname", "");

        holder = null;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(layoutResource, parent, false);

            holder = new ListHolder();
            holder.imagelink = (ImageView) v.findViewById(R.id.main3_imagelink);
            holder.endtime = (TextView) v.findViewById(R.id.main3_endtime);
            holder.type = (TextView) v.findViewById(R.id.main3_type);
            holder.category = (TextView) v.findViewById(R.id.main3_category);
            holder.brand = (TextView) v.findViewById(R.id.main3_brand);
            holder.itemname = (TextView) v.findViewById(R.id.main3_itemname);
            holder.endpoint = (TextView) v.findViewById(R.id.main3_endpoint);
            holder.nowpoint = (TextView) v.findViewById(R.id.main3_nowpoint);
            holder.lotspeople = (TextView) v.findViewById(R.id.main3_lotspeople);
            holder.userlotspoint = (TextView) v.findViewById(R.id.main3_userlotspoint);
            holder.whentype = (TextView) v.findViewById(R.id.main3_whentype);
            holder.when = (TextView) v.findViewById(R.id.main3_when);
            holder.status = (TextView) v.findViewById(R.id.main3_status);
            holder.finish = (TextView) v.findViewById(R.id.main3_finish);

            holder.layoutnull1 = (FrameLayout) v.findViewById(R.id.main3_layoutnull1);
            holder.layoutnull2 = (LinearLayout) v.findViewById(R.id.main3_layoutnull2);
            holder.layoutnull3 = (LinearLayout) v.findViewById(R.id.main3_layoutnull3);
            holder.layoutnull4 = (LinearLayout) v.findViewById(R.id.main3_layoutnull4);
            holder.layoutnull5 = (LinearLayout) v.findViewById(R.id.main3_layoutnull5);
            holder.layoutnull6 = (LinearLayout) v.findViewById(R.id.main3_layoutnull6);
            holder.layoutnull7 = (LinearLayout) v.findViewById(R.id.main3_layoutnull7);
            holder.layoutnull8 = (LinearLayout) v.findViewById(R.id.main3_layoutnull8);

            v.setTag(holder);
        } else {
            holder = (ListHolder) v.getTag();
        }

        // 리스트에 아이템 값 넣기
        if (adlotsItem != null) {
            // 만약 응모 & 구입 사실이 없을 경우
            if(items.get(0).id.equals("null")) {
                if(items.get(0).howtobuy.equals("lots")){
                    holder.itemname.setText("\n응모하신 내역이 없습니다.\n\n포인트를 경품에 응모해보세요!\n");
                } else {
                    holder.itemname.setText("\n바로구입하신 내역이 없습니다.\n\n포인트로 상품을 구입해보세요!\n");
                }
                holder.layoutnull1.setVisibility(View.GONE);
                holder.layoutnull2.setVisibility(View.GONE);
                holder.layoutnull3.setVisibility(View.GONE);
                holder.layoutnull4.setVisibility(View.GONE);
                holder.layoutnull5.setVisibility(View.GONE);
                holder.layoutnull6.setVisibility(View.GONE);
                holder.layoutnull7.setVisibility(View.GONE);
                holder.layoutnull8.setVisibility(View.GONE);
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

                holder.endtime.setText(adlotsItem.endtime.substring(5,7)+"월 "+adlotsItem.endtime.substring(8,10)+"일 "+adlotsItem.endtime.substring(11,13)+"시");
                if(adlotsItem.type.equals("giftcon")){
                    holder.type.setText("기프트콘");
                } else if(adlotsItem.type.equals("delivery")){
                    holder.type.setText("배송물품");
                }
                holder.category.setText(adlotsItem.category);
                holder.brand.setText(adlotsItem.brand);
                holder.itemname.setText(adlotsItem.itemname);
                holder.endpoint.setText(adlotsItem.endpoint);
                holder.nowpoint.setText(adlotsItem.nowpoint);
                holder.lotspeople.setText(adlotsItem.lotspeople);

                if(adlotsItem.howtobuy.equals("lots")) {
                    holder.userlotspoint.setText(adlotsItem.userlotspoint);
                    holder.whentype.setText("응모 일시");
                    if(adlotsItem.winorlose.equals("win")){
                        holder.status.setText("마감\n<당첨>");

                        if(adlotsItem.type.equals("giftcon")){
                            holder.finish.setText("2일 이내 지급 예정");

                        } else if(adlotsItem.type.equals("delivery")){
                            if(adlotsItem.address.equals("")){
                                holder.finish.setText("배송 받을 주소 입력");
                                holder.finish.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View v) {
                                        getaddress(adlotsItem.itemid, pref_nickname, adlotsItem.itemname);
                                    }
                                });
                            } else {
                                if(adlotsItem.finish.equals("")){
                                    holder.finish.setText("2일 이내 지급 예정");
                                } else {
                                    holder.finish.setText("지급 완료");
                                }
                            }
                        }
                    } else if(adlotsItem.winorlose.equals("lose")){
                        holder.status.setText("마감\n<미당첨>");
                        holder.finish.setVisibility(View.GONE);

                    } else {
                        holder.status.setText("현재 응모 진행중");
                        holder.finish.setVisibility(View.GONE);
                    }
                } else if(adlotsItem.howtobuy.equals("purchase")){
                    holder.userlotspoint.setText(adlotsItem.endpoint);
                    holder.whentype.setText("구입 일시");
                    holder.status.setText("구입 완료");

                    if(adlotsItem.type.equals("giftcon")){
                        holder.finish.setText("2일 이내 지급 예정");

                    } else if(adlotsItem.type.equals("delivery")){
                        if(adlotsItem.address.equals("")){
                            holder.finish.setText("배송 받을 주소 입력");
                            holder.finish.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View v) {
                                    getaddress(adlotsItem.itemid, pref_nickname, adlotsItem.itemname);
                                }
                            });
                        } else {
                            if(adlotsItem.finish.equals("")){
                                holder.finish.setText("2일 이내 지급 예정");
                            } else {
                                holder.finish.setText("지급 완료");
                            }
                        }
                    }
                }
                holder.when.setText(adlotsItem.endtime.substring(5,7)+"월 "+adlotsItem.endtime.substring(8,10)+"일 "+adlotsItem.endtime.substring(11,13)+"시");
            }
        }
        return v;
    }

    static class ListHolder {
        TextView category, brand, itemname;
        ImageView imagelink;
        TextView endtime;
        TextView endpoint, nowpoint, lotspeople;
        TextView type, userlotspoint, whentype, when, status, finish;

        FrameLayout layoutnull1;
        LinearLayout layoutnull2, layoutnull3, layoutnull4, layoutnull5, layoutnull6, layoutnull7, layoutnull8;
    }

    public void getaddress(final String itemid, final String nickname, String itemname) {
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

                    RestClient.AdlotsService service = RestClient.getService();
                    service.getuserAddress(data, new Callback<JsonElement>() {
                        @Override
                        public void success(JsonElement jsonElement, Response response) {
                            Toast.makeText(context, "입력하신 주소로 2일 내에 배송해드리겠습니다.", Toast.LENGTH_SHORT).show();
                            main3_refresh();
                            holder.finish.setText("2일 이내 지급 예정");
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

    public void main3_refresh() {
        FragmentTransaction transaction = MainThirdPage.staticvar.getChildFragmentManager().beginTransaction();
        Fragment currentFragment = MainThirdPage.staticvar.getChildFragmentManager().findFragmentById(R.id.main3_fragment);
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
