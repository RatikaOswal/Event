package com.ht.event.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ht.event.R;
import com.ht.event.model.Event;
import com.ht.event.model.Session;

import java.util.ArrayList;

public class SessionListAdapter extends RecyclerView.Adapter<SessionListAdapter.ViewHolderSessionList> {

    private LayoutInflater layoutInflater;
    public ArrayList<Session> session;
    public Context context;

    public SessionListAdapter(Context context, ArrayList<Session> session) {
        System.out.print("sesss1");
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.session = session;
    }


    @Override
    public ViewHolderSessionList onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.session_list_layout, parent,false);
        System.out.print("sesss2"+view);
        ViewHolderSessionList viewHolderSessionList;
        viewHolderSessionList = new ViewHolderSessionList(view);
        return viewHolderSessionList;
    }


    @Override
    public void onBindViewHolder(final ViewHolderSessionList holder, int position) {

        final Session current = session.get(position);
        System.out.println(session.size());
        holder.bind(current);
    }

    @Override
    public int getItemCount() {

        return session.size();
        //back session.getSize();

    }

    public static class ViewHolderSessionList extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title,price;
        public TextView moreInfo;
        public Context context;
        public CheckBox checkBox;
        ArrayList<Session> session=new ArrayList<Session>();

        public ViewHolderSessionList(View itemView) {
            super(itemView);
            moreInfo= (TextView)itemView.findViewById(R.id.moreInfo);
            title = (TextView)itemView.findViewById(R.id.sessionName);
            price = (TextView)itemView.findViewById(R.id.sessionPrice);
            checkBox = (CheckBox)itemView.findViewById(R.id.checkBox);
        }

        @Override
        public void onClick(View v) {

        }
        public void bind(Session session){
            System.out.println(session.getTitle() + "sesss");
            title.setText(session.getTitle());
            price.setText(session.getPrice());
        }
    }


}

