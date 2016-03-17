package com.ht.event.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.ht.event.R;

public class DiscardMessage extends DialogFragment implements
        android.view.View.OnClickListener {
    private Activity c;
    private TextView yes,no;

    public DiscardMessage(Activity c) {
        this.c = c;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        super.onCreateDialog(savedInstanceState);
        Dialog d = new Dialog(c);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.fragment_discard_message);
        yes =(TextView)d.findViewById(R.id.yes);
        no =(TextView)d.findViewById(R.id.no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

        return d ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yes:
                c.onBackPressed();
                break;
            case R.id.no:
                getDialog().dismiss();
                break;
            default:
                break;


        }

    }
}
