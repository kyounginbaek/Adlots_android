package com.adlots.androidapp.activity.MainActivity.MainSecondFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.adlots.android.R;
import com.adlots.androidapp.rest.RestClient;
import com.adlots.androidapp.rest.model.MainSecondItem;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by baekkyoungin on 16. 3. 22..
 */
public class MainSecondDelivery extends Fragment {

    private Context deliveryContext = null;
    private View deliveryView = null;

    ListView deliveryList;
    MainSecondListAdapter deliveryAdapter;
    public ArrayList<MainSecondItem> deliveryArray = new ArrayList<MainSecondItem>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        deliveryContext = container.getContext();
        deliveryView = (View) inflater.inflate(R.layout.fragment_main_second_delivery,
                container, false);

        RestClient.AdlotsService service = RestClient.getService();
        service.getitemList("delivery", new Callback<List<MainSecondItem>>() {
            @Override
            public void success(List<MainSecondItem> getitem, Response response) {
                if (getitem != null) {
                    deliveryArray.addAll(getitem);
                    deliveryAdapter.notifyDataSetChanged();
                } else {
                    MainSecondItem temp = new MainSecondItem("null", "delivery"); // id에 null 입력
                    deliveryArray.add(0, temp);
                    deliveryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        deliveryList = (ListView) deliveryView.findViewById(R.id.delivery_listview);
        deliveryAdapter = new MainSecondListAdapter(deliveryContext, R.layout.content_main_second_item, deliveryArray);
        deliveryList.setAdapter(deliveryAdapter);
        deliveryAdapter.clear();

        return deliveryView;
    }
}
