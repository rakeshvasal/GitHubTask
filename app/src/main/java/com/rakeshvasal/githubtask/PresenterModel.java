package com.rakeshvasal.githubtask;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class PresenterModel implements Presenter {

    UIView uiView;

    public PresenterModel(UIView uiView) {
        this.uiView = uiView;
    }

    @Override
    public void perFormLogin(final String username, final String password) {
        final byte[] basictoken = (username + ":" + password).getBytes();
        CentralFacade.getInstance().getToken(basictoken, new Callbacks() {
            @Override
            public void onSuccess(Object response) {
                uiView.onSuccess(response);
            }

            @Override
            public void onFailure() {

            }
        });
    }

    @Override
    public void getAllUsersFromApi(final String token) {
        CentralFacade.getInstance().getAllUsersFromApi(token, new Callbacks() {
            @Override
            public void onSuccess(final Object response) {
                final List<User> userList = (List<User>) response;

                Log.d("UserList", "" + userList);
                Realm realm = RealmConfigurations.getInstance();
                try {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.insertOrUpdate(userList);
                            uiView.onSuccess(response);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //realm.close();
                }


            }

            @Override
            public void onFailure() {

            }
        });
    }

    @Override
    public void getAllUsersFromRealmDb() {
        final ArrayList<User> users = new ArrayList<>();
        Realm realm = RealmConfigurations.getInstance();
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<User> results = realm.where(User.class).findAll();
                    Log.d("UserList", "" + results);
                    users.addAll(results);
                    uiView.getAllSearchResults(users);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //realm.close();
        }
    }

    @Override
    public void getUserFromRealmDb(final String keyword) {
        final ArrayList<User> users = new ArrayList<>();
        Realm realm = RealmConfigurations.getInstance();
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<User> results = realm.where(User.class).contains("login", keyword).findAll();
                    Log.d("UserList", "" + results);
                    users.addAll(results);
                    uiView.getAllSearchResults(users);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //realm.close();
        }
    }

    @Override
    public void getAllUsersFromApiSince(String token, String since) {
        CentralFacade.getInstance().getAllUsersFromApiSince(token, new Callbacks() {
            @Override
            public void onSuccess(Object response) {
                final List<User> userList = (List<User>) response;

                Log.d("UserList", "" + userList);
                Realm realm = RealmConfigurations.getInstance();
                try {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.insertOrUpdate(userList);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //realm.close();
                }
                uiView.onSuccess(response);

            }

            @Override
            public void onFailure() {

            }
        }, since);
    }

    @Override
    public int getCountOfUsersInDb() {
        final ArrayList<User> users = new ArrayList<>();
        Realm realm = RealmConfigurations.getInstance();
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<User> results = realm.where(User.class).findAll();
                    Log.d("UserList", "" + results);
                    users.addAll(results);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //realm.close();
        }
        return users.size();
    }

    @Override
    public int getLastIdFromDb() {
        final ArrayList<User> users = new ArrayList<>();
        Realm realm = RealmConfigurations.getInstance();
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<User> results = realm.where(User.class).findAll();
                    Log.d("UserList", "" + results);
                    users.addAll(results);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //realm.close();
        }
        return users.get(users.size() - 1).id;
    }


}
