package com.ht.event.activity;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.widget.TextView;

import com.example.event.eventapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ht.event.modle.Item;

public class MapLocation extends AppCompatActivity implements GoogleMap.OnInfoWindowClickListener {
    private LatLng defaultLatLng = new LatLng(39.233956, -77.484703);
    private GoogleMap map;
    private int zoomLevel = 7;
    public TextView venue,venueAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_location);

        //Setting toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.mapbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        venue = (TextView)findViewById(R.id.venuemap);
        venueAddress = (TextView) findViewById(R.id.venueAddressmap);


        Item itemobjects = (Item) getIntent().getSerializableExtra("Item");
        venue.setText(itemobjects.getVenue());
        venueAddress.setText(itemobjects.getVenueAddress());

        try

        {
            map = ((MapFragment) getFragmentManager().findFragmentById(R.id.maplocation))
                    .getMap();
            if (map != null) {
                map.getUiSettings().setCompassEnabled(true);
                map.setTrafficEnabled(true);

                // Move the camera instantly to defaultLatLng.
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLatLng, zoomLevel));
                // Map zoom in out
                map.getUiSettings().setZoomControlsEnabled(true);
                map.addMarker(new MarkerOptions().position(defaultLatLng)
                        .title(itemobjects.getVenue())
                        .snippet(itemobjects.getVenueAddress())
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.ic_marker)));


                map.setOnInfoWindowClickListener(this);


            }


        } catch (NullPointerException e)


        {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        if (map != null) {

            map.setTrafficEnabled(false);
        }
        super.onPause();
    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(this, MapLocation.class);
        intent.putExtra("snippet", marker.getSnippet());
        intent.putExtra("title", marker.getTitle());
        intent.putExtra("position", marker.getPosition());
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        setTitle(item.getTitle());
        return super.onOptionsItemSelected(item);
    }

}
