package com.example.divar.RecyclerView;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelRecycler {
    @SerializedName("Title")
    @Expose
    private String Title;

    @SerializedName("Avatar")
    @Expose
    private int Avatar;

    @SerializedName("Price")
    @Expose
    private String Price;

    @SerializedName("Time")
    @Expose
    private String Time;

        /*  public ModelRecycler(int itemAvatar, String itemTitle, String itemPrice, String itemTime) {
            this.Avatar = itemAvatar;
            this.Title = itemTitle;
            this.Price = itemPrice;
            this.Time = itemTime;
        }*/


    public String title, price, time, avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
