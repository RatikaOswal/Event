package com.ht.event.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ht.event.activity.MainActivity;
import com.ht.event.R;
import com.ht.event.adapter.RegisteredListAdap;
import com.ht.event.model.Event;
import com.ht.event.model.EventList;
import com.ht.event.utils.EventsPreferences;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TicketsFragment extends Fragment implements View.OnClickListener{
    private RecyclerView mListView;
    private RelativeLayout relativeLayout;
    private View view;
    private TextView discover;
    private String item;
    private RegisteredListAdap registeredListAdap;
    public ArrayList<Event> eventitem;


    public TicketsFragment() {
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
        view = inflater.inflate(R.layout.fragment_tickets, container, false);
        mListView = (RecyclerView)view.findViewById(R.id.saved_ticket);
        relativeLayout = (RelativeLayout)view.findViewById(R.id.saveInfo);
        discover = (TextView)view.findViewById(R.id.discover);
        discover.setOnClickListener(this);
        eventitem = new ArrayList();
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Gson gson = new Gson();
        item = EventsPreferences.getRegistered(getActivity());
        EventList eventList = gson.fromJson(item, EventList.class);
        if (item != null) {
            registeredListAdap= new RegisteredListAdap(getActivity(), eventList.getData());
            mListView.setAdapter(registeredListAdap);
            //  display();
            relativeLayout.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);

        }
        else {

            relativeLayout.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
        }

     return view ;
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

        switch (id)
        {
            case R.id.discover :
                Intent intent =new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);


                break;

        }
    }
}
