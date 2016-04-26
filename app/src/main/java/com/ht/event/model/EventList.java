package com.ht.event.model;

import java.io.Serializable;
import java.util.ArrayList;


public class EventList implements Serializable {


    private ArrayList<Event> data;

    public ArrayList<Event> getData() {
        return data;
    }

    public void setData(ArrayList<Event> data) {
        this.data = data;
    }
}
