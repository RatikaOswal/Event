package com.ht.event.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.ht.event.R;
import com.ht.event.adapter.ExploreItemListAdp;
import com.ht.event.model.Event;
import com.ht.event.model.EventList;
import com.ht.event.model.Item;
import com.ht.event.utils.EventsPreferences;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedFragment extends Fragment {
    private RecyclerView mListView;
    private RecyclerView.Adapter ExploreItemList;
    public ArrayList<Event> eventitem;
    private View view;
    String dataListInStr;
    public static final String BOOKMARKED_INFO = "bookmarkedInfo";
    public static final String BOOKMARKED_LIST = "bookmarkedList";



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
        view= inflater.inflate(R.layout.fragment_saved, container, false);
        mListView = (RecyclerView) view.findViewById(R.id.saved_bookmark);
        eventitem = new ArrayList();
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));




        Gson gson = new Gson();
        String item =EventsPreferences.getBookmarked(getActivity());
        EventList eventList = gson.fromJson(item, EventList.class);


      // ArrayList<Item> itemArrayList = itemList.getData();
        //ItemList itemList = gson.fromJson, ItemList.class);


        ExploreItemList = new ExploreItemListAdp(getActivity(), eventList.getData());
        mListView.setAdapter(ExploreItemList);






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
}
