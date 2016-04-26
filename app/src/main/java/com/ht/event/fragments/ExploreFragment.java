package com.ht.event.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ht.event.R;
import com.google.gson.Gson;
import com.ht.event.adapter.ExploreItemListAdapter;
import com.ht.event.model.Event;
import com.ht.event.model.EventList;
import com.ht.event.model.Session;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends Fragment  {
    private ArrayList<Event> eventitem;
    private RecyclerView mListView;
    private RecyclerView.Adapter ExploreItemList;
    private View view;

   // private ExploreItemListAdapter.ViewHolderExploreList.ClickListener clickListener;
   // public RecyclerView.LayoutManager mLayoutManager;


    public ExploreFragment() {
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
        mListView = (RecyclerView) view.findViewById(R.id.listview_explore);
        eventitem = new ArrayList();
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));

       // conversion gson from json
        String jsonData = getData();
        Gson gson = new Gson();
        EventList eventList = gson.fromJson(jsonData, EventList.class);
        ExploreItemList = new ExploreItemListAdapter(getActivity(), eventList.getData());
        mListView.setAdapter(ExploreItemList);

        return view;
    }



    public static String getData()

    {
        ArrayList<Event> data = new ArrayList<>();

        int[] icon = {R.drawable.cover1, R.drawable.cover2, R.drawable.cover3, R.drawable.cover4, R.drawable.cover5};
        int[] id ={1,2,3,4,5};
        String[] title = {"Title1", "Title2", "Title3", "Title4", "Title5"};
        String[] time = {"fri,feb 5, 10:00 am", "fri,feb 7, 6:00 pm", "fri,feb 10, 1:00 pm", "fri,feb 13, 5:00 pm", "fri,feb 18, 3:00 pm"};
        String[] venue = {"venue1", "venue2", "venue3", "venue4", "venue5"};
        String[] tag1 = {"#Business", "#Seminar", "#Game", "#Family&Education", "#Networking"};
        String[] tag2 = {"#Expo", "#Networking", "#Race", "#Game", "#Seminar"};
        String[] price = {"FREE", "FREE", "FREE", "$5-$8", "FREE"};
        String[] venueAddress = {"palam dwarka sector-8", "Gurgaon", "Faridabad", "Saket", "Punjabi Bagh"};
        String[] discription = {"description 1:I can almost bet that the thinking behind not implementing the buttons is because the zoom/pinch gestures are almost considered second nature these days The tradeoff for a lack of this indication (pinch/zoom actions) is a cleaner UI that allows the user to focus on the content, which in this case is finding a location on a map. The buttons within a mobile context of such limited real estate can be of a nuisance and can just get in the way"
                , "description 2", "description 3", "description 4", "description 5"};
        String[] organisationName = {"Uk Trade & Investment", "Analytics.Club", "WeAreTheCity India", "TechHub", "University of Maryland"};

        ArrayList<Session> sessionList = new ArrayList<>();
        Session session1 = new Session();
        session1.getTitle("session1");
        session1.getVenue("6th floor Room No. 356");
       session1.getPrice("free");
        sessionList.add(session1);

        Session session2 =new Session();
        session2.getTitle("session2");
        session2.getVenue("2nd floor Room No. 56");
        session2.getPrice("free");
        sessionList.add(session2);

        Session session3 =new Session();
        session3.getTitle("session2");
        session3.getVenue("11th floor Audi");
        session3.getPrice("free");
        sessionList.add(session3);

        for(int i=0;i<title.length;i++)
        {
            Event current=new Event();
            current.setId(id[i]);
            current.setImage(icon[i]);
            current.setTime(time[i]);
            current.setTitle(title[i]);
            current.setDiscription(discription[i]);
            current.setVenueAddress(venueAddress[i]);
            current.setVenue(venue[i]);
            current.setTag1(tag1[i]);
            current.setTag2(tag2[i]);
            current.setPrice(price[i]);
            current.setOrganisationName(organisationName[i]);
            current.setSessions(sessionList);
            data.add(current);

        }

        EventList eventList = new EventList();
        eventList.setData(data);
// conversion gson to json
        Gson gson = new Gson();
        String jsonData = gson.toJson(eventList,EventList.class);
        return jsonData;


    }

    @Override
    public void onResume() {
        super.onResume();
        //ExploreItemList.notifyDataSetChanged();
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
