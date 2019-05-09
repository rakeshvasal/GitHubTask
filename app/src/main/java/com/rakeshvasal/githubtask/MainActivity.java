package com.rakeshvasal.githubtask;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements UIView {


    TextView submit;
    EditText username, password;
    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!RealmConfigurations.realmExists()) {
            RealmConfigurations.initializeRealm(this);
        }

        submit = findViewById(R.id.submit);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        presenter = new PresenterModel(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString();
                String pass = password.getText().toString();

                presenter.perFormLogin(uname, pass);
            }
        });

    }

    @Override
    public void onSuccess(Object response) {
        Gson gson = new Gson();
        String json = gson.toJson(response);
        LoginResponseModel loginResponseModel = gson.fromJson(json, LoginResponseModel.class);
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Utils.TOPIC_GLOBAL, 0);
        pref.edit().putString("Token", loginResponseModel.getToken()).apply();
        Intent intent = new Intent(this, GithubUsersActivity.class);
        startActivity(intent);
    }

    @Override
    public void Failure() {

    }

    @Override
    public void getAllFilterResults(ArrayList arrayList) {

    }

    @Override
    public void getAllSearchResults(ArrayList arrayList) {

    }

}
