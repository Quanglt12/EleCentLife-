package com.example.elecentlife;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FriendListActivity extends AppCompatActivity {

    private ArrayList<friend> arrayfriend = new ArrayList<>();
    private ListView lvfriend;
    private FriendListAdapter adapter;
    private Button calbtn;
    private Button btnadd,btndelete;
    private String userid; //user email
    private String usernickname;
    private FirebaseAuth auth;
    private int i;


    //conStrutor
    public void loadFriendlist() {
        try {
            File path = Environment.getExternalStorageDirectory();
            File file = new File(path, "friendsFile");
            File friendsFile = new File(file, "friendsFile.txt");
            //friendsFile.delete();
            //create the file if it doesn't exist
            if (!friendsFile.exists()) {
                file.mkdirs();
                friendsFile.createNewFile();

            }
            else {
                FileInputStream fis = new FileInputStream(friendsFile);
                ObjectInputStream oin = new ObjectInputStream(fis);

                //set FriendList to contents of the file
                arrayfriend = (ArrayList<friend>) oin.readObject();
                oin.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException c) {
            c.printStackTrace();
        }

    }




    private void saveFriends () {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "friendsFile");
            File friendsFile = new File(file, "friendsFile.txt");
            FileOutputStream fos = new FileOutputStream(friendsFile);
            ObjectOutputStream out = new ObjectOutputStream(fos);

            out.writeObject(arrayfriend);
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        decalare();
        loadFriendlist();

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

        btndelete.setOnClickListener(new View.OnClickListener() {
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
                saveFriends();

                return false;

            }
        });
    }

    private void decalare() {
        lvfriend = (ListView) findViewById(R.id.ListViewFriend);

        auth = FirebaseAuth.getInstance(); //get the Authentication instance from the database
        calbtn = (Button) findViewById(R.id.calbutton);
        btnadd = (Button) findViewById(R.id.addfriendbutton);
        btndelete = (Button) findViewById(R.id.removefriendbutton);


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
                               saveFriends();
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
