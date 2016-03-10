package com.ht.event.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Window;

import com.ht.event.R;

/**
 * Created by RATIKA on 10-Mar-16.
 */
public class MoreInfoMessage extends DialogFragment {
    public Activity c;

    public MoreInfoMessage(Activity c) {
        this.c = c;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        super.onCreateDialog(savedInstanceState);
        Dialog d = new Dialog(c);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.fragment_more_info);

        return d;
    }
}
