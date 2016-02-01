package com.event.android.eventapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.event.android.eventapp.R;
import com.event.android.eventapp.metadata.Information;

import java.util.Collections;
import java.util.List;

/**
 * Created by jeetu on 16/2/15.
 */
public class VivzAdapter extends RecyclerView.Adapter<VivzAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;

    private ClickListener clickListener;

    List<Information> data= Collections.emptyList();

    //-----------make constructor for layout inflation---
    public VivzAdapter(Context context, List<Information> data) {

        inflater=LayoutInflater.from(context);
        this.data = data;
    }


    //-----delete any item -------------
    public void delete(int position){

        data.remove(position);
        notifyItemRemoved(position);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        //------------root view of list items i.e custom_row.xml ------------
       View view= inflater.inflate(R.layout.custom_row, parent, false);

        Log.d("vivz","onCreateViewHolder called");
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //--------set the data belongs to corresponding row----------
        Information current = data.get(position);

        Log.d("vivz","onBindViewHolder called "+position);

        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);

        //-------set onclick on indivdual items of each list view item--------
       /* holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,"Item clicked at poition:" ,Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    //-------when this method called ---**argument is the fragment object -------
    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);

            title= (TextView) itemView.findViewById(R.id.listText);
            icon= (ImageView) itemView.findViewById(R.id.listIcon);

         //   icon.setOnClickListener(this);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
                Log.d("onclick",""+getPosition());

            //----to avoid null pointer execption , by adapter.setClickListener(this); if this method not called somehow.
            if(clickListener!= null){

                clickListener.itemClicked(v, getPosition());
            }
        //-------start an activity on click of a particular item of listview of Navigation drawer----------------
           // context.startActivity(new Intent(context, SubActivity.class));
            //Toast.makeText(context,"Item clicked at poition:"+getPosition() ,Toast.LENGTH_SHORT).show();

            //delete(getPosition());
        }
    }

    //--------make an interface to do onclick on list items ,So CREATE AN INTERFACE HERE which is used in NavigationDrawerFragment--------
    public interface ClickListener{

        public void itemClicked(View view, int position);

    }

}
