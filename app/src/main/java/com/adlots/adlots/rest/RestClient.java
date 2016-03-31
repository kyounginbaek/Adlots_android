package com.adlots.adlots.rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.adlots.adlots.rest.model.MainSecond;

import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Query;

/**
 * Created by baekkyoungin on 16. 3. 31..
 */
public class RestClient {

    private static SsmService mService;

    public static SsmService getService() {
        if (mService != null)
            return mService;

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        RestAdapter adapter = new RestAdapter.Builder().
                setEndpoint("http://ezbrother.com").
                setConverter(new GsonConverter(gson)).
                build();

        mService = adapter.create(SsmService.class);
        return mService;
    }

    public interface SsmService {
        //업데이트 버젼 정보 가져오기
        @GET("/version.php")
        void getVersion(Callback<String> callback);

        /* 현재 글감 가자오기
        @GET("/cards.php")
        void getKeyword(@Query("purpose") String purpose, Callback<Card> callback); */

        /* 현재 글감 가자오기
        @GET("/cards.php")
        void getMyssmword(@Query("purpose") String purpose, @Query("myssm_word") String myssm_word, Callback<Card> callback); */

        //내가 쓴 씀만 가져오기
        @GET("/ssm.php")
        void getMySsm(@Query("purpose") String purpose, @Query("author") String author, @Query("email") String email, Callback<List<MainSecond>> callback);

        /* 공개 씀 리스트 카드 정보 가져오기
        @GET("/cards.php")
        void getOpenCard(@Query("purpose") String purpose, Callback<List<Card>> callback); */

        //공개 씀 해당 글감의 씀 가져오기
        @GET("/ssm.php")
        void getOpenSsm(@Query("purpose") String purpose, @Query("word") String word, Callback<List<MainSecond>> callback);

        //새 글 작성
        @POST("/ssm.php")
        void postSsm(@Body Map<String, String> ssm, Callback<MainSecond> callback);

        //글 수정
        @PUT("/ssm.php")
        void putSsm(@Body Map<String, String> ssm, @Query("purpose") String purpose, Callback<MainSecond> callback);

        //글 공개하기
        @PUT("/ssm.php")
        void putSsmopen(@Body Map<String, String> ssm, @Query("purpose") String purpose, Callback<JsonElement> callback);

        //글 공개 취소하기
        @PUT("/ssm.php")
        void putSsmclose(@Body Map<String, String> ssm, @Query("purpose") String purpose, Callback<JsonElement> callback);

        //글 삭제
        @DELETE("/ssm.php")
        void deleteSsm(@Query("id") String id, @Query("word") String word, Callback<JsonElement> callback);

        //Writtne 글 삭제
        @DELETE("/ssm.php")
        void deleteWrittenSsm(@Query("content") String content, @Query("word") String word, Callback<JsonElement> callback);

        //회원 정보 수정
        @PUT("")
        void putProfile();

        //회원 등록
        @POST("")
        void postProfile();

        //회원 탈퇴
        @DELETE("")
        void deleteProfile(@Query("id") String id, Callback<JsonElement> callback);


        @POST("/cards.php")
        void postCard(@Body Map<String, String> card, Callback<JsonElement> callback);


    }

}
