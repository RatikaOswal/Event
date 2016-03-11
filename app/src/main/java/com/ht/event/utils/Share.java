package com.ht.event.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by RATIKA on 11-Mar-16.
 */
public class Share {

    public static void shareItem (Context context,String url) {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, Config.SHARE_DOMAIN + url);
        context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}
