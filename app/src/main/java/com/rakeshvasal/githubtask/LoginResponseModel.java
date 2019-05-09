package com.rakeshvasal.githubtask;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponseModel implements Serializable {
    @SerializedName("token")
    String token;
    @SerializedName("id")
    int id;

    public String getToken() {
        return token;
    }

    public int getId() {
        return id;
    }
}
