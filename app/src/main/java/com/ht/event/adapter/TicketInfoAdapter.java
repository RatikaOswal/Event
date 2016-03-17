package com.ht.event.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ht.event.fragments.InfoFragment;
import com.ht.event.fragments.TicketDetailFragment;

/**
 * Created by RATIKA on 16-Mar-16.
 */
public class TicketInfoAdapter extends FragmentPagerAdapter{

    public TicketInfoAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new TicketDetailFragment();

            case 1:
               return new InfoFragment();
        }
        return null;
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
                return "INFO";

        }
        return null;
    }
}
