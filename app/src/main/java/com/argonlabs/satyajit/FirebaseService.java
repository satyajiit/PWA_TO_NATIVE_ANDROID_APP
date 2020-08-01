package com.argonlabs.satyajit;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class FirebaseService extends FirebaseMessagingService {

    String CHANNEL_NAME = "PUSH NOTIFICATIONS";
    String CHANNEL_ID = "444";

    @Override
    public void onNewToken(String s) {

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        super.onMessageReceived(remoteMessage);


        Map<String, String> data = remoteMessage.getData();
        String body = data.get("body");
        String title = data.get("title");
        Intent intent;
        intent = new Intent(getApplicationContext(), com.google.androidbrowserhelper.trusted.LauncherActivity.class);

        //Check if contains url , then it will redirect to link
        if (data.containsKey("urlLink")) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.get("urlLink")));
        }


        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 101, intent, 0);

        NotificationManager nm = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);


        NotificationChannel channel;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            nm.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(
                        getApplicationContext(), CHANNEL_ID)
                        .setContentTitle(title)
                        .setAutoCancel(true)
                        .setStyle(new NotificationCompat.BigTextStyle()
                         .bigText(body))
                        .setSmallIcon(R.drawable.ic_baseline_code_24)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentIntent(pi);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) builder.setColor(getColor(R.color.colorPrimary));

        if (title!=null&&!title.equals("")) nm.notify(101, builder.build());
    }
}