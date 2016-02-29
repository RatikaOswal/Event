package com.ht.event.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by RATIKA on 17-Feb-16.
 */
public class ItemList implements Serializable {


    private ArrayList<Item> data;

    public ArrayList<com.ht.event.model.Item> getData() {
        return data;
    }

    public void setData(ArrayList<com.ht.event.model.Item> data) {
        this.data = data;
    }
}
