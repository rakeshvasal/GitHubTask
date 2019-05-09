package com.rakeshvasal.githubtask;

public class Utils {

    public static String CLIENT_ID = "c831b11fc53fa3952be4";
    public static String CLIENT_SECRET_KEY = "f491d45a4f1a4d32e075ee885cc4720f4c1983b0";
    public static int MAX_RESULTS_LIMIT = 200;
    public static final String TOPIC_GLOBAL = "global";

    public static LoginModel getBodyForLogin() {
        LoginModel loginModel = new LoginModel();
        loginModel.setClient_id(CLIENT_ID);
        loginModel.setClient_secret(CLIENT_SECRET_KEY);
        loginModel.setNote("admin script");
        loginModel.setScopes(new String[]{"public_repo"});
        return loginModel;

    }
}
