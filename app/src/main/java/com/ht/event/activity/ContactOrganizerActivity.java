package com.ht.event.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ht.event.R;
import com.ht.event.dialog.DiscardMessage;
import com.ht.event.model.User;
import com.ht.event.utils.EventsPreferences;

import org.w3c.dom.Text;

public class ContactOrganizerActivity extends AppCompatActivity {
    DiscardMessage discardMessage;
    TextView userName,userEmail,userMessage;
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

        userName = (TextView)findViewById(R.id.userName);
        userEmail =(TextView)findViewById(R.id.userEmail);
        userMessage = (TextView)findViewById(R.id.message);

        User user = EventsPreferences.getUser(this);

        if(user.getEmail()!= null){
            userName.setText(user.getName());
            userEmail.setText(user.getEmail());

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
<<<<<<< Updated upstream
            discardMessage.show(this.getFragmentManager(),"discard");
=======
            discardMessage.show(this.getFragmentManager(),"logout ");
>>>>>>> Stashed changes
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
