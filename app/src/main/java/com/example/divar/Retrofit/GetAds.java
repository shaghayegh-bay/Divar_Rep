package com.example.divar.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAds {

    @SerializedName("Title")
    @Expose
    private String Title;

    @SerializedName("Avatar")
    @Expose
    private String Avatar;

    @SerializedName("Price")
    @Expose
    private String Price;

    @SerializedName("Time")
    @Expose
    private String Time;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
