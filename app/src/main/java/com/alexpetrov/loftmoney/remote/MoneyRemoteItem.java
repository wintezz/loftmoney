package com.alexpetrov.loftmoney.remote;

import com.google.gson.annotations.SerializedName;

public class MoneyRemoteItem {

    @SerializedName("id")
    private String itemId;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private Double price;

    @SerializedName("type")
    private String type;

    @SerializedName("date")
    private String date;

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}
