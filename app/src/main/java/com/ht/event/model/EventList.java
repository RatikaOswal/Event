package com.ht.event.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by RATIKA on 17-Feb-16.
 */
public class EventList implements Serializable {


    private ArrayList<Event> data;

    public ArrayList<Event> getData() {
        return data;
    }

    public void setData(ArrayList<Event> data) {
        this.data = data;
    }
}
