package com.adlots.adlots.activity.MainActivity.MainSecondFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class MainSecondDeadline extends Fragment {

    private Context deadlineContext = null;
    private View deadlineView = null;

    ListView deadlineList;
    MainSecondListAdapter deadlineAdapter;
    public ArrayList<MainSecondItem> deadlineArray = new ArrayList<MainSecondItem>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        deadlineContext = container.getContext();
        deadlineView = (View) inflater.inflate(R.layout.fragment_main_second_deadline,
                container, false);

        RestClient.AdlotsService service = RestClient.getService();
        service.getItem("deadline", new Callback<List<MainSecondItem>>() {
            @Override
            public void success(List<MainSecondItem> getitem, Response response) {
                if(getitem!=null) {
                    deadlineArray.addAll(getitem);
                    deadlineAdapter.notifyDataSetChanged();
                } else {
                    MainSecondItem temp = new MainSecondItem("null", "delivery"); // id에 null 입력
                    deadlineArray.add(0, temp);
                    deadlineAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void failure(RetrofitError error) {

            }
        });

        deadlineList = (ListView) deadlineView.findViewById(R.id.deadline_listview);
        deadlineAdapter = new MainSecondListAdapter(deadlineContext, R.layout.content_main_second_item, deadlineArray);
        deadlineList.setAdapter(deadlineAdapter);
        deadlineAdapter.clear();

        return deadlineView;
    }
}
