package com.ht.event.activity;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import android.widget.TextView;

import com.example.event.eventapp.R;




public class DiscriptionItemListActivity extends AppCompatActivity {

    ImageView imageView;
    TextView time, registerbut;
    TextView title, price;
    private boolean bookmarked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.discription_of_item);
        imageView = (ImageView) findViewById(R.id.CoverView);

        time = (TextView) findViewById(R.id.event_time);
        title = (TextView) findViewById(R.id.event_name);
        price = (TextView) findViewById(R.id.price);
        TextView text = (TextView) findViewById(R.id.text_fill);
        registerbut = (TextView) findViewById(R.id.registerBut);


        imageView.setImageResource(getIntent().getIntExtra("CoverImg", 00));
        time.setText(getIntent().getStringExtra("Time"));
        title.setText(getIntent().getStringExtra("Title"));
        price.setText(getIntent().getStringExtra("Price"));

        //Setting toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.discrbar);
        setSupportActionBar(mToolbar);
        //final ActionBar ab = getSupportActionBar();
        // ab.setHomeAsUpIndicator(R.drawable.wbackk);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        registerbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DiscriptionItemListActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });

// Create an icon for floating action bar
        /*icon = new ImageView(this);
        icon.setImageResource(R.drawable.add);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();


        ImageView iconSnap=new ImageView(this);
        iconSnap.setImageResource(R.drawable.snap);

        ImageView iconShare=new ImageView(this);
        iconShare.setImageResource(R.drawable.ic_share);

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


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_discription_of_item, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        switch (id) {

            case R.id.share_ic:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                sharingIntent.putExtra(Intent.EXTRA_TITLE, "Check Out: ");
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;

            case R.id.bookmark_ic:

                if (!bookmarked) {
//                bookmark.setImageResource(R.drawable.ic_starfill);
                      bookmarked = true;

                } else {
//                bookmark.setImageResource(R.drawable.ic_starw);
                      bookmarked = false;
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

   /* @Override
    public void onClick(View v) {

       /*int id=v.getId();
        switch(id){
            case R.id.flagbut:
                break;
            case R.id.registerbut:
                Intent intent= new Intent(v.getContext(),egistrationActivity.class);
                startActivityForResult(intent,0);

                break;
            case R.id.sharebut:
                break;


            default:
                Toast.makeText(this, "Not Implemented!", Toast.LENGTH_SHORT).show();
                break;
        }*/



}


