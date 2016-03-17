package com.ht.event.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.ht.event.R;
import com.ht.event.activity.ContactOrganizerActivity;
import com.ht.event.activity.EventDescriptionActivity;
import com.ht.event.activity.EventVenueLocationActivity;
import com.ht.event.activity.OrganisationDescription;
import com.ht.event.model.Event;
import com.ht.event.model.EventList;
import com.ht.event.utils.Config;
import com.ht.event.utils.EventsPreferences;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Event event;
    private TextView title, time, description, venueName, venueAddress,organisation,contact;
    private ImageView coverView;
    private GoogleMap map;
    private int zoomLevel = 12;
    private LinearLayout mapLayout;
    private double latitude,longitude;
    private ArrayList<Event> registerArrayList;


    public InfoFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_info, container, false);
        event = (Event) getActivity().getIntent().getSerializableExtra(Config.ITEM_INTENT_OBJECT);

        title = (TextView) view.findViewById(R.id.event_name);
        time = (TextView) view.findViewById(R.id.event_time);
        description = (TextView) view.findViewById(R.id.description);
        venueName = (TextView) view.findViewById(R.id.venue);
        venueAddress = (TextView) view.findViewById(R.id.venueAddress);
        coverView = (ImageView) view.findViewById(R.id.CoverView);
        mapLayout = (LinearLayout) view.findViewById(R.id.mapLayout);
        organisation = (TextView)view.findViewById(R.id.organisationName);
        contact = (TextView)view.findViewById(R.id.contact);

        title.setText(event.getTitle());
        time.setText(event.getTime());
        description.setText(event.getDiscription());
        venueName.setText(event.getVenue());
        venueAddress.setText(event.getVenueAddress());
        coverView.setImageResource(event.getImage());


    if(event.getLat()== null  && event.getLng() ==null ) {

        String eventsInStr = EventsPreferences.getRegistered(getActivity());
        if(eventsInStr!=null) {
            Gson gson = new Gson();
            EventList eventList = gson.fromJson(eventsInStr, EventList.class);
             registerArrayList= eventList.getData();
        }



    }




        map = ((MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.maplocationstatic))
                .getMap();
        showMap(latitude, longitude);
        mapLayout.setOnClickListener(this);
        description.setOnClickListener(this);
        contact.setOnClickListener(this);
        organisation.setOnClickListener(this);


        return view;
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

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {

            case R.id.mapLayout :
            Intent intent = new Intent(getActivity(), EventVenueLocationActivity.class);
            intent.putExtra(Config.ITEM_INTENT_OBJECT, event);
            startActivity(intent);
                break;
            case R.id.description :
                Intent intent1 = new Intent(getActivity(), EventDescriptionActivity.class);
                intent1.putExtra(Config.ITEM_INTENT_OBJECT, event);
                startActivity(intent1);
                break;
            case R.id.contact :
                Intent intent2 = new Intent(getActivity(), ContactOrganizerActivity.class);
                startActivity(intent2);
                break;
            case R.id.organisationName :
                Intent intent3 = new Intent(getActivity(),OrganisationDescription.class);
                startActivity(intent3);
                break;
        }

    }
}
