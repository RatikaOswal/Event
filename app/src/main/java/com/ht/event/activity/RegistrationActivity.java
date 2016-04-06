package com.ht.event.activity;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.ht.event.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.ht.event.application.AppController;
import com.ht.event.model.Event;
import com.ht.event.model.User;
import com.ht.event.utils.Config;
import com.ht.event.utils.EventsPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener {
    private static final int REQUEST_CODE_RESOLVE_ERR = 9001;
    private CallbackManager mcallbackManager;
    private TextView mTextDetail;
    private SignInButton btnSignIn;
    private ImageView imgProfilePic;
    private LoginButton logInButton;
    Uri personPhotoUrl;
    public static final String TAG = "MainActivity";
    public static final int PROFILE_PIC_SIZE = 400;
    private GoogleApiClient mGoogleApiClient;
    private boolean mIntentInProgress;
    private boolean mSignInClicked;
    private ConnectionResult mConnectionResult;
    private Event eventIntentObject;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mcallbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_registration);

//setting the toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.regisbar);
        setSupportActionBar(mToolbar);

//setting the icon
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnSignIn = (SignInButton) findViewById(R.id.google_sign_in);
        if (getIntent().hasExtra(Config.ITEM_INTENT_OBJECT)){
            eventIntentObject = (Event) getIntent().getSerializableExtra(Config.ITEM_INTENT_OBJECT);
        }

        // Button click listeners
        btnSignIn.setOnClickListener(this);

        //setting google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //setting  facebook loginButton
        logInButton = (LoginButton) findViewById(R.id.login_button);
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile", "user_friends"));
        logInButton.setReadPermissions();
        logInButton.registerCallback(mcallbackManager, new FacebookCallback<LoginResult>() {
        Gson gson = new Gson();
            @Override
            public void onSuccess(final LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                try {
                                    String userImage = object.getJSONObject("picture").getJSONObject("data").getString("url");
                                    String name = object.getString("name");
                                    String email = object.getString("email");
                                    User user = new User();
                                    user.setName(name);
                                    user.setEmail(email);
                                    user.setImage(userImage);
                                EventsPreferences.saveUser(RegistrationActivity.this,user);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                System.out.println(object.toString());
                                Log.e("GraphResponse", "-------------" + response.toString());



                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,link,name,email,picture");
                request.setParameters(parameters);
                request.executeAsync();
            }

//                System.out.println("Facebook Login Successful!");
//                System.out.println("Logged in user Details : ");
//                System.out.println("--------------------------");
//                System.out.println("User ID  : " + loginResult.getAccessToken().getUserId());
//                System.out.println("Authentication Token : " + loginResult.getAccessToken().getToken());
//                System.out.println("User Name : " + loginResult.getClass());
//
//                Profile profile = Profile.getCurrentProfile();
//
//                if (profile != null) {
//                    mTextDetail.setText("WELCOME" + profile.getName());
//
//                }


            @Override
            public void onCancel() {
                    Log.d(AppController.TAG, "fb cancel success");

            }

            @Override
            public void onError(FacebookException e) {
            }
        });
    }

    protected void onStart() {
        super.onStart();
//        mGoogleApiClient.connect();
    }

    protected void onStop() {
        super.onStop();
//        if (mGoogleApiClient.isConnected()) {
//            mGoogleApiClient.disconnect();
//        }
    }

    /**
     * Method to resolve any signin errors
     */
    private void resolveSignInError() {
//        System.out.println("mConnectionResult.hasResolution()"+mConnectionResult.hasResolution());
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, REQUEST_CODE_RESOLVE_ERR);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (result.hasResolution()) {
            try {
                result.startResolutionForResult(this, REQUEST_CODE_RESOLVE_ERR);
            } catch (IntentSender.SendIntentException e) {
                mGoogleApiClient.connect();
            }
        }
        // Save the result and resolve the connection failure upon a user click.
        mConnectionResult = result;
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
        //System.out.println("onActivityResult" + requestCode + "resultCode" + resultCode);
        mcallbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_RESOLVE_ERR) {
            if (resultCode != RESULT_OK) {
                mSignInClicked = false;
            }
            else{
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                getProfileInformation(result);
                this.finish();
            }

            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar event clicks here. The action bar will
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


//    @Override
//    public void onConnected(Bundle bundle) {
//        mSignInClicked = false;
//        Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();
//
//        // Get user's information
////        getProfileInformation();
//
//        // Update the UI after signin
//        updateUI(true);
//
//
//    }




    /**
     * Fetching user's information name, email, profile pic
     */
    private void getProfileInformation(GoogleSignInResult result) {
        try {


            if (result.isSuccess()){
                GoogleSignInAccount acct = result.getSignInAccount();
                String personName = acct.getDisplayName();
                personPhotoUrl =  acct.getPhotoUrl();
                String email = acct.getEmail();

                Log.e(TAG, "Name: " + personName + ", plusProfile: "
                        + ", email: " + email
                        + ", Image: " + result.getSignInAccount().getPhotoUrl());

                User user = new User();
                user.setEmail(email);
                user.setName(personName);
                user.setImage(personPhotoUrl + "");
               // System.out.println("USER_IMAGE_USER_AC" + personPhotoUrl);
                EventsPreferences.saveUser(this, user);

                Toast.makeText(this, personName + " is connected.", Toast.LENGTH_LONG)
                        .show();
              Intent intent =new Intent(this,AttendeesInfoActivity.class);
                intent.putExtra(Config.ITEM_INTENT_OBJECT, eventIntentObject);
                startActivity(intent);

            } else {
                Toast.makeText(getApplicationContext(),
                        "Person information is null", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    @Override
//    public void onConnectionSuspended(int arg) {
//        mGoogleApiClient.connect();
//        updateUI(false);
//
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.google_sign_in:
                // SignIn button clicked
                signInWithGplus();
                break;

        }
    }

    private void signInWithGplus() {

         if(!mGoogleApiClient.isConnected()){

            mGoogleApiClient.connect();
        }
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, REQUEST_CODE_RESOLVE_ERR);

        }

    /**
     * Background Async task to load user profile picture from url
     * */
    private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public LoadProfileImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}