package com.adlots.androidapp.activity.MainActivity.MainThirdFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.adlots.android.R;
import com.adlots.androidapp.rest.RestClient;
import com.adlots.androidapp.rest.model.MainThirdItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by baekkyoungin on 16. 3. 22..
 */
public class MainThirdUserLots extends android.support.v4.app.Fragment {

    private Context userlotsContext = null;
    private View userlotsView = null;

    ListView userlotsList;
    MainThirdListAdapter userlotsAdapter;
    public ArrayList<MainThirdItem> userlotsArray = new ArrayList<MainThirdItem>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        userlotsContext = container.getContext();
        userlotsView = (View) inflater.inflate(R.layout.fragment_main_third_user_lots,
                container, false);

        SharedPreferences pref = getActivity().getSharedPreferences("pref", userlotsContext.MODE_PRIVATE);
        String pref_nickname = pref.getString("nickname", "");

        HashMap<String, String> data = new HashMap<>();
        data.put("nickname", pref_nickname);

        RestClient.AdlotsService service = RestClient.getService();
        service.getuserItem("lots", data, new Callback<List<MainThirdItem>>() {
            @Override
            public void success(List<MainThirdItem> getitem, Response response) {
                if(getitem!=null) {
                    userlotsArray.addAll(getitem);
                    userlotsAdapter.notifyDataSetChanged();
                } else {
                    MainThirdItem temp = new MainThirdItem("null", "lots"); // id에 null 입력
                    userlotsArray.add(0, temp);
                    userlotsAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void failure(RetrofitError error) {
            }
        });

        userlotsList = (ListView) userlotsView.findViewById(R.id.userlots_listview);
        userlotsAdapter = new MainThirdListAdapter(userlotsContext, R.layout.content_main_third_item, userlotsArray);
        userlotsList.setAdapter(userlotsAdapter);
        userlotsAdapter.clear();

        return userlotsView;
    }
}
