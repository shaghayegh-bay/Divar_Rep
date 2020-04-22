package com.example.divar.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    String BASE_URL = "http://shaghayegh.bay.tps-co.ir/";
    @GET("/get_data.php")
    Call<List<GetAds>> getAdsModels();
}
