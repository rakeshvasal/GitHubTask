package com.rakeshvasal.githubtask;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://api.github.com/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;
    private static OkHttpClient.Builder lognInHttpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());


    // provides an instance fo retrofit
    public static Retrofit getRetrofit() {
        return builder.build();
    }

    public static <S> S getDefaultInstanceForToken(final byte[] data, Class<S> serviceClass) {
        lognInHttpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "Basic " + Base64.encodeToString(data, Base64.NO_WRAP))
                        .header("Content-Type", "application/json")
                        .method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = builder.client(lognInHttpClient.build()).build();
        return retrofit.create(serviceClass);
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public static <S> S getInstanceForUsers(Class<S> serviceClass, final String token) {
        lognInHttpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "token " + token)
                        .header("Content-Type", "application/json")
                        .method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = builder.client(lognInHttpClient.build()).build();
        return retrofit.create(serviceClass);
    }

}