package com.ht.event.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ht.event.R;
import com.ht.event.model.Event;
import com.ht.event.model.EventList;
import com.ht.event.utils.EventsPreferences;

import java.util.ArrayList;


public class BookmarkedListAdap extends RecyclerView.Adapter<BookmarkedListAdap.ViewHolderBookmarkedList> {

    private LayoutInflater layoutInflater;
    public ArrayList<Event> eventitem ;
    public Context context;
    private ArrayList<Event> bookmarkedArrayList;


    public BookmarkedListAdap(Context context,ArrayList<Event> eventitem){
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        this.eventitem=eventitem;
        String eventsInStr = EventsPreferences.getBookmarked(context);
        if(eventsInStr!=null) {
            Gson gson = new Gson();
            EventList eventList = gson.fromJson(eventsInStr, EventList.class);
            bookmarkedArrayList = eventList.getData();
        }


    }
    @Override
    public BookmarkedListAdap.ViewHolderBookmarkedList onCreateViewHolder(ViewGroup parent, int viewType) {
         View view = layoutInflater.inflate(R.layout.fargment_saved_card,parent,false);
        ViewHolderBookmarkedList viewHolderBookmarkedList;
        viewHolderBookmarkedList = new ViewHolderBookmarkedList(view,context,eventitem);
        return viewHolderBookmarkedList;


    }

    @Override
    public void onBindViewHolder(BookmarkedListAdap.ViewHolderBookmarkedList holder, int position) {
        final Event current = eventitem.get(position);
        holder.bind(current);




    }

    @Override
    public int getItemCount() {
        return eventitem.size();
    }

    public class ViewHolderBookmarkedList extends RecyclerView.ViewHolder {
        public Context context;
        public TextView title,price,time;
        public ImageView cover;
        ArrayList<Event>eventitem = new ArrayList<Event>();

        public ViewHolderBookmarkedList(View itemView,Context context,ArrayList<Event>eventitem)
        {
            super(itemView);
            this.eventitem=eventitem;
            this.context=context;
            cover = (ImageView) itemView.findViewById(R.id.CoverView);
            time = (TextView) itemView.findViewById(R.id.event_time);
            title = (TextView) itemView.findViewById(R.id.event_name);
            price=(TextView)itemView.findViewById(R.id.price);

        }
        public void bind(Event event)
        {
            title.setText(event.getTitle());
            time.setText(event.getTime());
            price.setText(event.getPrice());
            cover.setImageResource(event.getImage());


        }
    }

}
