package com.ht.event.model;


import java.io.Serializable;
import java.util.ArrayList;

public class SessionList implements Serializable{

    private ArrayList<Session> session;

    public ArrayList<Session> getSession() {
        return session;
    }

    public void setSession(ArrayList<Session> session) {
        this.session = session;
    }
}
