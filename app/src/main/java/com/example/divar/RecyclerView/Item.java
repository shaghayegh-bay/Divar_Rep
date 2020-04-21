package com.example.divar.RecyclerView;


    public class Item {

        public int itemAvatar;
        public String itemTitle;
        public String itemPrice;
        public String itemTime;

    /*    public Item(int itemAvatar, String itemTitle, String itemPrice, String itemTime) {
            this.itemAvatar = itemAvatar;
            this.itemTitle = itemTitle;
            this.itemPrice = itemPrice;
            this.itemTime = itemTime;
        }*/

        public int getItemAvatar() {
            return itemAvatar;
        }

        public void setItemAvatar(int itemAvatar) {
            this.itemAvatar = itemAvatar;
        }

        public String getItemTitle() {
            return itemTitle;
        }

        public void setItemTitle(String itemTitle) {
            this.itemTitle = itemTitle;
        }

        public String getItemPrice() {
            return itemPrice;
        }

        public void setItemPrice(String itemPrice) {
            this.itemPrice = itemPrice;
        }

        public String getItemTime() {
            return itemTime;
        }

        public void setItemTime(String itemTime) {
            this.itemTime = itemTime;
        }
    }
