package com.ht.event.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ht.event.R;
import com.ht.event.activity.EventDetailActivity;
import com.ht.event.activity.TicketInfoActivity;
import com.ht.event.model.Event;
import com.ht.event.model.EventList;
import com.ht.event.utils.Config;
import com.ht.event.utils.EventsPreferences;

import java.util.ArrayList;

/**
 * Created by RATIKA on 15-Mar-16.
 */
public class RegisteredListAdap extends RecyclerView.Adapter<RegisteredListAdap.ViewHolderRegisteredList> {
    private LayoutInflater layoutInflater;
    public ArrayList<Event> eventitem;
    public Context context;
    private ArrayList<Event> registeredArrayList;

    public RegisteredListAdap(Context context, ArrayList<Event> eventitem) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.eventitem = eventitem;
        String eventsInStr = EventsPreferences.getRegistered(context);
        if (eventsInStr != null) {
            Gson gson = new Gson();
            EventList eventList = gson.fromJson(eventsInStr, EventList.class);
            registeredArrayList = eventList.getData();
        }
    }

    @Override
    public  RegisteredListAdap.ViewHolderRegisteredList onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.fragment_saved_card, parent, false);
        RegisteredListAdap.ViewHolderRegisteredList viewHolderRegisteredList;
        viewHolderRegisteredList = new ViewHolderRegisteredList(view, context, eventitem);
        return viewHolderRegisteredList;
    }

    @Override
    public void onBindViewHolder(RegisteredListAdap.ViewHolderRegisteredList holder, int position) {
        final Event current = eventitem.get(position);
        holder.bind(current);
    }


    @Override
    public int getItemCount() {
        return eventitem.size();
    }

    public class ViewHolderRegisteredList extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Context context;
        public TextView title, price, time;
        public ImageView cover;
        ArrayList<Event> eventitem = new ArrayList<Event>();

        public ViewHolderRegisteredList(View itemView, Context context, ArrayList<Event> eventitem) {
            super(itemView);
            this.eventitem = eventitem;
            this.context = context;
            itemView.setOnClickListener(this);
            cover = (ImageView) itemView.findViewById(R.id.CoverView);
            time = (TextView) itemView.findViewById(R.id.event_time);
            title = (TextView) itemView.findViewById(R.id.event_name);
            price = (TextView) itemView.findViewById(R.id.price);

        }

        public void bind(Event event) {
            title.setText(event.getTitle());
            time.setText(event.getTime());
            price.setText(event.getPrice());
            cover.setImageResource(event.getImage());


        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Event event = this.eventitem.get(position);
            Intent intent = new Intent(this.context, TicketInfoActivity.class);
            intent.putExtra(Config.ITEM_INTENT_OBJECT, event);
            this.context.startActivity(intent);

        }
    }
}
