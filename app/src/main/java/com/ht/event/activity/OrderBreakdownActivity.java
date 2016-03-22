package com.ht.event.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ht.event.R;
import com.ht.event.adapter.SessionListAdapter;
import com.ht.event.model.Event;
import com.ht.event.model.User;
import com.ht.event.utils.Config;
import com.ht.event.utils.EventsPreferences;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

public class OrderBreakdownActivity extends AppCompatActivity {

    private Event event;
    private TextView venue,time,price,name,email,OrgName,phoneNo, continuetext;
    private SessionListAdapter sessionListAdapter;
    private RecyclerView mListView;




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

        LinearLayoutManager lm = new LinearLayoutManager(this);

        int recyclerViewHeight = event.getSessions().size() * 137 ;
        DisplayMetrics d = getResources().getDisplayMetrics();
        int itemWidth = d.widthPixels;
        System.out.println(itemWidth+"itemWidth");
        LinearLayout.LayoutParams params = new
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.height=recyclerViewHeight;
        mListView.setLayoutParams(params);
        sessionListAdapter = new SessionListAdapter(this,event.getSessions());

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

                String uniqueID = UUID.randomUUID().toString();
                event.setTicketId(uniqueID);
                EventsPreferences.saveRegistered(OrderBreakdownActivity.this, event);
                Toast.makeText(OrderBreakdownActivity.this, "Registration Completed", Toast.LENGTH_SHORT).show();
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
