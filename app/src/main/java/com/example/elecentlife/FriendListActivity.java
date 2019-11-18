package com.example.elecentlife;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.ArrayList;
import java.util.List;

public class FriendListActivity extends AppCompatActivity {

    ListView lvfriend;
    ArrayList<friend> arrayfriend;
    FriendListAdapter adapter;
    Button calbtn;
    Button btnadd,btndetele;
    String userid; //user email
    String usernickname;
    FirebaseAuth auth;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        decalare();
        //set button go back to calendar

        calbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);
            }
        });

        //set button setting go to setting activity
        Button settingsButton = (Button) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(startIntent);
            }
        });

        //set button new event go to event activity
        Button eventButton = (Button) findViewById(R.id.newEventButton);
        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), NewEventActivity.class);
                startActivity(startIntent);
            }
        });

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

                        //pass user email to validate user method
                        validateuser(userid, usernickname);
                        dialog.cancel();

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
                //I will add the question asking user sure about delete this user friend later

                arrayfriend.remove(i);
                adapter.notifyDataSetChanged();

                return false;

            }
        });
    }

    private void decalare() {
        lvfriend = (ListView) findViewById(R.id.ListViewFriend);
        arrayfriend = new ArrayList<>();

        auth = FirebaseAuth.getInstance(); //get the Authentication instance from the database
        calbtn = (Button) findViewById(R.id.calbutton);
        btnadd = (Button) findViewById(R.id.addfriendbutton);
        btndetele = (Button) findViewById(R.id.removefriendbutton);


    }

private void validateuser(final String newuserEmail, final String nickName)
    {
       auth.fetchSignInMethodsForEmail(newuserEmail)
               .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                   @Override
                   public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                       if (task.isSuccessful())
                       {
                           SignInMethodQueryResult result = task.getResult();
                           List<String> signInMethods = result.getSignInMethods();
                           if(!signInMethods.isEmpty()) //if the signInmethod is empty, it means that the Email doesn't exist in the data
                           {
                               arrayfriend.add(new friend(nickName, newuserEmail));
                               adapter.notifyDataSetChanged(); //set new data after changed
                           }
                           else
                           {
                               Toast toast = Toast.makeText(getApplicationContext(),"Incorrect User email",Toast.LENGTH_SHORT);
                               toast.show();
                           }
                       }

                   }
               });



    };
}
