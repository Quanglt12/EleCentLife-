package com.example.elecentlife;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class FriendListActivity extends AppCompatActivity {

    ListView lvfriend;
    ArrayList<friend> arrayfriend;
    FriendListAdapter adapter;
    Button btnadd,btndetele;
    String userid;
    String usernickname;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        decalare();

        adapter = new FriendListAdapter(FriendListActivity.this, R.layout.friend_list, arrayfriend);
        lvfriend.setAdapter(adapter);

        //set the add friend btn

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create dialog for add new friend
                final Dialog dialog = new Dialog(FriendListActivity.this);
                dialog.setCancelable(false); // you can not cancel by click outside of the dialog
                dialog.setContentView(R.layout.dialogfriend);

                //now get the information in the dialog to add new friend
                final EditText edtfriendname = (EditText) dialog.findViewById(R.id.edtNewFriend);
                final EditText edtNickName = (EditText) dialog.findViewById(R.id.edtNickName);
                Button cancel = (Button) dialog.findViewById(R.id.cancelDialogFriend);
                Button add = (Button) dialog.findViewById(R.id.addFriendDialogbtn);

                //initialize add button in dialog friend
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userid = edtfriendname.getText().toString().trim();
                        usernickname = edtNickName.getText().toString().trim();
                        arrayfriend.add(new friend(usernickname, userid));
                        adapter.notifyDataSetChanged(); //set new data after changed
                        dialog.cancel();
                       // validateuser(userid,usernickname);
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });

        btndetele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),"long click on the user name you want to remove",Toast.LENGTH_LONG);
                toast.show();
            }
        });

        lvfriend.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {


                arrayfriend.remove(i);
                adapter.notifyDataSetChanged();

                return false;
            }
        });
    }

    private void decalare() {
        lvfriend = (ListView) findViewById(R.id.ListViewFriend);
        arrayfriend = new ArrayList<>();

        btnadd = (Button) findViewById(R.id.addfriendbutton);
        btndetele = (Button) findViewById(R.id.removefriendbutton);
        arrayfriend.add(new friend("Quang", "Euro"));
        arrayfriend.add(new friend("Nam", "Dang"));
        arrayfriend.add(new friend("Hung", "sinh"));
        arrayfriend.add(new friend("Devan", "Griffin"));
    }

 /*private void validateuser(String newuserid, String nickName)
    {
        //create class login as a static of Login
        Login login = new Login();
        String value = login.getusername();

        if(newuserid.equals(value))
        {
            arrayfriend.add(new friend(nickName, newuserid));
            adapter.notifyDataSetChanged(); //set new data after changed
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),"Incorrect User id name",Toast.LENGTH_SHORT);
            toast.show();
        }

    }*/
}
