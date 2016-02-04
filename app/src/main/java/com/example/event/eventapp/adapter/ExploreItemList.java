package com.example.event.eventapp.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.event.eventapp.R;
import com.example.event.eventapp.modle.Item;

import java.util.ArrayList;


public class ExploreItemList extends RecyclerView.Adapter<ExploreItemList.ViewHolderExploreList>{

    private LayoutInflater layoutInflater;
    private ArrayList<Item> eventitem;

    public ExploreItemList(Context context, ArrayList<Item> array){
        eventitem = array;
        layoutInflater=LayoutInflater.from(context);

    }

    public void setEventitem(ArrayList<Item>eventitem){
        this.eventitem=eventitem;
        notifyItemRangeChanged(0,eventitem.size());

    }


    @Override
    public ViewHolderExploreList onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.list_exlore_layout,parent,false);
        ViewHolderExploreList viewHolderExploreList=new ViewHolderExploreList(view);
        return viewHolderExploreList;


    }

    @Override
    public void onBindViewHolder(ViewHolderExploreList holder, int position) {
        Item current = eventitem.get(position);
        holder.time.setText(current.time);
        holder.title.setText(current.title);
        holder.venue.setText(current.venue);
        holder.cover.setImageResource(current.image);
    }

    @Override
    public int getItemCount() {

        return 5;
        //return data.getSize();

    }

    static class ViewHolderExploreList extends RecyclerView.ViewHolder{

        private ImageView cover;
        private TextView time;
        private TextView title;
        private TextView venue;



        public ViewHolderExploreList(View itemView)
        {
            super(itemView);
            cover=(ImageView)itemView.findViewById(R.id.CoverView);
            time=(TextView)itemView.findViewById(R.id.event_time);
            title=(TextView)itemView.findViewById(R.id.event_name);
            venue=(TextView)itemView.findViewById(R.id.event_venue);

        }
    }
}
