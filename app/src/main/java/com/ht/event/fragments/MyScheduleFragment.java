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
import com.ht.event.R;
import com.ht.event.activity.MainActivity;
import com.ht.event.adapter.ExploreItemListAdp;
import com.ht.event.adapter.RegisteredListAdapter;
import com.ht.event.model.Event;
import com.ht.event.model.EventList;
import com.ht.event.model.Scheduler;
import com.ht.event.model.SchedulerList;
import com.ht.event.utils.EventsPreferences;

import java.util.ArrayList;


public class MyScheduleFragment extends Fragment implements View.OnClickListener {
    private ArrayList<Event> eventitem;
    private RecyclerView mListView;
    private RegisteredListAdapter registeredListAdap;
    private View view;
    private TextView discover;
    private String item;
    private RelativeLayout relativeLayout;


    public MyScheduleFragment() {
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
        view = inflater.inflate(R.layout.fragment_my__schedule, container, false);
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
            registeredListAdap= new RegisteredListAdapter(getActivity(), eventList.getData());
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


    public static String getScheduler() {
        ArrayList<Scheduler> scheduler = new ArrayList<>();

        String[] month = {"january","february","march","april","may"};
        String[] day ={"monday","tuesday","wednesday","thursday","friday"};
        String[] date ={"05","03","02","07","08"};
        String[] time ={"09:30 AM","07:00 AM","09:00 AM","05:35 AM","06:15 PM"};
        String[] detailing={"Description1","Description2","Description3","Description4","Description5"};

        for(int i=0;i<month.length;i++)
        {
            Scheduler schedulers= new Scheduler();
            schedulers.setMonth(month[i]);
            schedulers.setDay(day[i]);
            schedulers.setDate(date[i]);
            schedulers.setTime(time[i]);
            schedulers.setDetailing(detailing[i]);
            scheduler.add(schedulers);
        }
        SchedulerList schedulerList= new SchedulerList();
        schedulerList.setScheduler(scheduler);
// conversion gson to json
        Gson gson = new Gson();
        String jsonData = gson.toJson(schedulerList,SchedulerList.class);
        return jsonData;

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        int i =(v.getId());

        switch(i){
            case R.id.discover:
                Intent intent =new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

        }
    }
}
