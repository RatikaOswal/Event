package com.event.android.eventapp.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.event.android.eventapp.R;
import com.event.android.eventapp.adapters.VivzAdapter;
import com.event.android.eventapp.metadata.Information;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements VivzAdapter.ClickListener {
    private static final String TAG = NavigationDrawerFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    CircleImageView circleImageViewProfile;
    TextView textViewUserName;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    public static final String PREF_FILE_NAME = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private VivzAdapter adapter;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;

    private View containerView;


    private static String[] Signed_data = { "Explore",
             "My Schedule ", "Map","Settings","About"};

   public static int [] Signed_images={R.drawable.support_icon,R.drawable.rate_icon,R.drawable.invite_friend_icon,R.drawable.condtion_icon,R.drawable.about_icon};

    public NavigationDrawerFragment() {
        // Required empty public constructor
       // Log.d(TAG, "Login status" + Constants.isLogin);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
//        Toast.makeText(getActivity(),"onCreateView works",Toast.LENGTH_SHORT).show();
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);

        circleImageViewProfile = (CircleImageView) layout.findViewById(R.id.circleImageViewProfile);
        textViewUserName = (TextView) layout.findViewById(R.id.textViewUserName);


        //-------
        adapter=new VivzAdapter(getActivity(), getData());
        adapter.setClickListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }

    public static List<Information> getData(){

        List<Information> data = new ArrayList<>();

            for (int i = 0; i< Signed_data.length;i++){
                Information current = new Information();
                current.iconId=Signed_images[i];
                current.title=Signed_data[i];

                data.add(current);
            }

        //for (int i = 0; i<titles.length && i<icons.length;i++){
        return data;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar){

        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //--------user just open the navigationDrawerFragment
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }
                //------redraw action bar again------------------------------
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                //------redraw action bar again------------------------------
                getActivity().invalidateOptionsMenu();
            }

            //--------fading of toolbar------------------------------
          /*  @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
*/
        };

        //------handling of very 1st time user open the drawerview
        /*if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(containerView);
        }*/

        //---------listner -----------------------------------
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //------Showing hamburger icon on actionbar -------------------------
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public void saveToPreferences(Context context, String preferenceName, String preferenceValue){

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor=sharedPreferences.edit();
        editor.putString(preferenceValue, preferenceValue);
        editor.commit();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }


    @Override
    public void itemClicked(View view, int position) {

        if(true){

            switch (position){
                case 0:{
                    explore explores = new explore();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.explore,explores);
                    fragmentTransaction.commit();

                }
                case 1:{

                    My_schedule myschedule = new My_schedule();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.myschedule, myschedule);
                    fragmentTransaction.commit();
                    break;
                }
                case 2:{
                    Search search = new Search();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.search,search);
                    fragmentTransaction.commit();
                    break;
                }
                case 3:{
                    Settings settings = new Settings();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.settings,settings);
                    fragmentTransaction.commit();
                    break;
                }
                case 4:{
                    About about = new About();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.about,about);
                    fragmentTransaction.commit();
                    break;
                }

            }

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //+++++++++++++++++++++++++++ set profile pic +++++++++++++++++++++++++++++++

        textViewUserName.setText("UserName");
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //    Toast.makeText(getActivity(),"Onstart works"+"URL---->"+url,Toast.LENGTH_SHORT).show();
    }
}
