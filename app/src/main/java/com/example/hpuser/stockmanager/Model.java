package com.example.hpuser.stockmanager;

/**
 * Created by HP USER on 07/06/2018.
 */

public class Model {
    private String ItemName;
    private String ItemId;
    private String date;
    private String ItemPrice;
    private String ItemQrt;
    private byte[] image;

    public Model(String itemName, String itemId, String date, String itemPrice, String itemQrt, byte[] image) {
       this.ItemName = itemName;
        this.ItemId = itemId;
        this.date = date;
       this.ItemPrice = itemPrice;
        this.ItemQrt = itemQrt;
        this.image = image;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        ItemPrice = itemPrice;
    }

    public String getItemQrt() {
        return ItemQrt;
    }

    public void setItemQrt(String itemQrt) {
        ItemQrt = itemQrt;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
