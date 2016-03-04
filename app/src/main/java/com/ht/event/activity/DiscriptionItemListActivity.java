package com.ht.event.activity;


import android.content.Context;
import android.content.Intent;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ht.event.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ht.event.Interface.DataHandler;
import com.ht.event.dialog.ConnectionDetector;
import com.ht.event.dialog.ConnectionFragment;
import com.ht.event.model.GeocoderLocation;
import com.ht.event.model.Item;
import com.ht.event.model.User;
import com.ht.event.utils.Config;
import com.ht.event.utils.EventsPreferences;

import java.util.HashMap;


public class DiscriptionItemListActivity extends AppCompatActivity implements DataHandler {

    public ImageView imageView;
    private GoogleMap map;
    public MenuItem BookmarkItem;
    public Item itemObjects;
    public TextView time, registerbut, description,organisationName;
    public TextView title, price, venue, venueAddress;
    private boolean bookmarked;
    private int zoomLevel = 12;
    public double latitude, longitude;







    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.discription_of_item);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.address_frame);

        imageView = (ImageView) findViewById(R.id.CoverView);
        time = (TextView) findViewById(R.id.event_time);
        title = (TextView) findViewById(R.id.event_name);
        price = (TextView) findViewById(R.id.price);
        registerbut = (TextView) findViewById(R.id.registerBut);
        venue = (TextView) findViewById(R.id.venue);
        venueAddress = (TextView) findViewById(R.id.venueAddress);
        description = (TextView) findViewById(R.id.text_discription);
        organisationName = (TextView) findViewById(R.id.organisationName);


        itemObjects = (Item) getIntent().getSerializableExtra("Item");
        imageView.setImageResource(itemObjects.getImage());
        time.setText(itemObjects.getTime());
        title.setText(itemObjects.getTitle());
        price.setText(itemObjects.getPrice());
        venue.setText(itemObjects.getVenue());
        description.setText(itemObjects.getDiscription());
        venueAddress.setText(itemObjects.getVenueAddress());
        organisationName.setText(itemObjects.getOrganisationName());
        String address = itemObjects.getVenueAddress();


        //GeoCoder
        GeocoderLocation locationAddress = new GeocoderLocation();
        locationAddress.getAddressFromLocation(address,
                getApplicationContext(), new GeocoderHandler());

        //Setting toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.discrbar);
        setSupportActionBar(mToolbar);
        //final ActionBar ab = getSupportActionBar();
        // ab.setHomeAsUpIndicator(R.drawable.wbackk);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DiscriptionItemListActivity.this, EventDescriptionActivity.class);
                intent.putExtra("Item", itemObjects);
                startActivity(intent);
            }
        });

        registerbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(  View v) {
                User user = EventsPreferences.getUser(DiscriptionItemListActivity.this);
                if(user.getEmail() == null){
                    Intent intent = new Intent(DiscriptionItemListActivity.this, RegistrationActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(DiscriptionItemListActivity.this,AttendeesInfoActivity.class);
                    startActivity(intent);
                }


            }
        });

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.maplocationstatic))
                .getMap();


        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DiscriptionItemListActivity.this, MapLocationActivity.class);
                intent.putExtra("Item", itemObjects);
                intent.putExtra("Latitude", latitude);
                intent.putExtra("Longitude", longitude);
                startActivity(intent);


            }
        });
    }
        public void contactInfo(View view)
    {
        Intent intent =new Intent(DiscriptionItemListActivity.this,ContactOrganizerActivity.class);
        startActivity(intent);


    }


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




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_discription_of_item, menu);
        BookmarkItem = menu.findItem(R.id.bookmark_ic);
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

                    BookmarkItem.setIcon(R.drawable.ic_starfill);
                    bookmarked = true;

                } else {
                    BookmarkItem.setIcon(R.drawable.ic_starw);
                    bookmarked = false;
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(HashMap<String, Object> map) {
        Item item = (Item) map.get(Config.KEY_DATA);
        item.getLat();
        item.getLng();

    }

    @Override
    public void onFailure(HashMap<String, Object> map) {

    }

    private class GeocoderHandler extends Handler {
        public void handleMessage(Message message) {
            String locationAddress;
            String [] separate;

            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
                    Boolean isInternetPresent = cd.isConnectingToInternet();
                    if(isInternetPresent && locationAddress != null){
                    separate=locationAddress.split(",");
                    latitude=Double.parseDouble(separate[0]);
                    longitude=Double.parseDouble(separate[1]);
                    showMap(latitude, longitude);}
                    else
                    {
                        ConnectionFragment  cFragment = new ConnectionFragment();
                        // Show DialogFragment
                        cFragment.show(DiscriptionItemListActivity.this,"No Internet Connection","You don't have internet connection.",false);

                    }

                    break;
                default:
                    locationAddress = null;
            }


        }
    }

    private void showMap(double latitude, double longitude) {
        if (map != null) {
            map.getUiSettings().setAllGesturesEnabled(false);

            // Move the camera instantly to defaultLatLng.
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), zoomLevel));

            map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.ic_marker)));

        }




    }
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






