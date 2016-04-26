package com.ht.event.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ht.event.R;
import com.ht.event.dialog.ConnectionMessage;
import com.ht.event.model.User;
import com.ht.event.utils.ConnectionDetector;

/**
 * Created by hp on 4/12/2016.
 */
public class GeocoderHandler extends Handler {
    User userObjects;
    private double latitude,longitude;
    private GoogleMap map;
    private int zoomLevel = 12;


    public void handleMessage(Message message) {
        String locationAddress;
        String[] separate;



      /*  switch (message.what) {
            case 1:
                Bundle bundle = message.getData();
                locationAddress = bundle.getString("address");
                ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
                Boolean isInternetPresent = cd.isConnectingToInternet();
               if (isInternetPresent && locationAddress != null) {
                    separate = locationAddress.split(",");
                    latitude = Double.parseDouble(separate[0]);
                    longitude = Double.parseDouble(separate[1]);
                    userObjects.setLat(String.valueOf(latitude));
                    userObjects.setLng(String.valueOf(longitude));
                    showMap(latitude, longitude);

                } else {

                }

                break;
            default:
                locationAddress = null;
        } */


    }



    private void showMap(double latitude, double longitude){
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
