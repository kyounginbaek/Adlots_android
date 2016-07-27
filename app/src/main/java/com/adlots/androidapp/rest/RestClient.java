package com.adlots.androidapp.rest;

import com.adlots.androidapp.rest.model.MainFourthWinner;
import com.adlots.androidapp.rest.model.MainSecondItem;
import com.adlots.androidapp.rest.model.MainThirdItem;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by baekkyoungin on 16. 3. 31..
 */
public class RestClient {

    private static AdlotsService mService;

    public static AdlotsService getService() {
        if (mService != null)
            return mService;

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        RestAdapter adapter = new RestAdapter.Builder().
                setEndpoint("http://adlots.co.kr/android_php").
                setConverter(new GsonConverter(gson)).
                build();

        mService = adapter.create(AdlotsService.class);
        return mService;
    }

    public interface AdlotsService {
        //현재 버전과 마켓 버전 비교
        @GET("/getVersion.php")
        void getVersion(Callback<JsonElement> callback);

        //회원가입 화면
        @POST("/signup.php")
        void signup(@Body Map<String, String> signup, Callback<JsonElement> callback);

        //로그인 화면
        @POST("/signin.php")
        void signin(@Body Map<String, String> signin, Callback<JsonElement> callback);

        //아이템 정보 가져오기
        @GET("/getitemList.php")
        void getitemList(@Query("purpose") String purpose, Callback<List<MainSecondItem>> callback);

        //특정 아이템 1개 정보만 가져오기
        @POST("/getoneItem.php")
        void getoneItem(@Body Map<String, String> getoneItem, Callback<JsonElement> callback); //MainSecondItem 공유

        //유저아이템 정보 가져오기
        @POST("/getuserItem.php")
        void getuserItem(@Query("purpose") String purpose, @Body Map<String, String> getuserItem, Callback<List<MainThirdItem>> callback);

        //유저포인트 정보 가져오기
        @POST("/getuserPoint.php")
        void getuserPoint(@Body Map<String, String> getuserPoint, Callback<JsonElement> callback);

        //유저주소 정보 가져오기
        @POST("/getuserAddress.php")
        void getuserAddress(@Body Map<String, String> getuserAddress, Callback<JsonElement> callback);

        //환불 금액 받기
        @POST("/getuserRefund.php")
        void getuserRefund(@Query("purpose") String purpose, @Body Map<String, String> getuserRefund, Callback<JsonElement> callback);

        //아이템 응모하기(방법1.응모-lots, 방법2.바로구입-buy)
        @POST("/itemhowtoBuy.php")
        void itemhowtoBuy(@Query("purpose") String purpose, @Body Map<String, String> itemhowtoBuy, Callback<JsonElement> callback);

        //유저 개인정보 변경
        @POST("/userinfoChange.php")
        void userinfoChange(@Query("purpose") String purpose, @Body Map<String, String> useinfoChange, Callback<JsonElement> callback);

        //당첨자 리스트 가져오기
        @GET("/getwinnerList.php")
        void getwinnerList(Callback<List<MainFourthWinner>> callback);

        //Tnk 포인트 정보 CRUD
        @POST("/tnkad.php")
        void tnkad(@Body Map<String, String> tnkad, Callback<JsonElement> callback);

        //추천인 정보 가져오기
        @POST("/recommend.php")
        void recommend(@Query("purpose") String purpose, @Body Map<String, String> recommend, Callback<JsonElement> callback);

        //최근 로그인 정보를 가져와서 현재 datetime이랑 하루 차이가 날 경우 20포인트 지급
        @POST("/latest_signin.php")
        void latest_signin(@Body Map<String, String> latest_signin, Callback<JsonElement> callback);
    }

}
