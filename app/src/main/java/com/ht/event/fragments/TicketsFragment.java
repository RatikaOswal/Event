package com.ht.event.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ht.event.Main;
import com.ht.event.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TicketsFragment extends Fragment implements View.OnClickListener{
    private RecyclerView mListView;
    private RelativeLayout relativeLayout;
    private View view;
    private TextView discover;


    public TicketsFragment() {
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
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tickets, container, false);
        mListView = (RecyclerView)view.findViewById(R.id.saved_ticket);
        relativeLayout = (RelativeLayout)view.findViewById(R.id.saveInfo);
        discover = (TextView)view.findViewById(R.id.discover);
        discover.setOnClickListener(this);
        display();
     return view ;
    }

     public  void display(){
         relativeLayout.setVisibility(View.VISIBLE);
         mListView.setVisibility(View.GONE);

     }
    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id)
        {
            case R.id.discover :
                Intent intent =new Intent(getActivity(), Main.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);


                break;

        }
    }
}
