package com.example.elecentlife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class FriendListAdapter extends BaseAdapter {

    private Context context;
    private int Layout;
    private List<friend> friendList;

    public FriendListAdapter(Context context, int layout, List<friend> friendList) {
        this.context = context;
        Layout = layout;
        this.friendList = friendList;
    }

    @Override
    public int getCount() {
        return friendList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(Layout,null);

        //decalre view
        TextView txtName = (TextView) view.findViewById(R.id.Name);
        TextView txtUserid = (TextView) view.findViewById(R.id.userid);

        //call class friend
        friend friend  = friendList.get(i); //set object friend for friendlist position i

        //set value for friend attributes
        txtName.setText(friend.getName());
        txtUserid.setText(friend.getUserid());

        //return the view for every list view
        return view;
    }
}
