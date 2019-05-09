package com.rakeshvasal.githubtask;

import java.util.List;

import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("authorizations")
    Call<Object> getToken(@Body LoginModel json);

    @GET("users")
    Call<List<User>> getAllUsers();

    @GET("users?")
    Call<List<User>> getAllUsersSince(@Query("since") String since);
}
