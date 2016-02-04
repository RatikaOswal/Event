package com.example.event.eventapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.event.eventapp.R;
import com.example.event.eventapp.adapter.ExploreItemList;
import com.example.event.eventapp.modle.Item;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Explore extends Fragment {
    public ArrayList<Item> eventitem;
    private RecyclerView mListView;
    private RecyclerView.Adapter ListOfExploreItem;
    private View view;
   // public RecyclerView.LayoutManager mLayoutManager;


    public Explore() {
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

        view = inflater.inflate(R.layout.fragment_explore, container, false);
        mListView =(RecyclerView) view.findViewById(R.id.listview_explore);
        eventitem=new ArrayList();

        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListOfExploreItem = new ExploreItemList(getActivity(),getData());
        mListView.setAdapter(ListOfExploreItem);


        return view;

    }

    public static ArrayList<Item> getData()

    {
        ArrayList<Item> data =new ArrayList<>();
        String []title={"Title1","Title2","Title3","Title4","Title5"};
        String []time={"fri,feb 5, 10:00 am","fri,feb 7, 6:00 pm","fri,feb 10, 1:00 pm","fri,feb 13, 5:00 pm","fri,feb 18, 3:00 pm"};
        String []venue={"venue1","venue2","venue3","venue4","venue5"};
        int []icon={R.drawable.cover1,R.drawable.cover2,R.drawable.cover3,R.drawable.cover4,R.drawable.cover5};

        for(int i=0;i<title.length && i<time.length&&i<venue.length&&i<icon.length;i++)
        {
            Item current=new Item();
            current.setImage(icon[i]);
            current.setTime(time[i]);
            current.setTitle(title[i]);
            current.setVenue(venue[i]);
            data.add(current);


        }

        return data;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
