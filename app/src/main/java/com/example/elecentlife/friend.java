package com.example.elecentlife;

public class friend {
    private String Name;
    private String userid;

    public friend(String name, String userid) {
        Name = name;

        this.userid = userid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
