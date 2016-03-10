package com.ht.event.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.ht.event.model.Event;
import com.ht.event.model.EventList;
import com.ht.event.model.User;

import java.util.ArrayList;

public class EventsPreferences {
    public static final String USER_INFO = "userInfo";
    public static final String BOOKMARKED_INFO = "bookmarkedInfo";
    public static final String BOOKMARKED_LIST = "bookmarkedList";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String ORGANIZATIONNAME = "orgName" ;
    public static final String PHONENO = "phoneno";
    public static final String ORGWEBSITE = "orgWebsite";

    public static void saveUser(Context context, User user) {
        try {

            SharedPreferences mPrefs = context.getSharedPreferences(USER_INFO, 0);

            SharedPreferences.Editor editor = mPrefs.edit();
            // conversion gson to json
            Gson gson = new Gson();
            String userStr = gson.toJson(user);

            String name = user.getName();
            String email = user.getEmail();
            String phoneno = user.getPhoneNo();
            String orgName = user.getOrganisation();
            String orgWebsite = user.getOrgWebsite();


            if (name != null) {
                editor.putString(NAME, name);
            }

            if (email != null) {
                editor.putString(EMAIL, email);
            }
            if (phoneno != null) {
                editor.putString(PHONENO, phoneno);
            }

            if (orgWebsite != null) {
                editor.putString(ORGWEBSITE, orgWebsite);
            }
            if (orgName != null) {
                editor.putString(ORGANIZATIONNAME, orgName);
            }
            editor.commit();

        }

        catch(Exception e){
            e.printStackTrace();


        }
    }

    public static User getUser(Context context) {

        try {
            User user = new User();
            SharedPreferences mPref = context.getSharedPreferences(USER_INFO, 0);
            user.setEmail(mPref.getString(EMAIL, null));
            user.setName(mPref.getString(NAME, null));
            user.setPhoneNo(mPref.getString(PHONENO, null));
            user.setOrganisation(mPref.getString(ORGANIZATIONNAME, null));
            user.setOrgWebsite(mPref.getString(ORGWEBSITE, null));
            return user;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }




    public static void saveBookmarked(Context context,Event event) {

        String dataListInStr = getBookmarked(context);
        Gson gson = new Gson();

        if(dataListInStr == null){
            ArrayList<Event> bookmarkedItemArrayList = new ArrayList<Event>();
            bookmarkedItemArrayList.add(event);
            EventList eventList = new EventList();
            eventList.setData(bookmarkedItemArrayList);
            String itemListInString = gson.toJson(eventList);
            SharedPreferences mPrefs = context.getSharedPreferences(BOOKMARKED_INFO, 0);
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString(BOOKMARKED_LIST,itemListInString);
            editor.commit();
        }
        else {
            EventList itemList = gson.fromJson(dataListInStr,EventList.class);
            ArrayList<Event> itemArrayList = itemList.getData();
            itemArrayList.add(event);
            itemList.setData(itemArrayList);

            String itemListInString = gson.toJson(itemList);
            SharedPreferences mPrefs = context.getSharedPreferences(BOOKMARKED_INFO, 0);
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString(BOOKMARKED_LIST,itemListInString);
            editor.commit();

        }


    }



    public static String getBookmarked(Context context) {


        SharedPreferences mPrefs = context.getSharedPreferences(BOOKMARKED_INFO,0);
        return mPrefs.getString(BOOKMARKED_LIST,null);

    }

}
