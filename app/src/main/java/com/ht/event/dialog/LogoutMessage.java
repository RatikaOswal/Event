package com.ht.event.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.ht.event.R;

public class LogoutMessage extends DialogFragment implements
        android.view.View.OnClickListener {
    public Activity c;
    public TextView cancel, logout;
    private GoogleApiClient mGoogleApiClient;

    public LogoutMessage(Activity c) {
        this.c = c;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        super.onCreateDialog(savedInstanceState);
        Dialog d = new Dialog(c);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.fragment_logout_message);
        cancel = (TextView) d.findViewById(R.id.cancel);
        logout = (TextView) d.findViewById(R.id.logout);
        logout.setOnClickListener(this);
        cancel.setOnClickListener(this);

        //setting google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

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
                break;


        }
    }
    private void signOut(){
        if(mGoogleApiClient.isConnected()){
            mGoogleApiClient.disconnect();
        }

    }
}
