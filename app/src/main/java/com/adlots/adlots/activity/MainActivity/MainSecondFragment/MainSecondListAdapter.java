package com.adlots.adlots.activity.MainActivity.MainSecondFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.adlots.adlots.helper.Util;
import com.adlots.adlots.rest.model.MainSecond;

import java.util.ArrayList;

/**
 * Created by baekkyoungin on 16. 3. 31..
 */
public class MainSecondListAdapter extends ArrayAdapter<MainSecond> {
    private Context context;
    private ArrayList<MainSecond> items;
    private String written_num;
    private String open_num;
    private String first_date;
    private String last_coment;
    private String author;
    int layoutResource;


    private static final int TYPE_COUNT = 2;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_BANNER = 1;

    public MainSecondListAdapter(Context context, int resource, ArrayList<MainSecond> items) {
        super(context, resource, items);
        this.layoutResource = resource;
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        //Card card = items.get(position);
        // return  TYPE_ITEM;
        if (position==0)
            return TYPE_BANNER;
        else
            return TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    private View getItemView(int position,View convertView, ViewGroup parent) {
        View v = convertView;
        //ViewGroup vg = (ViewGroup) convertView;
        //ViewGroup root = (ViewGroup) parent.findViewById(R.id.content);
        MySsmListHolder holder =  null;
        if(v==null){
            LayoutInflater vi =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(layoutResource,parent,false);
            Util.setGlobalFont(context, v);
            holder = new MySsmListHolder();
            /*
            holder.myssm_number = (TextView) v.findViewById(com.projectm.ezbrother.ssm.R.id.myssm_number);
            holder.myssm_word = (TextView) v.findViewById(com.projectm.ezbrother.ssm.R.id.myssm_word);
            holder.myssm_date = (TextView) v.findViewById(com.projectm.ezbrother.ssm.R.id.myssm_date);
            v.setTag(holder);
            */
        }
        else{
            holder = (MySsmListHolder)v.getTag();
        }
        MainSecond mySsmItem = items.get(position);
        if(mySsmItem!=null){
            //
            // holder.myssm_number.setText(mySsmItem.myssm_id);
            holder.myssm_word.setText(mySsmItem.word);
            holder.myssm_date.setText(mySsmItem.when);
        }
        //setGlobalFont(parent);

        return v;
    }

    private View getBannerView(int position,View convertView, ViewGroup parent) {
        View v  = convertView;
        BannerViewHolder holder = null;
        if(v==null){
            LayoutInflater vi =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            /*
            v = vi.inflate(com.projectm.ezbrother.ssm.R.layout.myssm_banner,parent,false);
            Util.setGlobalFont(context, v);
            holder = new BannerViewHolder();
            holder.tv_written_num = (TextView) v.findViewById(com.projectm.ezbrother.ssm.R.id.myssm_banner_written);
            holder.tv_open_num= (TextView) v.findViewById(com.projectm.ezbrother.ssm.R.id.myssm_banner_open);
            holder.tv_first_date = (TextView) v.findViewById(com.projectm.ezbrother.ssm.R.id.myssm_banner_author);
            holder.tv_last_coment = (TextView) v.findViewById(com.projectm.ezbrother.ssm.R.id.myssm_banner_comment);
            v.setTag(holder);
            */
        }
        else{
            holder = (BannerViewHolder)v.getTag();
        }
        MainSecond timeLineFilterItem = (MainSecond) items.get(position);
        if(timeLineFilterItem!=null){
            if (items.get(0).id.equals("null")) {

                holder.tv_written_num.setText("0");
                holder.tv_open_num.setText("0");
                holder.tv_first_date.setText(items.get(0).author);
                holder.tv_last_coment.setText("첫 번째 씀을 기다리고 있습니다.");

            }else {
                written_num = String.valueOf(items.size()-1);
                int open_int=0;
                for (int i = 0; i< items.size(); i++) {
                    if (items.get(i).open.equals("1")) {
                        open_int++;
                    }
                }
                if(open_int != 0) {
                    open_int--;
                }
                open_num = String.valueOf(open_int);

                int temp = items.size() - 1;
                first_date = items.get(temp).when;

                last_coment = first_date+", 첫 번째 씀";
                holder.tv_written_num.setText(written_num);
                holder.tv_open_num.setText(open_num);
                holder.tv_first_date.setText(items.get(0).author);
                holder.tv_last_coment.setText(last_coment);
            }


        }

        return v;
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {

        switch (getItemViewType(position)) {
            case TYPE_ITEM:
                return getItemView(position, convertView, parent);
            case TYPE_BANNER:
                return getBannerView(position, convertView, parent);
        }

        return null;
    }


    class BannerViewHolder {
        TextView tv_written_num;
        TextView tv_open_num;
        TextView tv_first_date;
        TextView tv_last_coment;
    }


    static class MySsmListHolder
    {
        TextView myssm_number;
        TextView myssm_word;
        TextView myssm_date;
    }

}
