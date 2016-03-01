package com.ht.event.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.ht.event.activity.UserProfileActivity;
import com.ht.event.fragments.SavedFragment;
import com.ht.event.fragments.TicketsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 3/1/2016.
 */
public class ProfilePagerAdapter extends FragmentPagerAdapter {


    public ProfilePagerAdapter(FragmentManager fm) {
        super(fm);
    }

   /* public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    } */

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new TicketsFragment();

            case 1:
                return new SavedFragment();
        }
        return  null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:
                return "TICKETS";

            case 1:
                return "SAVED";

        }
        return null;
    }
}
