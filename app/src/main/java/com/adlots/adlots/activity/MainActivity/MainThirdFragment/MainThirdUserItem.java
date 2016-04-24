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
 * Created by baekkyoungin on 16. 3. 22..
 */
public class MainThirdUserItem extends android.support.v4.app.Fragment {

    private Context useritemContext = null;
    private View useritemView = null;

    ListView useritemList;
    MainThirdListAdapter useritemAdapter;
    public ArrayList<MainThirdItem> useritemArray = new ArrayList<MainThirdItem>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        useritemContext = container.getContext();
        useritemView = (View) inflater.inflate(R.layout.fragment_main_third_user_item,
                container, false);

        SharedPreferences pref = getActivity().getSharedPreferences("pref", useritemContext.MODE_PRIVATE);
        String pref_email = pref.getString("email", "");
        String pref_password = pref.getString("password", "");

        HashMap<String, String> data = new HashMap<>();
        data.put("email", pref_email);
        data.put("password", pref_password);

        RestClient.AdlotsService service = RestClient.getService();
        service.getuserItem(data, new Callback<List<MainThirdItem>>() {
            @Override
            public void success(List<MainThirdItem> getitem, Response response) {
                useritemArray.addAll(getitem);
                useritemAdapter.notifyDataSetChanged();
            }
            @Override
            public void failure(RetrofitError error) {
            }
        });

        useritemList = (ListView) useritemView.findViewById(R.id.giftcon_listview);
        useritemAdapter = new MainThirdListAdapter(useritemContext, R.layout.content_main_third_item, useritemArray);
        useritemList.setAdapter(useritemAdapter);
        useritemAdapter.clear();

        return useritemView;
    }
}
