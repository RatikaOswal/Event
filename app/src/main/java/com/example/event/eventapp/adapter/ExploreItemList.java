package com.example.event.eventapp.adapter;


import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.event.eventapp.R;
import com.example.event.eventapp.activity.DiscriptionOfItem;
import com.example.event.eventapp.modle.Item;
import java.util.ArrayList;


public class ExploreItemList extends RecyclerView.Adapter<ExploreItemList.ViewHolderExploreList>{

    private LayoutInflater layoutInflater;
    public ArrayList<Item> eventitem ;
    public Context context;
   // public ViewHolderExploreList.ClickListener clickListener;


    public ExploreItemList(Context context, ArrayList<Item>eventitem){

        this.context=context;
        layoutInflater=LayoutInflater.from(context);
        this.eventitem=eventitem;

    }

    public void setEventitem(ArrayList<Item>eventitem){

        notifyItemRangeChanged(0,eventitem.size());

    }


    @Override
    public ViewHolderExploreList onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=layoutInflater.inflate(R.layout.list_exlore_layout, parent, false);
        ViewHolderExploreList viewHolderExploreList;
        viewHolderExploreList = new ViewHolderExploreList(view,context,eventitem);

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

     public static class ViewHolderExploreList extends RecyclerView.ViewHolder implements View.OnClickListener{

         private ImageView cover;
         private TextView time;
         private TextView title;
         private TextView venue;
         private Context context;
         // public ClickListener clickListener;

        ArrayList<Item>eventitem=new ArrayList<Item>();


         public ViewHolderExploreList(View itemView,Context context,ArrayList<Item>eventitem)
         {
             super(itemView);
             this.eventitem=eventitem;
             this.context=context;
             itemView.setOnClickListener(this);
             cover = (ImageView) itemView.findViewById(R.id.CoverView);
             time = (TextView) itemView.findViewById(R.id.event_time);
             title = (TextView) itemView.findViewById(R.id.event_name);
             venue = (TextView) itemView.findViewById(R.id.event_venue);

         }


         @Override
         public void onClick(View v) {

             int position=getAdapterPosition();
             Item item=this.eventitem.get(position);
             Intent intent=new Intent(this.context, DiscriptionOfItem.class);
             intent.putExtra("CoverImg",item.getImage());
             intent.putExtra("Time",item.getTime());
             intent.putExtra("Title",item.getTitle());
             this.context.startActivity(intent);


         }
     }
    }
