package com.adlots.adlots.activity.MainActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adlots.adlots.R;
import com.adlots.adlots.helper.ImageLoadTask;
import com.adlots.adlots.rest.model.MainFourthWinner;

import java.util.ArrayList;

/**
 * Created by baekkyoungin on 16. 3. 31..
 */
public class MainFourthWinnerAdapter extends ArrayAdapter<MainFourthWinner> {
    private Context context;
    private ArrayList<MainFourthWinner> items;
    int layoutResource;
    ListHolder holder;

    public MainFourthWinnerAdapter(Context context, int resource, ArrayList<MainFourthWinner> items) {
        super(context, resource, items);
        this.layoutResource = resource;
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View v = convertView;
        final MainFourthWinner adlotsItem = items.get(position);

        holder = null;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(layoutResource, parent, false);

            holder = new ListHolder();
            holder.imagelink = (ImageView) v.findViewById(R.id.main4_imagelink);
            holder.whendone = (TextView) v.findViewById(R.id.main4_whendone);
            holder.type = (TextView) v.findViewById(R.id.main4_type);
            holder.category = (TextView) v.findViewById(R.id.main4_category);
            holder.brand = (TextView) v.findViewById(R.id.main4_brand);
            holder.itemname = (TextView) v.findViewById(R.id.main4_itemname);
            holder.lotspeople = (TextView) v.findViewById(R.id.main4_lotspeople);
            holder.userlotspoint = (TextView) v.findViewById(R.id.main4_userlotspoint);
            holder.endpoint = (TextView) v.findViewById(R.id.main4_endpoint);
            holder.status = (TextView) v.findViewById(R.id.main4_status);
            holder.nickname = (TextView) v.findViewById(R.id.main4_nickname);

            holder.layoutnull1 = (FrameLayout) v.findViewById(R.id.main4_layoutnull1);
            holder.layoutnull2 = (LinearLayout) v.findViewById(R.id.main4_layoutnull2);
            holder.layoutnull3 = (LinearLayout) v.findViewById(R.id.main4_layoutnull3);
            holder.layoutnull4 = (LinearLayout) v.findViewById(R.id.main4_layoutnull4);
            holder.layoutnull5 = (LinearLayout) v.findViewById(R.id.main4_layoutnull5);
            holder.layoutnull6 = (LinearLayout) v.findViewById(R.id.main4_layoutnull6);

            v.setTag(holder);
        } else {
            holder = (ListHolder) v.getTag();
        }

        // 리스트에 아이템 값 넣기
        if (adlotsItem != null) {
            // 만약 응모 & 구입 사실이 없을 경우
            if(items.get(0).id.equals("null")) {
                holder.itemname.setText("\n당첨자가 아직 없습니다.\n\n상품을 응모해 당첨자가 되어보세요!\n");
                holder.itemname.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);

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

                holder.whendone.setText(adlotsItem.whendone.substring(5, 7) + "월 " + adlotsItem.whendone.substring(8, 10) + "일 " + adlotsItem.whendone.substring(11, 13) + "시");

                if(adlotsItem.type.equals("giftcon")){
                    holder.type.setText("기프트콘");
                } else if(adlotsItem.type.equals("delivery")){
                    holder.type.setText("배송물품");
                }
                holder.category.setText(adlotsItem.category);
                holder.brand.setText(adlotsItem.brand);
                holder.itemname.setText(adlotsItem.itemname);
                holder.lotspeople.setText(adlotsItem.lotspeople);
                holder.userlotspoint.setText(adlotsItem.userlotspoint);
                holder.endpoint.setText(adlotsItem.endpoint);
                holder.nickname.setText(adlotsItem.nickname);
            }
        }
        return v;
    }

    static class ListHolder {
        TextView category, brand, itemname;
        ImageView imagelink;
        TextView whendone, lotspeople, endpoint;
        TextView type, userlotspoint, status, nickname;

        FrameLayout layoutnull1;
        LinearLayout layoutnull2, layoutnull3, layoutnull4, layoutnull5, layoutnull6;
    }
}
