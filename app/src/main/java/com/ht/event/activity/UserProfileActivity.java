package com.ht.event.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ht.event.R;
import com.ht.event.adapter.ProfilePagerAdapter;

import com.ht.event.dialog.LogoutMessage;

import com.ht.event.model.Event;
import com.ht.event.model.User;
import com.ht.event.utils.EventsPreferences;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileActivity extends AppCompatActivity  {

    private TextView userName;
    private LogoutMessage logoutMessage;
    private CircleImageView profilePicture;
    private ImageLoader imgLoader;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        logoutMessage = new LogoutMessage(this);

//setting up toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.ProfileTabLayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        viewPager.setAdapter(new ProfilePagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);



        profilePicture = (CircleImageView) findViewById(R.id.circleImageViewProfilePicture);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        imgLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        imgLoader.init(config);

        userName = (TextView) findViewById(R.id.ProfileUserName);
       User user = EventsPreferences.getUser(this);
        String imageUrl = user.getImage();

        if (user.getEmail() != null) {
            userName.setText(user.getName());
            imgLoader.displayImage(imageUrl, profilePicture);


        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_edit_profile:
                Intent intent = new Intent(this, UserProfileEditActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_settings:
                intent = new Intent(this, UserProfileSettingsActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_logout:
                logoutMessage.show(this.getFragmentManager(), "logout");


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }


}


