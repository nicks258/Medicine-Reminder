package com.sumit.medicinereminder.Util;

/**
 * Created by Sumit on 13-Jul-17.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.view.WindowManager.LayoutParams;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.sumit.medicinereminder.Activity.AlertActivity;
import com.sumit.medicinereminder.Activity.MainActivity;
import com.sumit.medicinereminder.R;


/**
 * Utilized the link below as a reference guide:
 * http://wptrafficanalyzer.in/blog/setting-up-alarm-using-alarmmanager-and-waking-up-screen-and-unlocking-keypad-on-alarm-goes-off-in-android/
 *
 * This is a dialog box that AlertActivity called when it is triggered.
 * It contains three buttons to let the user respond to an alarm.
 */

public class AlertAlarm extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Logger.addLogAdapter(new AndroidLogAdapter());
        Intent intent = new Intent(getActivity(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        final String pill_name = getActivity().getIntent().getStringExtra("pill_name");
        NotificationCompat.Builder b = new NotificationCompat.Builder(getActivity());

        b.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.logo)
                .setTicker("Sumit")
                .setContentTitle("Medicine Reminder")
                .setContentText("Take your "+ pill_name + " Right Now")
                .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent)
                .setContentInfo("sumit");


        NotificationManager notificationManager = (NotificationManager) getActivity() .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, b.build());
        Logger.i("Alarm Created");
        /** Turn Screen On and Unlock the keypad when this alert dialog is displayed */
        getActivity().getWindow().addFlags(LayoutParams.FLAG_TURN_SCREEN_ON | LayoutParams.FLAG_DISMISS_KEYGUARD);

        /** Creating a alert dialog builder */
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        /** Setting title for the alert dialog */
        builder.setTitle("Medicine Reminder");

        /** Making it so notification can only go away by pressing the buttons */
        setCancelable(false);



        builder.setMessage("Did you take your "+ pill_name + " ?");

        builder.setPositiveButton("I took it", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlertActivity act = (AlertActivity)getActivity();
                Logger.addLogAdapter(new AndroidLogAdapter());

                act.doPositiveClick(pill_name);
                getActivity().finish();
            }
        });

        builder.setNeutralButton("Snooze", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /** Exit application on click OK */
                AlertActivity act = (AlertActivity)getActivity();
                act.doNeutralClick(pill_name);
                getActivity().finish();
            }
        });

        builder.setNegativeButton("I won't take", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /** Exit application on click OK */
                AlertActivity act = (AlertActivity)getActivity();
                act.doNegativeClick(pill_name);
                getActivity().finish();
            }
        });

        return builder.create();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().finish();
    }
}