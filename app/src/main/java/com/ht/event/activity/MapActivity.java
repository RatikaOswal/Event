package com.ht.event.activity;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;;
import android.os.AsyncTask;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ht.event.Interface.DataHandler;
import com.ht.event.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;


public class MapActivity extends AppCompatActivity implements DataHandler,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener {

    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    private GoogleMap map;
    private int zoomLevel = 12;
    private double latitude, longitude;
    TextView mLocationView;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    LocationListener mLocationListener;
    Location mCurrentLocation;
    String mLastUpdateTime;

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map1);
       mLocationView = (TextView) findViewById(R.id.mLocation);

        if (!isGooglePlayServicesAvailable()) {
            finish();
        }
        createLocationRequest();


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maplocationstatic1))
                .getMap();


    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }


    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);

        LocationServices.FusedLocationApi.requestLocationUpdates
                (mGoogleApiClient, mLocationRequest, mLocationListener);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


    @Override
    public void onLocationChanged(Location location) {
            mLocationView.setText("Location received: " + location.toString());
            mCurrentLocation = location;
            mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
            addMarker();


    }

    private void addMarker() {
        MarkerOptions options = new MarkerOptions();
        LatLng currentLatLng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
        options.position(currentLatLng);
        Marker mapMarker = map.addMarker(options);
        long atTime = mCurrentLocation.getTime();
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date(atTime));
        mapMarker.setTitle(mLastUpdateTime);

    }


    @Override
    public void onSuccess(HashMap<String, Object> map) {

    }

    @Override
    public void onFailure(HashMap<String, Object> map) {

    }
}
