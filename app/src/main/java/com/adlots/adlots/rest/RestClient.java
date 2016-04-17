package com.adlots.adlots.rest;

import com.adlots.adlots.rest.model.MainSecondItem;
import com.adlots.adlots.rest.model.MainThirdItem;
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
import retrofit.http.PUT;
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

        //회원가입 화면
        @POST("/signup.php")
        void signup(@Body Map<String, String> signup, Callback<JsonElement> callback);

        //로그인 화면
        @POST("/signup.php")
        void signin(@Body Map<String, String> signin, Callback<JsonElement> callback);

        //아이템 정보 가져오기
        @GET("/getitem.php")
        void getItem(@Query("purpose") String purpose, Callback<List<MainSecondItem>> callback);

        //유저아이템 정보 가져오기
        @GET("/getuseritem.php")
        void getuserItem(@Query("purpose") String purpose, Callback<List<MainThirdItem>> callback);

        //아이템 추첨시 정보 입력


        //유저 개인정보 변경
        @PUT("/userinfochange.php")
        void userInfochange(@Body Map<String, String> useinfochange, @Query("email") String email, @Query("password") String password, Callback<JsonElement> callback);
    }

}
