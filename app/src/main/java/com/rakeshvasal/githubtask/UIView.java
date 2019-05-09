package com.rakeshvasal.githubtask;

import java.util.ArrayList;

public interface UIView<T> {

    void onSuccess(T response);

    void Failure();

    void getAllSearchResults(ArrayList<User> userArrayList);

    void getAllFilterResults(ArrayList<User> userArrayList);

}
