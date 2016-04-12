package com.adlots.adlots.activity.MainActivity.MainSecondFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adlots.adlots.R;
import com.adlots.adlots.helper.ImageLoadTask;
import com.adlots.adlots.rest.model.MainSecondItem;

import java.util.ArrayList;

/**
 * Created by baekkyoungin on 16. 3. 31..
 */
public class MainSecondListAdapter extends ArrayAdapter<MainSecondItem> {
    private Context context;
    private ArrayList<MainSecondItem> items;
    int layoutResource;
    TextView textbtn_lots, textbtn_buy;

    public MainSecondListAdapter(Context context, int resource, ArrayList<MainSecondItem> items) {
        super(context, resource, items);
        this.layoutResource = resource;
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View v = convertView;
        ListHolder holder = null;
        if(v==null){
            LayoutInflater vi =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(layoutResource,parent,false);

            holder = new ListHolder();
            holder.category = (TextView) v.findViewById(R.id.main2_category);
            holder.brand = (TextView) v.findViewById(R.id.main2_brand);
            holder.itemname = (TextView) v.findViewById(R.id.main2_itemname);

            holder.imagelink = (ImageView) v.findViewById(R.id.main2_imagelink);
            holder.endtime = (TextView) v.findViewById(R.id.main2_endtime);

            holder.endpoint = (TextView) v.findViewById(R.id.main2_endpoint);
            holder.nowpoint = (TextView) v.findViewById(R.id.main2_nowpoint);
            holder.lotspeople = (TextView) v.findViewById(R.id.main2_lotspeople);

            textbtn_lots = (TextView) v.findViewById(R.id.main2_textbtn_lots);
            textbtn_lots.setTag(position);
            textbtn_lots.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 클릭시 추첨 페이지 팝업되기
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //Dialog에서 보여줄 입력화면 View 객체 생성 작업
                    final View dialogView = inflater.inflate(R.layout.popup_main_second_itemlots, null); //Dialog의 listener에서 사용하기 위해 final로 참조변수 선언

                    AlertDialog.Builder buider = new AlertDialog.Builder(context); //AlertDialog.Builder 객체 생성
                    buider.setView(dialogView); //위에서 inflater가 만든 dialogView 객체 세팅
                    buider.setTitle("몇 랏츠를 응모하시겠습니까?");

                    AlertDialog dialog = buider.create(); //설정한 값으로 AlertDialog 객체 생성
                    dialog.setCanceledOnTouchOutside(true); //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                    dialog.show(); //Dialog 보이기
                }
            });

            textbtn_buy = (TextView) v.findViewById(R.id.main2_textbtn_buy);
            textbtn_buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //Dialog에서 보여줄 입력화면 View 객체 생성 작업
                    AlertDialog.Builder buider = new AlertDialog.Builder(context); //AlertDialog.Builder 객체 생성
                    buider.setTitle("바로구입 확인")
                            .setMessage("바로구입 하시겠습니까? " +
                                    "구입하신 포인트만큼 포인트가 차감됩니다.")
                            .setCancelable(true)
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                // 확인 버튼 클릭시 설정
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.cancel();
                                    Toast.makeText(context, "바로구입 되었습니다.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                // 취소 버튼 클릭시 설정
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog dialog = buider.create(); //설정한 값으로 AlertDialog 객체 생성
                    dialog.setCanceledOnTouchOutside(true); //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                    dialog.show(); //Dialog 보이기
                }
            });

            v.setTag(holder);
        }
        else{
            holder = (ListHolder)v.getTag();
        }

        MainSecondItem adlotsItem = items.get(position);
        if(adlotsItem!=null){
            holder.category.setText(adlotsItem.category);
            holder.brand.setText(adlotsItem.brand);
            holder.itemname.setText(adlotsItem.itemname);

            new ImageLoadTask(adlotsItem.imagelink, holder.imagelink).execute();
            holder.endtime.setText(adlotsItem.endtime);

            holder.endpoint.setText(adlotsItem.endpoint);
            holder.nowpoint.setText(adlotsItem.nowpoint);
            holder.lotspeople.setText(adlotsItem.lotspeople);
        }

        return v;
    }

    static class ListHolder {
        TextView category, brand, itemname;
        ImageView imagelink;
        TextView endtime;
        TextView endpoint, nowpoint, lotspeople;
    }
}
