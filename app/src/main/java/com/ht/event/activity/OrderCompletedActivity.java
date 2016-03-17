package com.ht.event.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ht.event.R;
import com.ht.event.model.Event;
import com.ht.event.utils.Config;
import com.ht.event.utils.EventsPreferences;
import com.ht.event.utils.Share;

import java.util.ArrayList;
import java.util.Calendar;

public class OrderCompletedActivity extends AppCompatActivity {

    private TextView add,title,time,done;
    private Event event;
    private ImageView share;
    private Context context;
    public ArrayList<Event> eventItem ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_completed);

        event = (Event) getIntent().getSerializableExtra(Config.ITEM_INTENT_OBJECT);

        add = (TextView) findViewById(R.id.add);
        title = (TextView) findViewById(R.id.compTitle);
        time = (TextView) findViewById(R.id.timeComp);
        done = (TextView)findViewById(R.id.done);
        share = (ImageView)findViewById(R.id.ic_share);
        title.setText(event.getTitle());
        time.setText(event.getTime());


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventsPreferences.saveRegistered(OrderCompletedActivity.this, event);
                Toast.makeText(OrderCompletedActivity.this, "Registration Completed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OrderCompletedActivity.this,MainActivity.class);
                intent.putExtra(Config.ITEM_INTENT_OBJECT,event);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCalendarEvent();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Share.shareItem(OrderCompletedActivity.this,"");
            }
        });
    }


    public void addCalendarEvent(){
        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.event/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", true);
        intent.putExtra("rrule", "");
        intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
        intent.putExtra("title", event.getTitle());
        intent.putExtra("location", event.getVenue());
    }
}
