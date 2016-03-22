package com.ht.event.fragments;


import android.content.Context;
import android.content.Intent;
import android.media.Image;
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
import com.ht.event.adapter.RegisteredListAdap;
import com.ht.event.model.Event;
import com.ht.event.model.EventList;
import com.ht.event.utils.Config;
import com.ht.event.utils.EventsPreferences;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketDetailFragment extends Fragment {
    private View view;
    private ImageLoader imgLoader;
    private Event event;
    private ImageView qrImage;
    private String fullUrl = Config.BASE_QR_URL;
    private ArrayList<Event> registerArrayList;


    public TicketDetailFragment() {
        // Required empty public constructor
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
        view = inflater.inflate(R.layout.fragment_ticket_detail, container, false);
        qrImage = (ImageView) view.findViewById(R.id.qrImg);
        event = (Event) getActivity().getIntent().getSerializableExtra(Config.ITEM_INTENT_OBJECT);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity()).build();
        imgLoader = ImageLoader.getInstance();
        imgLoader.init(config);

        if (event.getTicketId() == null) {

            String eventsInStr = EventsPreferences.getRegistered(getActivity());
            Gson gson = new Gson();
            EventList eventList = gson.fromJson(eventsInStr, EventList.class);
            registerArrayList = eventList.getData();

            for (Event event : registerArrayList) {

                if (this.event.equals(event)) {
                    try {
                        fullUrl += URLEncoder.encode(event.getTicketId(), "UTF-8");
                        imgLoader.displayImage(fullUrl, qrImage);
                        this.event = event;
                        break;

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
                else{
                    try {

                        fullUrl += URLEncoder.encode(event.getTicketId(), "UTF-8");
                        imgLoader.displayImage(fullUrl, qrImage);

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }



        return view;
    }


        @Override
        public void onDetach () {
            super.onDetach();
        }

        @Override
        public void onDestroy () {
            super.onDestroy();
        }

    }


