package com.adlots.adlots.activity.MainActivity.MainSecondFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.adlots.adlots.R;
import com.adlots.adlots.rest.RestClient;
import com.adlots.adlots.rest.model.MainSecondItem;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by baekkyoungin on 16. 3. 22..
 */
public class MainSecondGiftcon extends android.app.Fragment {

    private Context giftconContext = null;
    private View giftconView = null;

    ListView giftconList;
    MainSecondListAdapter giftconAdapter;
    public ArrayList<MainSecondItem> giftconArray = new ArrayList<MainSecondItem>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        giftconContext = container.getContext();
        giftconView = (View) inflater.inflate(R.layout.fragment_main_second_giftcon,
                container, false);

        RestClient.AdlotsService service = RestClient.getService();
        service.getItem("giftcon", new Callback<List<MainSecondItem>>() {
            @Override
            public void success(List<MainSecondItem> getitem, Response response) {
                giftconArray.addAll(getitem);
                giftconAdapter.notifyDataSetChanged();
            }
            @Override
            public void failure(RetrofitError error) {

            }
        });

        giftconList = (ListView) giftconView.findViewById(R.id.giftcon_listview);
        giftconAdapter = new MainSecondListAdapter(giftconContext, R.layout.content_main_second_item, giftconArray);
        giftconList.setAdapter(giftconAdapter);
        giftconAdapter.clear();

        return giftconView;
    }
}
