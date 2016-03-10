package com.ht.event.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.ht.event.model.User;

public class EventsPreferences {
    public static final String USER_INFO = "userInfo";
    public static final String BOOKMARKED_INFO = "bookmarkedInfo";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String ORGANIZATIONNAME = "orgName" ;
    public static final String PHONENO = "phoneno";
    public static final String ORGWEBSITE = "orgWebsite";
    public static final String BOOKMARKED = "bookmarked";

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
            user.setOrgWebsite(mPref.getString(ORGWEBSITE,null));
            return user;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean saveBookmarked(String[] array, String arrayName, Context context) {

        try{
            SharedPreferences mPrefs = context.getSharedPreferences(BOOKMARKED_INFO, 0);
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putInt(arrayName +"_size", array.length);
            for(int i=0;i<array.length;i++)
                editor.putString(arrayName + "_" + i, array[i]);
            return editor.commit();

        }
        catch (Exception e){
            e.printStackTrace();
        }

            return true;
    }


       public String[] loadArray(String arrayName,Context context){
           SharedPreferences mPrefs = context.getSharedPreferences(BOOKMARKED_INFO, 0);
           int size = mPrefs.getInt(arrayName + "_size", 0);
           String array[] = new String[size];
           for(int i=0;i<size;i++)
               array[i] = mPrefs.getString(arrayName + "_" + i, null);
           return array;

       }

    }
