package com.ht.event.activity;


import android.app.Activity;
import android.app.ExpandableListActivity;
import android.app.usage.UsageEvents;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.event.eventapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        //Setting toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.filtertoolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

          /* Initialize Radio Group and attach click handler */
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioGroup.clearCheck();

        /* Attach CheckedChangeListener to radio group */
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if(null!=rb && checkedId > -1){
                    Toast.makeText(FilterActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });



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


    public void onClear(View v) {
        /* Clears all selected radio buttons to default */
        radioGroup.clearCheck();
    }

    public void onSubmit(View v) {
        RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        Toast.makeText(FilterActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    }

