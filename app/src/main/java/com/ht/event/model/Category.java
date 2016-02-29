package com.ht.event.model;

import java.util.ArrayList;

/**
 * Created by komal on 18-02-2016.
 */
public class Category

{
        public String category_name = null;
        public ArrayList<Category> subcategory_array = new ArrayList<Category>();
    public boolean selected;
}

