package com.adlots.adlots.activity.MainActivity.MainSecondFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.adlots.adlots.R;
import com.adlots.adlots.rest.RestClient;
import com.adlots.adlots.rest.model.MainSecond;

import java.util.ArrayList;

/**
 * Created by baekkyoungin on 16. 3. 22..
 */
public class MainSecondGiftcon extends android.support.v4.app.Fragment {

    private Context giftconcontext = null;
    private View giftconview = null;

    ListView giftconlist;
    MainSecondListAdapter adapter;
    String category = null;
    String brand = null;
    String itemname = null;
    String imagelink = null;
    String referlink = null;
    int endpoint = 0;
    int nowpoint = 0;
    String endtime = null;
    int lotspeople = 0;

    public ArrayList<MainSecond> giftcon = new ArrayList<MainSecond>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        giftconcontext = container.getContext();
        giftconview = (View) inflater.inflate(R.layout.fragment_main_second_giftcon,
                container, false);

        /*
        RestClient.SsmService service = RestClient.getService();
        service.getMySsm("mylist", this_author, this_email, new Callback<List<MySsm>>() {
            @Override
            public void success(List<MySsm> mySsms, Response response) {
                if (mySsms != null) {
                    myssm.add(0,mySsms.get(0));
                    myssm.addAll(mySsms);
                    //adapter.clear();
                    //adapter.addAll(mySsms);
                    adapter.notifyDataSetChanged();
                } else {
                    SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                    this_author = pref.getString("nickname", "");
                    this_email = pref.getString("email","");
                    MySsm temp = new MySsm(this_author, "null");
                    myssm.add(0,temp);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void failure(RetrofitError error) {

            }
        });
        */

        RestClient.AdlotsService service = RestClient.getService();
        service.

        giftconlist = (ListView) giftconview.findViewById(R.id.giftcon_listview);
        adapter = new MainSecondListAdapter(giftconcontext,R.layout.content_main_second_item,giftcon);
        giftconlist.setAdapter(adapter);

        return giftconview ;
    }
}
