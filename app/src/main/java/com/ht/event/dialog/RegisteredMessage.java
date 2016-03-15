package com.ht.event.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.ht.event.R;

/**
 * Created by RATIKA on 15-Mar-16.
 */
public class RegisteredMessage extends DialogFragment implements
        android.view.View.OnClickListener {
    public Activity c;
    public TextView viewTicket;

    public RegisteredMessage(Activity c) {
        this.c = c;
    }
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        super.onCreateDialog(savedInstanceState);
        Dialog d = new Dialog(c);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.fragment_registered_message);
        viewTicket = (TextView) d.findViewById(R.id.viewTicket);
        viewTicket.setOnClickListener(this);

        return d;
    }
    @Override
    public void onClick(View v) {


    }

}
