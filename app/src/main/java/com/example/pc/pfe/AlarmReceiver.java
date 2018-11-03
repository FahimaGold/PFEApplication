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
 * Created by pc on 17/09/2018.
 */

public class AlarmReceiver extends BroadcastReceiver {

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
                context).setSmallIcon(R.drawable.ic_heartbeat)
                .setContentTitle("Suivez votre diabète")
                .setContentText("Avez vous respecté votre plan médical et votre régime aujourd'hui? Vous pouvez consulter votre régime et quelques recettes proposées").setSound(alarmSound)
                .setAutoCancel(true).setWhen(when)
                .setContentIntent(pendingIntent);
                //.setVibrate(DEFAULT_VIBRATE);
        notificationManager.notify(MID, mNotifyBuilder.build());
        MID++;

    }
}
