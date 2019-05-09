package com.rakeshvasal.githubtask;

public interface Presenter {

    void perFormLogin(String username,String password);

    void getAllUsersFromApi(String token);

    void getAllUsersFromRealmDb();

    void getUserFromRealmDb(String keyword);

    void getAllUsersFromApiSince(String token,String since);

    int getCountOfUsersInDb();

    int getLastIdFromDb();
}
