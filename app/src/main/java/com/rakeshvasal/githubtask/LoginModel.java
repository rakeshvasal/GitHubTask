package com.rakeshvasal.githubtask;

public class LoginModel {
    private String note;

    private String[] scopes;

    private String client_secret;

    private String client_id;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String[] getScopes() {
        return scopes;
    }

    public void setScopes(String[] scopes) {
        this.scopes = scopes;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    @Override
    public String toString() {
        return "ClassPojo [note = " + note + ", scopes = " + scopes + ", client_secret = " + client_secret + ", client_id = " + client_id + "]";
    }
}
