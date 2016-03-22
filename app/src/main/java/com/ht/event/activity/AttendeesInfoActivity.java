package com.ht.event.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ht.event.R;
import com.ht.event.model.Event;
import com.ht.event.model.User;
import com.ht.event.utils.Config;
import com.ht.event.utils.EventsPreferences;

public class AttendeesInfoActivity extends AppCompatActivity {
    private EditText phoneNo, orgName, orgWebsite;
    private TextView register, userName, email;
    private String PhoneNo,OrgName,OrgWebsite;
    private Event eventObjects;
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
        if(getIntent().hasExtra(Config.ITEM_INTENT_OBJECT)) {
            eventObjects = (Event) getIntent().getSerializableExtra(Config.ITEM_INTENT_OBJECT);
        }
        userName = (TextView)findViewById(R.id.getuserName);
        email = (TextView)findViewById(R.id.attendeeEmail);
        phoneNo = (EditText)findViewById(R.id.contactNumber);
        orgWebsite = (EditText)findViewById(R.id.companywebsite);
        orgName = (EditText)findViewById(R.id.company);
        register = (TextView)findViewById(R.id.continuetext);

        final User user = EventsPreferences.getUser(AttendeesInfoActivity.this);
        userName.setText(user.getName());
        email.setText(user.getEmail());


        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                PhoneNo = phoneNo.getText().toString();
                int lng = phoneNo.getText().length();
                if(lng != 10)
                {
                    Toast.makeText(AttendeesInfoActivity.this, "Invalid Number", Toast.LENGTH_SHORT).show();

                }
                OrgName = orgName.getText().toString();
                OrgWebsite = orgWebsite.getText().toString();

                user.setPhoneNo(PhoneNo);
                user.setOrgWebsite(OrgWebsite);
                user.setOrganisation(OrgName);

                EventsPreferences.saveUser(AttendeesInfoActivity.this, user);
                Intent intent = new Intent(AttendeesInfoActivity.this,OrderBreakdownActivity.class);
                intent.putExtra(Config.ITEM_INTENT_OBJECT, eventObjects);
                startActivity(intent);
                AttendeesInfoActivity.this.finish();
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
