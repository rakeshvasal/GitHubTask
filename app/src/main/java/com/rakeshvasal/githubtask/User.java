package com.rakeshvasal.githubtask;

import io.realm.RealmObject;

public class User extends RealmObject {
    public int id;
    public String avatar_url;
    public String html_url;
    public String login;
}
