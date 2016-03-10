package com.ht.event.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.ht.event.R;
import com.ht.event.model.User;
import com.ht.event.utils.EventsPreferences;

public class UserProfileEditActivity extends AppCompatActivity {

    EditText name,phoneNo,orgName,orgWebsite;
    TextView email,save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.profileEditToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (EditText)findViewById(R.id.name);
        email = (TextView) findViewById(R.id.emailAddress);
        phoneNo = (EditText) findViewById(R.id.phoneNo);
        orgName = (EditText) findViewById(R.id.orgName);
        orgWebsite = (EditText)findViewById(R.id.orgWebsite);
        save = (TextView)findViewById(R.id.save);

        final User user = EventsPreferences.getUser(UserProfileEditActivity.this);

        if (user.getEmail() != null) {
        email.setText(user.getEmail());
        name.setText(user.getName());
        phoneNo.setText(user.getPhoneNo());
        orgName.setText(user.getOrganisation());
        orgWebsite.setText(user.getOrgWebsite());
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                    name.setText(name.getText().toString());
//                    phoneNo.setText(phoneNo.getText().toString());
//                    orgName.setText(orgName.getText().toString());
//                    orgWebsite.setText(orgWebsite.getText().toString());
                user.setPhoneNo(phoneNo.getText().toString());
                user.setName(name.getText().toString());
                user.setOrganisation(orgName.getText().toString());
                user.setOrgWebsite(orgWebsite.getText().toString());
                EventsPreferences.saveUser(UserProfileEditActivity.this, user);
                Toast.makeText(UserProfileEditActivity.this, "SAVED", Toast.LENGTH_SHORT).show();
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
}}
