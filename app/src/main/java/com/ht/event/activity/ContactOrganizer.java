package com.ht.event.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ht.event.R;
import com.ht.event.dialog.DiscardMessage;

public class ContactOrganizer extends AppCompatActivity {
    DiscardMessage discardMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_organizer);

        discardMessage = new DiscardMessage(this);

        //Setting toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.organiserBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {

            discardMessage.show(this.getFragmentManager(),"discard ");
//            onBackPressed();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
