package com.example.elecentlife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FriendListActivity extends AppCompatActivity {

    ListView lvfriend;
    ArrayList<friend> arrayfriend;
    FriendListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        decalare();

        adapter = new FriendListAdapter(FriendListActivity.this,R.layout.friend_list,arrayfriend);
        lvfriend.setAdapter(adapter);
    }

    private void decalare() {
        lvfriend = (ListView) findViewById(R.id.ListViewFriend);
        arrayfriend = new ArrayList<>();

        arrayfriend.add(new friend("Quang","Euro"));
        arrayfriend.add(new friend("Nam","Dang"));
        arrayfriend.add(new friend("Hung","sinh"));
        arrayfriend.add(new friend("Devan","Griffin"));
    }
}
