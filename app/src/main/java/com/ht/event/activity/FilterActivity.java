package com.ht.event.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.event.eventapp.R;
import com.ht.event.adapter.listAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilterActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        //Setting toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.filtertoolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expandablelist);


        // preparing list data
        prepareListData();
        listAdapter = new listAdapter(this, listDataHeader, listDataChild);


        // setting list adapter
        expListView.setAdapter(listAdapter);
    }



    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Dates");
        listDataHeader.add("Event Topics");
        listDataHeader.add("Event Types");

        // Adding child data
        List<String> Dates = new ArrayList<String>();
        Dates.add("All dates");
        Dates.add("Today");
        Dates.add("Tomorrow");
        Dates.add("Weekend");
        Dates.add("Week Days");

        List<String> EventTopics = new ArrayList<String>();


        EventTopics.add("Music");
        EventTopics.add("Arts");
        EventTopics.add("Buisness");
        EventTopics.add("Health");
        EventTopics.add("Hobbies");

        List<String> EventTypes = new ArrayList<String>();
        EventTypes.add("Conference");
        EventTypes.add("Seminar");
        EventTypes.add("Expo");
        EventTypes.add("Festival");
        EventTypes.add("Perforrmance");

        listDataChild.put(listDataHeader.get(0), Dates); // Header, Child data
        listDataChild.put(listDataHeader.get(1), EventTopics);
        listDataChild.put(listDataHeader.get(2), EventTypes);
    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }}
