package com.ht.event.activity;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.ht.event.R;
import com.ht.event.model.User;
import com.ht.event.utils.EventsPreferences;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class UserProfileEditActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name,phoneNo,orgName,orgWebsite;
    private TextView email,save;
    private static int RESULT_LOAD_IMG = 1;
    private String imgDecodableString;
    private User user;
    private ImageView profilePic,editPic ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.profileEditToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (EditText)findViewById(R.id.name);
        email = (TextView) findViewById(R.id.emailAddress);
        phoneNo = (EditText) findViewById(R.id.phoneNo);
        orgName = (EditText) findViewById(R.id.orgName);
        orgWebsite = (EditText)findViewById(R.id.orgWebsite);
        save = (TextView)findViewById(R.id.save);
        profilePic = (ImageView)findViewById(R.id.circleImageViewProfilePicture);
        editPic = (ImageView)findViewById(R.id.picEdit);
        editPic.setOnClickListener(this);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader imgLoader = ImageLoader.getInstance();
        imgLoader.init(config);

        user = EventsPreferences.getUser(UserProfileEditActivity.this);

        if (user.getEmail() != null) {
        email.setText(user.getEmail());
        name.setText(user.getName());
        phoneNo.setText(user.getPhoneNo());
        orgName.setText(user.getOrganisation());
        orgWebsite.setText(user.getOrgWebsite());
            String imageUrl = user.getImage();
            imgLoader.displayImage(imageUrl, profilePic);

        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                    name.setText(name.getText().toString());
//                    phoneNo.setText(phoneNo.getText().toString());
//                    orgName.setText(orgName.getText().toString());
//                    orgWebsite.setText(orgWebsite.getText().toString());
                user.setPhoneNo(phoneNo.getText().toString());
                user.setName(name.getText().toString());
                user.setOrganisation(orgName.getText().toString());
                user.setOrgWebsite(orgWebsite.getText().toString());
                EventsPreferences.saveUser(UserProfileEditActivity.this, user);
                Toast.makeText(UserProfileEditActivity.this, "SAVED", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                user.setImage(imgDecodableString);
                EventsPreferences.saveUser(UserProfileEditActivity.this, user);
                cursor.close();

                // Set the Image in ImageView after decoding the String
                profilePic.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));


            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();

    }
        return true;
}

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id)
        {
            case R.id.picEdit :
//  intent to Open Image applications like Gallery, Google Photos
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
// Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
                break;

        }
    }
    }

