package com.ht.event.adapter;


import android.app.Activity;
import android.content.ClipData;
import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.ht.event.R;
import com.ht.event.activity.EventDetailActivity;
import com.ht.event.dialog.MoreInfoMessage;
import com.ht.event.dialog.RegisteredMessage;
import com.ht.event.model.Event;
import com.ht.event.model.EventList;
import com.ht.event.utils.Config;
import com.ht.event.utils.EventsPreferences;
import com.ht.event.utils.Share;

import java.util.ArrayList;


public class ExploreItemListAdp extends RecyclerView.Adapter<ExploreItemListAdp.ViewHolderExploreList>{

    private LayoutInflater layoutInflater;
    public ArrayList<Event> eventitem ;
    public Context context;
    private ArrayList<Event> bookmarkedArrayList;
    private ArrayList<Event> registeredArrayList;



    public ExploreItemListAdp(Context context, ArrayList<Event> eventitem){

        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        this.eventitem=eventitem;
        String eventsInStr = EventsPreferences.getBookmarked(context);
        if(eventsInStr!=null) {
            Gson gson = new Gson();
            EventList eventList = gson.fromJson(eventsInStr, EventList.class);
            bookmarkedArrayList = eventList.getData();
        }

        String registerInStr = EventsPreferences.getRegistered(context);
        if(registerInStr!=null){
            Gson gson = new Gson();
            EventList regList = gson.fromJson(registerInStr, EventList.class);
            registeredArrayList = regList.getData();

        }
    }




    @Override
    public ViewHolderExploreList onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=layoutInflater.inflate(R.layout.list_exlore_layout, parent, false);
        ViewHolderExploreList viewHolderExploreList;
        viewHolderExploreList = new ViewHolderExploreList(view,context,eventitem);


        return viewHolderExploreList;


    }

    @Override
    public void onBindViewHolder(final ViewHolderExploreList holder, int position) {
        final Event current = eventitem.get(position);

        if(bookmarkedArrayList!=null && bookmarkedArrayList.contains(current)) {
            holder.star.setImageResource(R.drawable.ic_starfill);
            current.setIs_bookmarked(true);

        }

        if(registeredArrayList !=null && registeredArrayList.contains(current))
        {
            current.setIs_registered(true);
        }
        holder.time.setText(current.time);
        holder.title.setText(current.title);
        holder.venue.setText(current.venue);
        holder.cover.setImageResource(current.image);
        holder.tag1.setText(current.tag1);
        holder.tag2.setText(current.tag2);
        holder.price.setText(current.price);

        holder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!current.is_bookmarked()) {

                    holder.star.setImageResource(R.drawable.ic_starfill);
                    current.setIs_bookmarked(true);
                    EventsPreferences.saveBookmarked(ExploreItemListAdp.this.context, current);


                } else {
                    holder.star.setImageResource(R.drawable.ic_sstar);
                    current.setIs_bookmarked(false);
                    EventsPreferences.removeBookmarked(ExploreItemListAdp.this.context, current);
                }

            }

        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Share.shareItem(context,"");
            }
        });


    }

    @Override
    public int getItemCount() {

        return this.eventitem.size();
        //back data.getSize();

    }

     public static class ViewHolderExploreList extends RecyclerView.ViewHolder implements View.OnClickListener{

         private ImageView cover,star,share;
         private TextView time;
         private TextView title,tag2,price;
         private TextView venue,tag1;
         private Context context;
         private LinearLayout list;
         // public ClickListener clickListener;

        ArrayList<Event>eventitem=new ArrayList<Event>();


         public ViewHolderExploreList(View itemView,Context context,ArrayList<Event>eventitem)
         {
             super(itemView);
             this.eventitem=eventitem;
             this.context=context;
             list = (LinearLayout)itemView.findViewById(R.id.list);
             cover = (ImageView) itemView.findViewById(R.id.CoverView);
             time = (TextView) itemView.findViewById(R.id.event_time);
             title = (TextView) itemView.findViewById(R.id.event_name);
             venue = (TextView) itemView.findViewById(R.id.event_venue);
             tag1 =(TextView) itemView.findViewById(R.id.tag1);
             tag2 =(TextView) itemView.findViewById(R.id.tag2);
             star =(ImageView)itemView.findViewById(R.id.bookmark);
             share=(ImageView)itemView.findViewById(R.id.share);
             price=(TextView)itemView.findViewById(R.id.price);
             list.setOnClickListener(this);

         }



         @Override
         public void onClick(View v) {
             int position = getAdapterPosition();
             Event event = this.eventitem.get(position);
           if(event.is_registered())
           {
               Activity activity  =(Activity) context;
               RegisteredMessage registeredMessage = new RegisteredMessage(activity);
               registeredMessage.show(activity.getFragmentManager(), "Registered Message");

           }
             else{
               Intent intent = new Intent(this.context, EventDetailActivity.class);
               intent.putExtra(Config.ITEM_INTENT_OBJECT, event);
               this.context.startActivity(intent);
           }

         }
     }
    }
