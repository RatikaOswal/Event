package com.ht.event.model;


import android.widget.TextView;

import java.io.Serializable;

public class User implements Serializable{

  private String Name;
    private String Email;

    public String getName() {
        return Name;
    }

    public TextView setName(String name) {
        Name = name;
        return null;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }



}
