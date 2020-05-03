package com.example.divar.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    String BASE_URL = "http://shaghayegh.bay.tps-co.ir/";

    @GET("get_data.php")
    Call<String> getAdsModels();
}
