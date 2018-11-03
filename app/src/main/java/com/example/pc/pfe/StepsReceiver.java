package com.example.pc.pfe;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

/**
 * Created by pc on 30/09/2018.
 */

public class StepsReceiver extends BroadcastReceiver

{

    private  static int MID= 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub


        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(
                context).setSmallIcon(R.drawable.ic_suivi_sant)
                .setContentTitle("Vous avez marchez 70 pas pendant 10mn!")
                .setContentText("Félicitations, vous allez brûlé 20 calories").setSound(alarmSound)
                .setAutoCancel(true).setWhen(when)
                .setContentIntent(pendingIntent);
        //.setVibrate(DEFAULT_VIBRATE);
        notificationManager.notify(MID, mNotifyBuilder.build());
        MID++;

    }
}
