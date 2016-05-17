package com.adlots.android.activity.MainActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import com.adlots.android.R;
import com.adlots.android.rest.RestClient;
import com.adlots.android.rest.model.MainFourthWinner;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by baekkyoungin on 16. 5. 14..
 */
public class MainFourthWinnerActivity extends FragmentActivity {

    ListView winnerList;
    MainFourthWinnerAdapter winnerAdapter;
    public ArrayList<MainFourthWinner> winnerArray = new ArrayList<MainFourthWinner>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_main_fourth_winnerlist);

        RestClient.AdlotsService service = RestClient.getService();
        service.getwinnerList(new Callback<List<MainFourthWinner>>() {
            @Override
            public void success(List<MainFourthWinner> getitem, Response response) {
                if (getitem != null) {
                    winnerArray.addAll(getitem);
                    winnerAdapter.notifyDataSetChanged();
                } else {
                    MainFourthWinner temp = new MainFourthWinner("null"); // id에 null 입력
                    winnerArray.add(0, temp);
                    winnerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });

        winnerList = (ListView) findViewById(R.id.winnerlist_listview);
        winnerAdapter = new MainFourthWinnerAdapter(getApplicationContext(), R.layout.content_main_fourth_winnerlist, winnerArray);
        winnerList.setAdapter(winnerAdapter);
        winnerAdapter.clear();

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}