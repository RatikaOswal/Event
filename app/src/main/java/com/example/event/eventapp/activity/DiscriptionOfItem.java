package com.example.event.eventapp.activity;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.event.eventapp.R;
import com.github.clans.fab.FloatingActionButton;

public class DiscriptionOfItem extends AppCompatActivity  {

    ImageView imageView;
    TextView time,registerbut;
    TextView title;
    FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.discription_of_item);
        imageView = (ImageView) findViewById(R.id.CoverView);
        time = (TextView) findViewById(R.id.event_time);
        title = (TextView) findViewById(R.id.event_name);
        imageView.setImageResource(getIntent().getIntExtra("CoverImg", 00));
        time.setText(getIntent().getStringExtra("Time"));
        title.setText(getIntent().getStringExtra("Title"));

        //Setting toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.discrbar);
        setSupportActionBar(mToolbar);
        final ActionBar ab = getSupportActionBar();

        getSupportActionBar().setHomeButtonEnabled(true);
       // ab.setHomeAsUpIndicator(R.drawable.wbackk);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



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


        //building the subAction buttons
       /* floatingActionButton =(FloatingActionButton)findViewById(R.id.flagbut);
        floatingActionButton.setOnClickListener(this);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.sharebut);
        floatingActionButton.setOnClickListener(this);*/


        //register text
       // registerbut=(TextView)findViewById(R.id.registerbut);
        //registerbut.setOnClickListener(this);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_discription_of_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id=item.getItemId();

        if(id==android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

   /* @Override
   public void onClick(View v) {

       int id=v.getId();
        switch(id){
            case R.id.flagbut:
                break;
            case R.id.registerbut:
                Intent intent= new Intent(v.getContext(),Registration.class);
                startActivityForResult(intent,0);

                break;
            case R.id.sharebut:
                break;


            default:
                Toast.makeText(this, "Not Implemented!", Toast.LENGTH_SHORT).show();
                break;
        }*/


    }


