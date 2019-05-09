package com.rakeshvasal.githubtask;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {


    public void getToken(byte[] data, final Callbacks callbacks) {
        LoginModel loginModel = Utils.getBodyForLogin();
        ApiInterface apiInterface = RetrofitClient.getDefaultInstanceForToken(data, ApiInterface.class);
        Call call = apiInterface.getToken(loginModel);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, Response<Response> response) {
                callbacks.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                callbacks.onFailure();
            }
        });
    }

    public void getAllUsersFromApi(final Callbacks callbacks,String token) {
        ApiInterface apiInterface = RetrofitClient.getInstanceForUsers(ApiInterface.class,token);
        Call<List<User>> call = apiInterface.getAllUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                callbacks.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callbacks.onFailure();
            }
        });
    }

    public void getAllUsersFromApiSince(final Callbacks callbacks,String token,String since) {
        ApiInterface apiInterface = RetrofitClient.getInstanceForUsers(ApiInterface.class,token);
        Call<List<User>> call = apiInterface.getAllUsersSince(since);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                callbacks.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callbacks.onFailure();
            }
        });
    }
}
