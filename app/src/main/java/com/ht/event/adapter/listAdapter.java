package com.ht.event.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.event.eventapp.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by hp on 2/23/2016.
 */
public class listAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private static final int CHILD_TYPE_1 = 0;
    private static final int CHILD_TYPE_2 = 1;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;


    public listAdapter(Context context, List<String> listDataHeader,
                       HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition);

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.parent_layout, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.parent_txt);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public int getChildTypeCount() {
        return 2;
    }

   /* @Override
    public int getChildType(int groupPosition, int childPosition) {
        ;
        if (groupPosition == 0) {
            return 1;
        } else {
            return 2;
        }

    }*/

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String incoming_text = (String) getChild(groupPosition, childPosition);
        int childType = getChildType(groupPosition,childPosition);
        LayoutInflater inflater = (LayoutInflater) _context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null || (int) convertView.getTag()!= childType) {


            switch (childType)

            {

                case CHILD_TYPE_1:
                    convertView = inflater.inflate(R.layout.child_layout, null);
                    convertView.setTag(childType);


                    break;

                case CHILD_TYPE_2:

                    convertView = inflater.inflate(R.layout.child_layout1, null);
                    convertView.setTag(childType);

                    break;

            }
        } else {
        }
        switch (childType) {

            case CHILD_TYPE_1:
                TextView txtListChild = (TextView) convertView.findViewById(R.id.child_txt);
                final RadioGroup radioGroup =(RadioGroup)convertView.findViewById(R.id.radiogroup);
                //final RadioButton radioButton =(RadioButton)convertView.findViewById(R.id.radiobutton);



                txtListChild.setText(incoming_text);

                break;

            case CHILD_TYPE_2:
                CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox1);
                TextView txtListChild2 = (TextView) convertView
                        .findViewById(R.id.child1_txt);





                txtListChild2.setText(incoming_text);



                break;

        }


        return convertView;
    }


//        if (convertView == null) {
//            LayoutInflater infalInflater = (LayoutInflater) this._context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = infalInflater.inflate(R.layout.child_layout, null);
//        }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        if(groupPosition == 0) {
            return CHILD_TYPE_1;
        }
        return CHILD_TYPE_2;

    }
}
