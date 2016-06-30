package com.adlots.androidapp.activity.MainActivity.MainFourthActivity;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adlots.android.R;
import com.adlots.androidapp.helper.ImageLoadTask;
import com.adlots.androidapp.rest.model.MainFourthWinner;

import java.util.ArrayList;

/**
 * Created by baekkyoungin on 16. 3. 31..
 */
public class MainFourthWinnerAdapter extends ArrayAdapter<MainFourthWinner> {
    private Context context;
    private ArrayList<MainFourthWinner> items;
    int layoutResource;

    public MainFourthWinnerAdapter(Context context, int resource, ArrayList<MainFourthWinner> items) {
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
        final MainFourthWinner adlotsItem = items.get(position);

        // null 설정
        ImageView imagelink = null;
        TextView whendone = null, type = null, category = null, brand = null, itemname = null;
        TextView lotspeople = null, userlotspoint = null, endpoint = null, status = null, nickname = null;
        FrameLayout layoutnull1 = null;
        LinearLayout layoutnull2 = null, layoutnull3 = null, layoutnull4 = null, layoutnull5 = null, layoutnull6 = null;
        ListHolder holder = null;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(layoutResource, parent, false);

            imagelink = (ImageView) v.findViewById(R.id.main4_imagelink);
            whendone = (TextView) v.findViewById(R.id.main4_whendone);
            type = (TextView) v.findViewById(R.id.main4_type);
            category = (TextView) v.findViewById(R.id.main4_category);
            brand = (TextView) v.findViewById(R.id.main4_brand);
            itemname = (TextView) v.findViewById(R.id.main4_itemname);
            lotspeople = (TextView) v.findViewById(R.id.main4_lotspeople);
            userlotspoint = (TextView) v.findViewById(R.id.main4_userlotspoint);
            endpoint = (TextView) v.findViewById(R.id.main4_endpoint);
            status = (TextView) v.findViewById(R.id.main4_status);
            nickname = (TextView) v.findViewById(R.id.main4_nickname);
            layoutnull1 = (FrameLayout) v.findViewById(R.id.main4_layoutnull1);
            layoutnull2 = (LinearLayout) v.findViewById(R.id.main4_layoutnull2);
            layoutnull3 = (LinearLayout) v.findViewById(R.id.main4_layoutnull3);
            layoutnull4 = (LinearLayout) v.findViewById(R.id.main4_layoutnull4);
            layoutnull5 = (LinearLayout) v.findViewById(R.id.main4_layoutnull5);
            layoutnull6 = (LinearLayout) v.findViewById(R.id.main4_layoutnull6);

            // holder 생성 및 Tag로 등록
            holder = new ListHolder();
            holder.imagelink = imagelink;
            holder.whendone = whendone;
            holder.type = type;
            holder.category = category;
            holder.brand = brand;
            holder.itemname = itemname;
            holder.lotspeople = lotspeople;
            holder.userlotspoint = userlotspoint;
            holder.endpoint = endpoint;
            holder.status = status;
            holder.nickname = nickname;
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
            whendone = holder.whendone;
            type = holder.type;
            category = holder.category;
            brand = holder.brand;
            itemname = holder.itemname;
            lotspeople = holder.lotspeople;
            userlotspoint = holder.userlotspoint;
            endpoint = holder.endpoint;
            status = holder.status;
            nickname = holder.nickname;
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
                itemname.setText("\n당첨자가 아직 없습니다.\n\n상품을 응모해 당첨자가 되어보세요!\n");
                itemname.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);

                layoutnull1.setVisibility(View.GONE);
                layoutnull2.setVisibility(View.GONE);
                layoutnull3.setVisibility(View.GONE);
                layoutnull4.setVisibility(View.GONE);
                layoutnull5.setVisibility(View.GONE);
                layoutnull6.setVisibility(View.GONE);

            } else {
                new ImageLoadTask(adlotsItem.imagelink, imagelink).execute();
                /*holder.imagelink.setOnClickListener(new View.OnClickListener() {
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
                });*/

                whendone.setText(adlotsItem.whendone.substring(5,7) + "월 " + adlotsItem.whendone.substring(8,10) + "일");

                if(adlotsItem.type.equals("giftcon")){
                    type.setText("기프트콘");
                } else if(adlotsItem.type.equals("delivery")){
                    type.setText("배송물품");
                }
                category.setText(adlotsItem.category);
                brand.setText(adlotsItem.brand);
                itemname.setText(adlotsItem.itemname);
                lotspeople.setText(adlotsItem.lotspeople);
                userlotspoint.setText(adlotsItem.userlotspoint);
                endpoint.setText(adlotsItem.endpoint);
                nickname.setText(adlotsItem.nickname);
            }
        }
        return v;
    }

    static class ListHolder {
        ImageView imagelink;
        TextView whendone, type, category, brand, itemname;
        TextView lotspeople, userlotspoint, endpoint, status, nickname;
        FrameLayout layoutnull1;
        LinearLayout layoutnull2, layoutnull3, layoutnull4, layoutnull5, layoutnull6;
    }
}
