package com.rakeshvasal.githubtask;

import android.content.Context;

import java.io.File;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmConfigurations {

    public static Realm instance;

    public static void initializeRealm(Context context) {
        Realm.init(context);
        Realm.setDefaultConfiguration(setRealmConfiguration());
    }

    public static RealmConfiguration setRealmConfiguration() {
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded().build();
        return configuration;
    }

    public static Realm getInstance() {
        if (instance == null) {
            instance = Realm.getDefaultInstance();
        }
        return instance;
    }

    public static boolean realmExists() {
        try {
            RealmConfiguration configurations = getInstance().getConfiguration();
            new File(configurations.getPath());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
