package com.adlots.adlots.activity.MainActivity.MainThirdFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.adlots.adlots.R;
import com.adlots.adlots.rest.RestClient;
import com.adlots.adlots.rest.model.MainThirdItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by baekkyoungin on 16. 5. 6..
 */
public class MainThirdUserPurchase extends android.support.v4.app.Fragment {

    private Context userpurchaseContext = null;
    private View userpurchaseView = null;

    ListView userpurchaseList;
    MainThirdListAdapter userpurchaseAdapter;
    public ArrayList<MainThirdItem> userpurchaseArray = new ArrayList<MainThirdItem>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        userpurchaseContext = container.getContext();
        userpurchaseView = (View) inflater.inflate(R.layout.fragment_main_third_user_purchase,
                container, false);

        SharedPreferences pref = getActivity().getSharedPreferences("pref", userpurchaseContext.MODE_PRIVATE);
        String pref_nickname = pref.getString("nickname", "");
        String pref_ispurchase = pref.getString("ispurchase", "");

        HashMap<String, String> data = new HashMap<>();
        data.put("nickname", pref_nickname);

        RestClient.AdlotsService service = RestClient.getService();
        service.getuserItem("purchase", data, new Callback<List<MainThirdItem>>() {
            @Override
            public void success(List<MainThirdItem> getitem, Response response) {
                if(getitem!=null) {
                    userpurchaseArray.addAll(getitem);
                    userpurchaseAdapter.notifyDataSetChanged();
                } else {
                    MainThirdItem temp = new MainThirdItem("null", "purchase"); // id에 null 입력
                    userpurchaseArray.add(0,temp);
                    userpurchaseAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void failure(RetrofitError error) {
            }
        });

        userpurchaseList = (ListView) userpurchaseView.findViewById(R.id.userpurchase_listview);
        userpurchaseAdapter = new MainThirdListAdapter(userpurchaseContext, R.layout.content_main_third_item, userpurchaseArray);
        userpurchaseList.setAdapter(userpurchaseAdapter);
        userpurchaseAdapter.clear();

        return userpurchaseView;
    }
}
