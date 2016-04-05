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
    public static final String REGISTERED_INFO = "registeredInfo";
    public static final String TICKETID_INFO = "ticketIdInfo";
    public static final String REGISTERED_LIST = "registeredList";
    public static final String TICKETID_LIST = "ticketIdList";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String IMAGE = "image";
    public static final String ORGANIZATIONNAME = "orgName";
    public static final String PHONENO = "phoneno";
    public static final String ORGWEBSITE = "orgWebsite";
    public static final String BOOKMARKED = "bookmarked";

    public static void saveUser(Context context, User user) {
        try {


            SharedPreferences mPrefs = context.getSharedPreferences(USER_INFO, 0);

            SharedPreferences.Editor editor = mPrefs.edit();
            // conversion gson to json
            Gson gson = new Gson();

            String name = user.getName();
            String email = user.getEmail();
            String phoneno = user.getPhoneNo();
            String orgName = user.getOrganisation();
            String orgWebsite = user.getOrgWebsite();
            String image = user.getImage();


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
            if (image != null)
            {
                editor.putString(IMAGE, image);
            }
            editor.commit();

        } catch (Exception e) {
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
            user.setImage(mPref.getString(IMAGE,null));
            return user;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static void saveBookmarked(Context context, Event event) {

        String dataListInStr = getBookmarked(context);
        Gson gson = new Gson();

        if (dataListInStr == null) {
            ArrayList<Event> bookmarkedItemArrayList = new ArrayList<Event>();
            bookmarkedItemArrayList.add(event);
            EventList eventList = new EventList();
            eventList.setData(bookmarkedItemArrayList);
            String itemListInString = gson.toJson(eventList);
            SharedPreferences mPrefs = context.getSharedPreferences(BOOKMARKED_INFO, 0);
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString(BOOKMARKED_LIST, itemListInString);
            editor.commit();
        } else {
            EventList eventList = gson.fromJson(dataListInStr, EventList.class);
            ArrayList<Event> bookmarkedItemArrayList = eventList.getData();
            bookmarkedItemArrayList.add(event);
            eventList.setData(bookmarkedItemArrayList);

            String itemListInString = gson.toJson(eventList);
            SharedPreferences mPrefs = context.getSharedPreferences(BOOKMARKED_INFO, 0);
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString(BOOKMARKED_LIST, itemListInString);
            editor.commit();


        }
    }


    public static String getBookmarked(Context context) {


        SharedPreferences mPrefs = context.getSharedPreferences(BOOKMARKED_INFO, 0);
        return mPrefs.getString(BOOKMARKED_LIST, null);

    }


    public static void removeBookmarked(Context context, Event event) {
        String dataListInStr = getBookmarked(context);
        Gson gson = new Gson();
        EventList eventList = gson.fromJson(dataListInStr, EventList.class);
        ArrayList<Event> bookmarkedItemArrayList = eventList.getData();
        bookmarkedItemArrayList.remove(event);

        eventList.setData(bookmarkedItemArrayList);
        String itemListInString = gson.toJson(eventList);
        SharedPreferences mPrefs = context.getSharedPreferences(BOOKMARKED_INFO, 0);
        SharedPreferences.Editor editor = mPrefs.edit();
        if (bookmarkedItemArrayList.size() == 0) {
            editor.putString(BOOKMARKED_LIST, null);
        } else {
            editor.putString(BOOKMARKED_LIST, itemListInString);
        }
        editor.commit();
    }

    public static void saveRegistered(Context context, Event event) {
        String saveListInStr = getRegistered(context);
        Gson gson = new Gson();
        if (saveListInStr == null) {
            ArrayList<Event> registeredItemArrayList = new ArrayList<Event>();
            registeredItemArrayList.add(event);
            EventList registeredList = new EventList();
            registeredList.setData(registeredItemArrayList);
            String itemListInString = gson.toJson(registeredList);
            SharedPreferences mPrefs = context.getSharedPreferences(REGISTERED_INFO, 0);
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString(REGISTERED_LIST, itemListInString);
            editor.commit();
        } else {
            EventList registeredList = gson.fromJson(saveListInStr, EventList.class);
            ArrayList<Event> registeredItemArrayList = registeredList.getData();
            registeredItemArrayList.add(event);
            registeredList.setData(registeredItemArrayList);

            String itemListInString = gson.toJson(registeredList);
            SharedPreferences mPrefs = context.getSharedPreferences(REGISTERED_INFO, 0);
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString(REGISTERED_LIST, itemListInString);
            editor.commit();


        }
    }

    public static String getRegistered(Context context) {

        SharedPreferences mPrefs = context.getSharedPreferences(REGISTERED_INFO, 0);
        return mPrefs.getString(REGISTERED_LIST, null);

    }

    public static void setSaveTicketId(Context context, Event event) {

        String TicketListInStr = getSaveTicketId(context);
        Gson gson = new Gson();

    }

    private static String getSaveTicketId(Context context) {

        SharedPreferences mPrefs = context.getSharedPreferences(TICKETID_INFO, 0);
        return mPrefs.getString(TICKETID_LIST, null);

    }
}
