package com.ht.event.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ht.event.R;
import com.ht.event.adapter.ExploreItemListAdp;
import com.ht.event.adapter.SessionListAdapter;
import com.ht.event.model.Event;
import com.ht.event.model.EventList;
import com.ht.event.model.SessionList;
import com.ht.event.model.User;
import com.ht.event.utils.Config;
import com.ht.event.utils.EventsPreferences;

public class OrderBreakdownActivity extends AppCompatActivity {

    public Event event;
    public TextView venue,time,price,name,email,OrgName,phoneNo, continuetext;
    public SessionListAdapter sessionListAdapter;
    public RecyclerView mListView;
    public View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_breakdown);
        //setting the toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.orderbreakbar);
        setSupportActionBar(mToolbar);
        //setting the icon
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        venue = (TextView)findViewById(R.id.venueName);
        time = (TextView)findViewById(R.id.venuetime);
        price = (TextView)findViewById(R.id.Regprice);
        name = (TextView)findViewById(R.id.RegUserName);
        email = (TextView)findViewById(R.id.RegUserEmail);
        OrgName = (TextView)findViewById(R.id.RegUserOrg);
        phoneNo = (TextView)findViewById(R.id.RegUserPhone);
        continuetext = (TextView)findViewById(R.id.continuetext);

        event = (Event) getIntent().getSerializableExtra(Config.ITEM_INTENT_OBJECT);
        mListView = (RecyclerView) findViewById(R.id.session_list);
        System.out.println(event+"eventd");
        System.out.println(event.getSessions() + "eventd");
        LinearLayoutManager lm = new LinearLayoutManager(this);
        System.out.println(lm+ "eventss");
        sessionListAdapter = new SessionListAdapter(this,event.getSessions());
        System.out.println("ss" +
                "eventss");

        mListView.setLayoutManager(lm);

        mListView.setAdapter(sessionListAdapter);
        mListView.getAdapter().notifyDataSetChanged();


        venue.setText(event.getTitle());
        time.setText(event.getTime());
        price.setText(event.getPrice());

        User user = EventsPreferences.getUser(this);
        name.setText(user.getName());
        email.setText(user.getEmail());
        phoneNo.setText(user.getPhoneNo());
        OrgName.setText(user.getOrganisation());


        continuetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderBreakdownActivity.this,OrderCompletedActivity.class);
                intent.putExtra(Config.ITEM_INTENT_OBJECT, event);
                startActivity(intent);
            }
        });


    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();

        }

        return true;
    }


}
