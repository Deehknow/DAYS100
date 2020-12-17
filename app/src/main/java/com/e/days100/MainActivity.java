 package com.e.days100;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

 public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button notify=(Button) findViewById(R.id.btn_not);
        Button logout=(Button) findViewById(R.id.btn_logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotification();
            }
        });
    }

     private void signOut() {

         SharedPreferences sharedPreferences=getSharedPreferences(login.MyPREFERENCES,Context.MODE_PRIVATE);
         SharedPreferences.Editor editor=sharedPreferences.edit();
         editor.clear();
         editor.commit();
     }


     private void addNotification() {

        // app compact
         NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
         String NOTIFICATION_CHANNEL_ID="my_channel_id_01";

         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
             NotificationChannel notificationChannel=new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                     "my notifications", NotificationManager.IMPORTANCE_DEFAULT);

             //config notification channel

             notificationChannel.setDescription("channel description");
             notificationChannel.enableLights(true);
             notificationChannel.setLightColor(Color.RED);
             notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
             manager.createNotificationChannel(notificationChannel);

         }

         NotificationCompat.Builder builder=new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID)
                 .setSmallIcon(R.drawable.abc)
                 .setContentTitle("100 Days App")
                 .setContentText("this is a notification test")
                 .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                 .setAutoCancel(true);

         Intent notificationIntent= new Intent(this,NotificationView.class);
         PendingIntent contentIntent=PendingIntent.getActivity(this,0,
                 notificationIntent,PendingIntent.FLAG_CANCEL_CURRENT);
         builder.setContentIntent(contentIntent);

         // ADD AS NOTIFICATION

         manager.notify(0,builder.build());
     }
 }
