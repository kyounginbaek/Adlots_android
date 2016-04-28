package com.adlots.adlots.activity.MainActivity.MainThirdFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adlots.adlots.R;
import com.adlots.adlots.helper.ImageLoadTask;
import com.adlots.adlots.rest.model.MainThirdItem;

import java.util.ArrayList;

/**
 * Created by baekkyoungin on 16. 3. 31..
 */
public class MainThirdListAdapter extends ArrayAdapter<MainThirdItem> {
    private Context context;
    private ArrayList<MainThirdItem> items;
    int layoutResource;

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

        ListHolder holder = null;
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
            holder.when = (TextView) v.findViewById(R.id.main3_when);
            holder.howtobuy = (TextView) v.findViewById(R.id.main3_howtobuy);
            holder.status = (TextView) v.findViewById(R.id.main3_status);

            v.setTag(holder);
        } else {
            holder = (ListHolder) v.getTag();
        }

        // 리스트에 아이템 값 넣기
        if (adlotsItem != null) {
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

            holder.endtime.setText(adlotsItem.endtime);
            holder.type.setText(adlotsItem.type);
            holder.category.setText(adlotsItem.category);
            holder.brand.setText(adlotsItem.brand);
            holder.itemname.setText(adlotsItem.itemname);
            holder.endpoint.setText(adlotsItem.endpoint);
            holder.nowpoint.setText(adlotsItem.nowpoint);
            holder.lotspeople.setText(adlotsItem.lotspeople);

            if(adlotsItem.howtobuy.equals("lots")) {
                holder.userlotspoint.setText(adlotsItem.userlotspoint);
                holder.howtobuy.setText("응모 신청");
                holder.status.setText("현재 진행중");
                // 당첨 여부 알려줄 예정
                // 만약 기간이 되어도 마감이 안될 경우 환불
                // 만약 배송 상품일 경우 버튼으로 배송 주소 받기
            } else {
                holder.userlotspoint.setText(adlotsItem.endpoint);
                holder.howtobuy.setText("바로 구입");
                holder.status.setText("2일 내로 지급 예정");
                // 지급이 완료되면 지급 완료로 표시
                // 만약 배송 상품일 경우 버튼으로 배송 주소 받기
            }

            holder.when.setText(adlotsItem.when);
        }

        return v;
    }

    static class ListHolder {
        TextView category, brand, itemname;
        ImageView imagelink;
        TextView endtime;
        TextView endpoint, nowpoint, lotspeople;
        TextView type, userlotspoint, when, howtobuy, status;
    }
}
