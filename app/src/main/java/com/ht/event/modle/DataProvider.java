package com.ht.event.modle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by komal on 17-02-2016.
 */
public class DataProvider {


    public static HashMap<String, List<String>> getInfo() {

        HashMap<String, List<String>> FilterDetails = new HashMap<String, List<String>>();
        List<String> Date = new ArrayList<String>();
        Date.add("All dates");
        Date.add("Today");
        Date.add("Tomorrow");
        Date.add("Weekend");
        Date.add("Choose Date");
        List<String> Event_Topics = new ArrayList<String>();


        List<String> Event_Types = new ArrayList<String>();

        FilterDetails.put("Date",Date);
        FilterDetails.put("Event Topics",Event_Topics);
        FilterDetails.put("Event Types",Event_Types);

        return FilterDetails;

    }




}
