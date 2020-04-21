package com.example.divar.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailsPojo {
    @SerializedName("ads")
    List<GetAds> newsModels;

    public List<GetAds> getAdsModels() {
        return newsModels;
    }

}
