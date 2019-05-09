package com.rakeshvasal.githubtask;

public interface Callbacks<T> {
    void onSuccess(T response);

    void onFailure();
}