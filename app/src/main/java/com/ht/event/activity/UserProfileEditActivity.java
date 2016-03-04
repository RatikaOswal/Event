package com.ht.event.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;


import com.ht.event.R;
import com.ht.event.model.User;
import com.ht.event.utils.EventsPreferences;

public class UserProfileEditActivity extends AppCompatActivity {

    EditText name,email,phoneNo,orgName,orgWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.profileEditToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (EditText)findViewById(R.id.Name);
        email = (EditText) findViewById(R.id.emailAddress);
        phoneNo = (EditText) findViewById(R.id.phoneNo);
        orgName = (EditText) findViewById(R.id.orgName);
        orgWebsite = (EditText)findViewById(R.id.orgWebsite);

        User user = EventsPreferences.getUser(this);
        if (user!=null){
            name.setText(user.getName());
            email.setText(user.getEmail());
            phoneNo.setText(user.getPhoneNo());
            orgName.setText(user.getOrganisation());
            orgWebsite.setText(user.getOrgWebsite());

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();

    }
        return true;
}}
