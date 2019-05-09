package com.rakeshvasal.githubtask;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayUsersAdapter extends RecyclerView.Adapter<DisplayUsersAdapter.MyViewHolder> {

    Activity activity;
    ArrayList<User> userArrayList;


    public DisplayUsersAdapter(Activity activity, ArrayList<User> userArrayList) {
        this.activity = activity;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = null;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list_item, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (userArrayList != null && userArrayList.size() > 0) {
            User user = userArrayList.get(i);
            myViewHolder.name.setText(user.id + " : " + user.login);
            myViewHolder.url.setText(user.html_url);
        }
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (userArrayList != null) {
            size = userArrayList.size();
        }
        return size;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, url;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            url = itemView.findViewById(R.id.url);
        }
    }
}

