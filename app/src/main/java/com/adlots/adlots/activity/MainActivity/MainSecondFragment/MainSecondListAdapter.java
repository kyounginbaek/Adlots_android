package com.adlots.adlots.activity.MainActivity.MainSecondFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

    public MainSecondListAdapter(Context context, int resource, ArrayList<MainSecondItem> items) {
        super(context, resource, items);
        this.layoutResource = resource;
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
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
