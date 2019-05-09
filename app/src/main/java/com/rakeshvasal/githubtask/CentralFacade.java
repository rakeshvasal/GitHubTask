package com.rakeshvasal.githubtask;


public class CentralFacade {

    private static CentralFacade instance;

    public static CentralFacade getInstance() {
        if (instance != null) {
            return instance;
        } else {
            return instance = new CentralFacade();
        }
    }

    public void getToken(byte[] data, final Callbacks callbacks) {
        Repository repository = new Repository();
        repository.getToken(data, new Callbacks() {
            @Override
            public void onSuccess(Object response) {
                callbacks.onSuccess(response);
            }


            @Override
            public void onFailure() {
                callbacks.onFailure();
            }
        });
    }

    public void getAllUsersFromApi(String token,final Callbacks callbacks) {
        Repository repository = new Repository();
        repository.getAllUsersFromApi(new Callbacks() {
            @Override
            public void onSuccess(Object response) {
                callbacks.onSuccess(response);
            }

            @Override
            public void onFailure() {
                callbacks.onFailure();
            }
        },token);
    }

    public void getAllUsersFromApiSince(String token,final Callbacks callbacks,String since) {
        Repository repository = new Repository();
        repository.getAllUsersFromApiSince(new Callbacks() {
            @Override
            public void onSuccess(Object response) {
                callbacks.onSuccess(response);
            }

            @Override
            public void onFailure() {
                callbacks.onFailure();
            }
        },token,since);
    }
}
