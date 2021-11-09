package com.alexpetrov.loftmoney.remote;

import com.google.gson.annotations.SerializedName;

public class AuthResponce {

    @SerializedName("status")
    private String status;

    @SerializedName("id")
    private String id;

    @SerializedName("auth_token")
    private String authToken;

    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public String getAuthToken() {
        return authToken;
    }
}


