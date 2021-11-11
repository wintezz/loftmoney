package com.alexpetrov.loftmoney.remote;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ItemsResponce {

    @SerializedName("status")
    String status;

    @SerializedName("data")
    ArrayList<RemoteItem> remoteItems;

    public String getStatus() {
        return status;
    }

    public ArrayList<RemoteItem> getRemoteItems() {
        return remoteItems;
    }
}
