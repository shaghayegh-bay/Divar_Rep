package com.example.divar.RecyclerView;


public class ModelRecycler {


    public String title, price, time, avatar;

    /*    public Item(int itemAvatar, String itemTitle, String itemPrice, String itemTime) {
            this.itemAvatar = itemAvatar;
            this.itemTitle = itemTitle;
            this.itemPrice = itemPrice;
            this.itemTime = itemTime;
        }*/

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
