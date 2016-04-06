package com.ht.event.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ht.event.adapter.listAdapter;

import com.ht.event.R;
import com.ht.event.model.Event;
import com.ht.event.utils.Config;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilterActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private TextView dates;
    private RadioGroup datesradiogroup;
    private TextView event_topics;
    private RelativeLayout checkbox_relativeLayout;
    private TextView event_types;
    private RelativeLayout event_types_relativelayout;
    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        //Setting toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.filtertoolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        event = (Event)getIntent().getSerializableExtra(Config.ITEM_INTENT_OBJECT);
        checkbox_relativeLayout =(RelativeLayout)findViewById(R.id.checkbox_relativelayout);
        datesradiogroup = (RadioGroup)findViewById(R.id.dates_radioGroup);
        event_topics =(TextView)findViewById(R.id.event_topics);
        event_topics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_relativeLayout.getVisibility() == View.VISIBLE) {
                    checkbox_relativeLayout.setVisibility(View.GONE);
                } else {
                    checkbox_relativeLayout.setVisibility(View.VISIBLE);
                }


            }
        });
        dates = (TextView)findViewById(R.id.dates);
        dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (datesradiogroup.getVisibility() == View.VISIBLE) {
                    datesradiogroup.setVisibility(View.GONE);

                } else {
                    datesradiogroup.setVisibility(View.VISIBLE);
                }


            }
        });
        event_types_relativelayout = (RelativeLayout)findViewById(R.id.checkbox_event_types_relativelayout);
        event_types =(TextView)findViewById(R.id.event_types);
        event_types.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (event_types_relativelayout.getVisibility() == View.VISIBLE) {
                    event_types_relativelayout.setVisibility(View.GONE);

                } else {
                    event_types_relativelayout.setVisibility(View.VISIBLE);
                }


            }
        });




        // preparing list data
        prepareListData();
        listAdapter = new listAdapter(this, listDataHeader, listDataChild);


    }



    /*no
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
        Dates.add(" title");
        /*Dates.add("Today");
        Dates.add("Tomorrow");
        Dates.add("Weekend");
        Dates.add("Week Days"); */

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
        EventTypes.add("Performance");

        listDataChild.put(listDataHeader.get(0), Dates); // Header, Child data
        listDataChild.put(listDataHeader.get(1), EventTopics);
        listDataChild.put(listDataHeader.get(2), EventTypes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        if(id==R.id.filterApply){
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra(Config.ITEM_INTENT_OBJECT,event);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}

