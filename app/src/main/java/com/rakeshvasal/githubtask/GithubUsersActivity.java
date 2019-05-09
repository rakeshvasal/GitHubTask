package com.rakeshvasal.githubtask;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class GithubUsersActivity extends AppCompatActivity implements UIView {

    Button getuserbyname, fetchmore;
    EditText searchname;
    TextView record_count;
    Presenter presenter;
    RecyclerView recyclerView;
    ArrayList<User> userList = new ArrayList<>();
    DisplayUsersAdapter displayUsersAdapter;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_users);
        getuserbyname = findViewById(R.id.getuserbyname);
        fetchmore = findViewById(R.id.fetchmore);
        searchname = findViewById(R.id.searchname);
        record_count = findViewById(R.id.count);
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        displayUsersAdapter = new DisplayUsersAdapter(this, userList);
        recyclerView.setAdapter(displayUsersAdapter);
        presenter = new PresenterModel(this);
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Utils.TOPIC_GLOBAL, 0);
        token = pref.getString("Token", "");
        presenter.getAllUsersFromRealmDb();

        getuserbyname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = searchname.getText().toString();
                presenter.getUserFromRealmDb(keyword);
            }
        });

        fetchmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int listSize = presenter.getCountOfUsersInDb();
                int id = presenter.getLastIdFromDb();
                if (listSize > 0) {
                    if (listSize < Utils.MAX_RESULTS_LIMIT) {
                        presenter.getAllUsersFromApiSince(token, "" + id);
                    }
                }
            }
        });

    }


    @Override
    public void onSuccess(Object response) {
        presenter.getAllUsersFromRealmDb();
    }

    @Override
    public void Failure() {

    }

    @Override
    public void getAllFilterResults(ArrayList arrayList) {

    }

    @Override
    public void getAllSearchResults(ArrayList arrayList) {
        userList.clear();
        userList.addAll(arrayList);
        if (arrayList.size() == 0) {
            presenter.getAllUsersFromApi(token);
        }
        displayUsersAdapter.notifyDataSetChanged();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
