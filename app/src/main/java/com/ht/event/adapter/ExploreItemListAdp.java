package com.ht.event.adapter;


import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.event.eventapp.R;
import com.ht.event.activity.DiscriptionItemListActivity;
import com.ht.event.modle.Item;
import java.util.ArrayList;


public class ExploreItemListAdp extends RecyclerView.Adapter<ExploreItemListAdp.ViewHolderExploreList>{

    private LayoutInflater layoutInflater;
    public ArrayList<Item> eventitem ;
    public Context context;
   // public ViewHolderExploreList.ClickListener clickListener;


    public ExploreItemListAdp(Context context, ArrayList<Item> eventitem){

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
    public void onBindViewHolder(final ViewHolderExploreList holder, int position) {
        final Item current = eventitem.get(position);
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

                } else {
                    holder.star.setImageResource(R.drawable.ic_sstar);
                    current.setIs_bookmarked(false);
                }

            }

        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                sharingIntent.putExtra(Intent.EXTRA_TITLE,"Check Out: ");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Date: ");
                sharingIntent.putExtra(Intent.EXTRA_TEXT,"Venue: ");
                context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });


    }

    @Override
    public int getItemCount() {

        return 5;
        //back data.getSize();

    }

     public static class ViewHolderExploreList extends RecyclerView.ViewHolder implements View.OnClickListener{

         private ImageView cover,star,share;
         private TextView time;
         private TextView title,tag2,price;
         private TextView venue,tag1;
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
             tag1 =(TextView) itemView.findViewById(R.id.tag1);
             tag2 =(TextView) itemView.findViewById(R.id.tag2);
             star =(ImageView)itemView.findViewById(R.id.bookmark);
             share=(ImageView)itemView.findViewById(R.id.share);
             price=(TextView)itemView.findViewById(R.id.price);

         }



         @Override
         public void onClick(View v) {

             int position=getAdapterPosition();
             Item item=this.eventitem.get(position);
             Intent intent=new Intent(this.context, DiscriptionItemListActivity.class);
             intent.putExtra("CoverImg",item.getImage());
             intent.putExtra("Time",item.getTime());
             intent.putExtra("Title",item.getTitle());
             intent.putExtra("Price",item.getPrice());
             this.context.startActivity(intent);


         }
     }
    }