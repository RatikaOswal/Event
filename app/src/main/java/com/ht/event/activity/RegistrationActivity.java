package com.ht.event.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.event.eventapp.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class RegistrationActivity extends AppCompatActivity  {

    private CallbackManager mcallbackManager;
    private TextView mTextDetail;

    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            FacebookSdk.sdkInitialize(getApplicationContext());
            mcallbackManager = CallbackManager.Factory.create();
            setContentView(R.layout.activity_registration);


//setting the toolbar
            Toolbar mToolbar = (Toolbar) findViewById(R.id.regisbar);
            setSupportActionBar(mToolbar);
            final ActionBar ab = getSupportActionBar();
//setting the icon
            getSupportActionBar().setHomeButtonEnabled(true);
            ab.setHomeAsUpIndicator(R.drawable.back);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            mTextDetail=(TextView)findViewById(R.id.userinfo);

            //setting loginbutton

            LoginButton logInButton=(LoginButton) findViewById(R.id.login_button);
            logInButton.setReadPermissions("user_friends");
            logInButton.registerCallback(mcallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                System.out.println("Facebook Login Successful!");
                System.out.println("Logged in user Details : ");
                System.out.println("--------------------------");
                System.out.println("User ID  : " + loginResult.getAccessToken().getUserId());
                System.out.println("Authentication Token : " + loginResult.getAccessToken().getToken());
                System.out.println("User Name : "+loginResult.getClass());

                Profile profile=Profile.getCurrentProfile();

                if (profile != null)
                {
                    mTextDetail.setText("WELCOME" +profile.getName());

                }
            }

            @Override
            public void onCancel() {
                System.out.println("Facebook Login failed!!");

            }

            @Override
            public void onError(FacebookException e) {
                System.out.println("Facebook Login failed!!");
            }
        });


        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_registration, menu);
            return true;
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mcallbackManager.onActivityResult(requestCode, resultCode, data);

//        twitterLogin.onActivityResult(requestCode, resultCode, data);
    }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            int id = item.getItemId();

            if (id == android.R.id.home) {
                onBackPressed();
                return true;
            }

            return super.onOptionsItemSelected(item);
        }


    }
