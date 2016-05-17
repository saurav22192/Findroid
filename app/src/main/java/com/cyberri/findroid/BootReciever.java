package com.cyberri.findroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by It's me! on 15-05-2016.
 */
public class BootReciever extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Intent myIntent = new Intent(context, homesplash.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);
    }

}