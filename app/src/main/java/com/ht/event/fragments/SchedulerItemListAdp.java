package com.ht.event.fragments;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ht.event.R;
import com.ht.event.adapter.ExploreItemListAdapter;
import com.ht.event.model.Event;
import com.ht.event.model.Scheduler;
import com.ht.event.model.SchedulerList;

import java.util.ArrayList;
import java.util.List;

public class SchedulerItemListAdp extends RecyclerView.Adapter<SchedulerItemListAdp.ViewHolderExploreList> {

    private ArrayList<Scheduler>scheduleItem ;
    private Context context;
    private Gson gson;

    public SchedulerItemListAdp(Context context, ArrayList<Scheduler>scheduleItem ) {
        this.context = context;
        this.scheduleItem = scheduleItem ;
    }



    @Override
    public ViewHolderExploreList onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.schedule_list_layout, parent, false);
        ViewHolderExploreList viewHolderExploreList;
        viewHolderExploreList = new ViewHolderExploreList(view,context,scheduleItem);
        return viewHolderExploreList;
    }


    @Override
    public void onBindViewHolder(final ViewHolderExploreList holder, int position) {
        Scheduler schedulers = scheduleItem.get(position);
        holder.month.setText(schedulers.month);
        holder.day.setText(schedulers.day);
        holder.date.setText(schedulers.date);
        holder.time.setText(schedulers.time);
        holder.detailing.setText(schedulers.detailing);
    }



    @Override
    public int getItemCount() {
        return this.scheduleItem.size();
    }

    public static class ViewHolderExploreList extends RecyclerView.ViewHolder implements View.OnClickListener{

        private RelativeLayout Schedule_list;
        private TextView month,day,date,time,detailing;
        private Context context;
        ArrayList<Scheduler> scheduleItem = new ArrayList<Scheduler>();

        public ViewHolderExploreList(View itemView ,final Context context, ArrayList<Scheduler> scheduleItem) {
            super(itemView);

            this.scheduleItem = scheduleItem;
            this.context = context;
            Schedule_list = (RelativeLayout) itemView.findViewById(R.id.schedule_list);
            time = (TextView) itemView.findViewById(R.id.time);
            month = (TextView) itemView.findViewById(R.id.month);
            day = (TextView) itemView.findViewById(R.id.day);
            date = (TextView) itemView.findViewById(R.id.date);
            detailing = (TextView) itemView.findViewById(R.id.detailing);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
