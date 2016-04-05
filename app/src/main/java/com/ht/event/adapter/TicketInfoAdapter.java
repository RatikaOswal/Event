package com.ht.event.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ht.event.fragments.InfoFragment;
import com.ht.event.fragments.ScannerFragment;
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

            case 2:
                return new ScannerFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
       return 3;
    }

    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:
                return "TICKETS";

            case 1:
                return "INFO";

            case 2:
                return "SCANNER";

        }
        return null;
    }
}
