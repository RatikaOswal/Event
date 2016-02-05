package com.example.event.eventapp.activity;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.event.eventapp.R;

public class DiscriptionOfItem extends AppCompatActivity {

    ImageView imageView,icon;
    TextView time;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.discription_of_item);
        imageView =(ImageView)findViewById(R.id.CoverView);
        time =(TextView)findViewById(R.id.event_time);
        title=(TextView)findViewById(R.id.event_name);
        imageView.setImageResource(getIntent().getIntExtra("CoverImg", 00));
        time.setText(getIntent().getStringExtra("Time"));
        title.setText(getIntent().getStringExtra("Title"));



// Create an icon for floating action bar
        /*icon = new ImageView(this);
        icon.setImageResource(R.drawable.add);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();


        ImageView iconSnap=new ImageView(this);
        iconSnap.setImageResource(R.drawable.snap);

        ImageView iconShare=new ImageView(this);
        iconShare.setImageResource(R.drawable.share);

        ImageView iconRegister=new ImageView(this);
        iconRegister.setImageResource(R.drawable.register);

        ImageView iconFlag=new ImageView(this);
        iconFlag.setImageResource(R.drawable.star);

//Creating Button
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        SubActionButton snapbut = itemBuilder.setContentView(iconSnap).build();
        SubActionButton sharebut = itemBuilder.setContentView(iconShare).build();
        SubActionButton registerbut = itemBuilder.setContentView(iconRegister).build();
        SubActionButton flagbut = itemBuilder.setContentView(iconFlag).build();

        //floating action menu

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(snapbut)
                .addSubActionView(sharebut)
                .addSubActionView(registerbut)
                .addSubActionView(flagbut)
                .attachTo(actionButton)
                .build();*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_discription_of_item, menu);
        return true;
    }
}


