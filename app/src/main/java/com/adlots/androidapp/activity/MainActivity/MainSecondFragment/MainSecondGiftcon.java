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
public class MainSecondGiftcon extends Fragment {

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
        service.getitemList("giftcon", new Callback<List<MainSecondItem>>() {
            @Override
            public void success(List<MainSecondItem> getitem, Response response) {
                if (getitem != null) {
                    giftconArray.addAll(getitem);
                    giftconAdapter.notifyDataSetChanged();
                } else {
                    MainSecondItem temp = new MainSecondItem("null", "giftcon"); // id에 null 입력
                    giftconArray.add(0, temp);
                    giftconAdapter.notifyDataSetChanged();
                }
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
