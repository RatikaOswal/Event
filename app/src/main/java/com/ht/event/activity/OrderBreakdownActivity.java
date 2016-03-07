package com.ht.event.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ht.event.R;
import com.ht.event.model.Item;
import com.ht.event.model.User;
import com.ht.event.utils.Config;
import com.ht.event.utils.EventsPreferences;

public class OrderBreakdownActivity extends AppCompatActivity {

    public Item item;
    public TextView venue,time,price,name,email,OrgName,phoneNo, continuetext;

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

        item = (Item) getIntent().getSerializableExtra(Config.ITEM_INTENT_OBJECT);
//        System.out.println("krlrk" + getIntent().getStringExtra("title"));
        venue.setText(item.getTitle());
        time.setText(item.getTime());
        price.setText(item.getPrice());

        User user = EventsPreferences.getUser(this);
        name.setText(user.getName());
        email.setText(user.getEmail());
        phoneNo.setText(user.getPhoneNo());
        OrgName.setText(user.getOrganisation());


        continuetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderBreakdownActivity.this,OrderCompletedActivity.class);
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
