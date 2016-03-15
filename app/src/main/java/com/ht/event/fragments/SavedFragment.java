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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ht.event.Main;
import com.ht.event.R;
import com.ht.event.adapter.BookmarkedListAdap;
import com.ht.event.adapter.ExploreItemListAdp;
import com.ht.event.model.Event;
import com.ht.event.model.EventList;
import com.ht.event.utils.EventsPreferences;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedFragment extends Fragment implements View.OnClickListener {
    private RecyclerView mListView;
    private BookmarkedListAdap bookmarkedListAdap;
    public ArrayList<Event> eventitem;
    private RelativeLayout relativeLayout;
    private View view;
    private String item;
    private TextView discover;
    private ArrayList<Event> bookmarkedArrayList;


    public SavedFragment() {
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
        view = inflater.inflate(R.layout.fragment_saved, container, false);
        mListView = (RecyclerView) view.findViewById(R.id.saved_bookmark);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.saveInfo);
        discover = (TextView) view.findViewById(R.id.discover);
        discover.setOnClickListener(this);


        eventitem = new ArrayList();
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Gson gson = new Gson();
        item = EventsPreferences.getBookmarked(getActivity());
        EventList eventList = gson.fromJson(item, EventList.class);
        if (item != null) {
            bookmarkedListAdap= new BookmarkedListAdap(getActivity(), eventList.getData());
            mListView.setAdapter(bookmarkedListAdap);
          //  display();
            relativeLayout.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);

        }
        else {

            relativeLayout.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
        }

        // ArrayList<Item> itemArrayList = itemList.getData();
        //ItemList itemList = gson.fromJson, ItemList.class);
       // display();
        return view;
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

        switch (id){
            case R.id.discover:
                Intent intent = new Intent(getActivity(),Main.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
        }
    }
}