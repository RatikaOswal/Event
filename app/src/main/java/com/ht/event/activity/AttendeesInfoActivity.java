package com.ht.event.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ht.event.R;
import com.ht.event.model.User;
import com.ht.event.utils.EventsPreferences;

public class AttendeesInfoActivity extends AppCompatActivity {
    private EditText phoneNo, orgName, orgWebsite;
    private TextView register;
    private String PhoneNo,OrgName,OrgWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendees_info);


//setting the toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.attendeebar);
        setSupportActionBar(mToolbar);

//setting the icon
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        phoneNo = (EditText)findViewById(R.id.contactNumber);
        phoneNo.getText().toString();
        orgName = (EditText)findViewById(R.id.company);
        orgName.getText().toString();
        orgWebsite = (EditText)findViewById(R.id.companywebsite);
        orgWebsite.getText().toString();

        register = (TextView)findViewById(R.id.continuetext);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = EventsPreferences.getUser(AttendeesInfoActivity.this);
                user.setPhoneNo(PhoneNo);
                user.setOrgWebsite(OrgWebsite);
                user.setOrganisation(OrgName);
                EventsPreferences.saveUser(AttendeesInfoActivity.this, user);

                Intent intent = new Intent(AttendeesInfoActivity.this,OrderBreakdownActivity.class);
                startActivity(intent);
                AttendeesInfoActivity.this.finish();
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_attendees_info, menu);
        return true;
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
