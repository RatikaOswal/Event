package com.ht.event.activity;


import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.event.eventapp.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ht.event.adapter.FilterAdapter;
import com.ht.event.modle.Category;
import com.ht.event.modle.DataProvider;
import com.ht.event.modle.SubCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilterActivity extends AppCompatActivity
{

    HashMap<String, List<String>> Filter_category;
    List<String> Filter_list;
    ExpandableListView Exp_list;
    FilterAdapter adapter;
    ArrayList<Category> category_array = new ArrayList<Category>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);


        // Setting up Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setting up backButton
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

        Exp_list = (ExpandableListView) findViewById(R.id.exp_list);
        Exp_list.setIndicatorBounds(width - GetPixelFromDips(50), width - GetPixelFromDips(10));

        Filter_category = DataProvider.getInfo();
        Filter_list = new ArrayList<String>(Filter_category.keySet());
        Exp_list.setGroupIndicator(null);
        adapter = new FilterAdapter(this, Filter_category, Filter_list);
        Exp_list.setAdapter(adapter);


        Exp_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
            {


                return false;
            }


            public boolean onOptionsItemSelected(MenuItem item)
            {

                int id = item.getItemId();

                if (id == R.id.home)
                {
                    onBackPressed();
                    return true;
                }
                return false;
            }

            private int GetPixelFromDips(float pixels)
            {
                final float scale = getResources().getDisplayMetrics().density;
                return (int) (pixels * scale + 0.5f);
            }
        });
    }

    private int GetPixelFromDips(int i)
    {
        return 0;
    }
}