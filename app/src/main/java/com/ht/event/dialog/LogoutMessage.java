package com.ht.event.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.ht.event.R;
import com.ht.event.activity.MainActivity;
import com.ht.event.activity.RegistrationActivity;
import com.ht.event.model.Event;
import com.ht.event.utils.Config;
import com.ht.event.utils.EventsPreferences;

public class LogoutMessage extends DialogFragment implements
        android.view.View.OnClickListener {
    private Activity c;
    private TextView cancel, logout;
    private Event event;


    public LogoutMessage(Activity c) {
        this.c = c;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        super.onCreateDialog(savedInstanceState);
        //setting google
        event = (Event) getActivity().getIntent().getSerializableExtra(Config.ITEM_INTENT_OBJECT);
       // RegistrationActivity.mGoogleApiClient.isConnected();
        Dialog d = new Dialog(c);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.fragment_logout_message);
        cancel = (TextView) d.findViewById(R.id.cancel);
        logout = (TextView) d.findViewById(R.id.logout);
        logout.setOnClickListener(this);
        cancel.setOnClickListener(this);





        return d;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.cancel:
                getDialog().dismiss();
                break;

            case R.id.logout:
                signOut();
                Toast.makeText(LogoutMessage.this.getActivity(), "Successfully Logout ", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
                Intent intent = new Intent(LogoutMessage.this.getActivity(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(Config.ITEM_INTENT_OBJECT, event);
                startActivity(intent);
                break;




        }
    }

    private void signOut() {
        if (RegistrationActivity.mGoogleApiClient.isConnected()) {
            Auth.GoogleSignInApi.signOut(RegistrationActivity.mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {

                        }
                    });

        }else
        {
            LoginManager.getInstance().logOut();

        }
        EventsPreferences.deleteUserInfo(LogoutMessage.this.getActivity());
        EventsPreferences.delTickets(LogoutMessage.this.getActivity());
    }

}
