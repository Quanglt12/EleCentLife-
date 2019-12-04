package com.example.elecentlife;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class groupEvent {
    private ArrayList<friend> arrayFriends;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    public static boolean value = false;
    private boolean[] checks = {false, false, false, false, false};
    private int index;

    private class uploadClass {
        public String user;
        public String event;

        public uploadClass() {}

        public uploadClass (String user, String event) {
            this.user = user;
            this.event = event;
        }
    }

    public groupEvent() {
        try {
            File friendsFile = new File("/sdcard/friendsFile/friendsFile.txt");
            //friendsFile.delete();
            //create the file if it doesn't exist
            if (!friendsFile.exists()) {
                friendsFile.createNewFile();
            }
            else {
                FileInputStream fis = new FileInputStream(friendsFile);
                ObjectInputStream oin = new ObjectInputStream(fis);

                //set FriendList to contents of the file
                arrayFriends = (ArrayList<friend>) oin.readObject();
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

    public boolean[] getChecks() {
        return checks;
    }

    private String[] getNicknames() {
        friend tempFriend;
        String[] friendNicknames = new String[arrayFriends.size()];

        for (int i = 0; i < arrayFriends.size(); i++) {
            tempFriend = arrayFriends.get(i);
            friendNicknames[i] = tempFriend.getName();
        }

        return friendNicknames;
    }

    public String[] getFriendArray() {
        return getNicknames();
    }

    public void sendEvent(final String event) {
        for (int i = 0; i < arrayFriends.size(); i++) {
            if (checks[i]) {
                index = 0;
                do {
                    database.child("Tables").child("Group Events").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() == null) {
                                groupEvent.value = true;
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    index++;
                } while (value);

                friend friendInstance = arrayFriends.get(i);

                uploadClass uc = new uploadClass(friendInstance.getUserid(), event);
                database.child("Tables").child("Group Events").child(String.valueOf(index)).setValue(uc);

                value = false;
            }
        }
    }
}
