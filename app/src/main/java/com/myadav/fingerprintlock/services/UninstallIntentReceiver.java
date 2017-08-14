package com.myadav.fingerprintlock.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by lenovo on 14-08-2017.Mohit yadav
 */
public class UninstallIntentReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // fetching package names from extras
        context.startService(new Intent(context, AppCheckServices.class));

        String[] packageNames = intent.getStringArrayExtra("android.intent.extra.PACKAGES");

        if(packageNames!=null){
//            Toast.makeText(context, "svf", Toast.LENGTH_SHORT).show();
            System.out.print("vdfvkkkkkkkkkk" +
                    "kkkkkkkkkkkkkkkkkkk" +
                    "k" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "kkkkkkkkkkkkkkkkkkkkkk" +
                    "k");
            for(String packageName: packageNames){
                if(packageName!=null && packageName.equals("com.spotify.music")){

                    // User has selected our application under the Manage Apps settings
                    // now initiating background thread to watch for activity
                    new ListenActivities(context).start();

                }
            }
        }
    }

}
