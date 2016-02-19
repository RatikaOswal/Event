package com.ht.event.adapter;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.event.eventapp.R;

import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by komal on 17-02-2016.
 */
public class FilterAdapter  extends BaseExpandableListAdapter{
   private Context cxt;
    private HashMap<String,List<String>>Filter_category;
    private List<String>Filter_List;
    private int[] groupStatus;
    Boolean isActive=false;


    public FilterAdapter(Context cxt,HashMap<String,List<String>>Filter_category,List<String>Filter_List){

        this.cxt=cxt;
        this.Filter_category=Filter_category;
        this.Filter_List=Filter_List;
        groupStatus = new int[Filter_List.size()];
        setListEvent();

    }

    private void setListEvent()
    {



       }

        @Override
    public int getGroupCount() {
        return Filter_List.size();
    }

    @Override
    public int getChildrenCount(int arg0) {
        return Filter_category.get(Filter_List.get(arg0)).size();
    }

    @Override
    public Object getGroup(int arg0) {
        return Filter_List.get(arg0);
    }

    @Override
    public Object getChild(int parent, int child) {
        return Filter_category.get(Filter_List.get(parent)).get(child);
    }

    @Override
    public long getGroupId(int arg0) {
        return arg0;
    }

    @Override
    public long getChildId(int parent, int child) {
        return child;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int parent, boolean iaExpanded , View convertview, ViewGroup parentview) {

        String group_title=(String)getGroup(parent);
        if(convertview==null)
        {

            LayoutInflater inflator = (LayoutInflater) cxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview=inflator.inflate(R.layout.parent_layout, parentview, false);
        }
        TextView  parent_textview = (TextView)convertview.findViewById(R.id.parent_txt);
        parent_textview.setTypeface(null, Typeface.BOLD);
        parent_textview.setText(group_title);
        return convertview;
    }

    @Override
    public View getChildView(int parent, int child, boolean LastChild, View convertView, ViewGroup parentView) {

        String child_title=(String) getChild(parent,child);
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater) cxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_layout,parentView,false);
        }

        TextView child_textView=(TextView)convertView.findViewById(R.id.child_txt);
        child_textView.setText(child_title);

        return convertView ;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
