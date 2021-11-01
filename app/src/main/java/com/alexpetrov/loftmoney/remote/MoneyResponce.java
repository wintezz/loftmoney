package com.alexpetrov.loftmoney.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoneyResponce {

    private String status;
    @SerializedName("data")
    private List<MoneyRemoteItem> moneyItemList;

    public String getStatus() {
        return status;
    }

    public List getMoneyItemList() {
        return moneyItemList;
    }
}
