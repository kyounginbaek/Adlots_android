package com.adlots.adlots.activity.MainActivity.MainSecondFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
public class MainSecondDeadline extends android.support.v4.app.Fragment {

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
        service.getItem("delivery", new Callback<List<MainSecondItem>>() {
            @Override
            public void success(List<MainSecondItem> getitem, Response response) {
                deadlineArray.addAll(getitem);
                deadlineAdapter.notifyDataSetChanged();
            }
            @Override
            public void failure(RetrofitError error) {

            }
        });

        deadlineList = (ListView) deadlineView.findViewById(R.id.deadline_listview);
        deadlineAdapter = new MainSecondListAdapter(deadlineContext, R.layout.content_main_second_item, deadlineArray);
        deadlineList.setAdapter(deadlineAdapter);
        deadlineAdapter.clear();

        deadlineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 클릭시 추첨 페이지 팝업되기
                LayoutInflater inflater = getActivity().getLayoutInflater(); //Dialog에서 보여줄 입력화면 View 객체 생성 작업
                final View dialogView = inflater.inflate(R.layout.popup_main_third_info, null); //Dialog의 listener에서 사용하기 위해 final로 참조변수 선언

                AlertDialog.Builder buider = new AlertDialog.Builder(getActivity()); //AlertDialog.Builder 객체 생성
                buider.setView(dialogView); //위에서 inflater가 만든 dialogView 객체 세팅
                buider.setTitle("얼만큼의 랏츠를 응모하시겠습니까?");

                AlertDialog dialog = buider.create(); //설정한 값으로 AlertDialog 객체 생성
                dialog.setCanceledOnTouchOutside(true); //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                dialog.show(); //Dialog 보이기
            }
        });

        return deadlineView ;
    }
}
