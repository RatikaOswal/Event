package com.ht.event.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ht.event.R;
import com.ht.event.model.Event;
import com.ht.event.utils.Config;

import java.util.Calendar;

public class OrderCompletedActivity extends AppCompatActivity {

    TextView add,title,time,done;
    Event event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_completed);

        event = (Event) getIntent().getSerializableExtra(Config.ITEM_INTENT_OBJECT);
        add = (TextView) findViewById(R.id.add);
        title = (TextView) findViewById(R.id.compTitle);
        time = (TextView) findViewById(R.id.timeComp);
        done = (TextView)findViewById(R.id.done);

        title.setText(event.getTitle());
        time.setText(event.getTime());

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Toast.makeText(OrderCompletedActivity.this, "Registration Completed", Toast.LENGTH_SHORT).show();

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCalendarEvent();
            }
        });
    }


    public void addCalendarEvent(){
        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.event/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", true);
        intent.putExtra("rrule", "FREQ=ONETIME");
        intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
        intent.putExtra("title", event.getTitle());
        intent.putExtra("location", event.getVenue());
        startActivity(intent);
    }
}
