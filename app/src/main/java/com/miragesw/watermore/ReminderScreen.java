package com.miragesw.watermore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.miragesw.watermore.utils.Constants;
import com.miragesw.watermore.utils.MyNotificationPublisher;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.util.ArrayList;
import java.util.Calendar;

public class ReminderScreen extends AppCompatActivity {
    private final static String default_notification_channel_id = "default";

    public void returnMainPage() {
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }

    ImageView geritusu, sabah, ogle, ikindi, gunbatimi, aksam, gece;
    int sabahOnOff, ogleOnOff, ikindiOnOff, gunbatimiOnOff, aksamOnOff, geceOnOff = 0;
    Calendar calendarSabah, calendarOgle, calendarikindi, calendarGunbatimi, calendarAksam, calendarGece;
    ArrayList<Calendar> calendarArrayList=new ArrayList<>();
    ArrayList<Integer> pendingIntentIdArray=new ArrayList<>();//sabah=1,ogle=2,ikindi=3,gunbatimi=4,aksam=5,gece=6
    ArrayList<Integer> cancelPendingIdArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_screen);
        AndroidThreeTen.init(getApplication());
        calendarSabah = Calendar.getInstance();
        calendarOgle = Calendar.getInstance();
        calendarikindi = Calendar.getInstance();
        calendarAksam = Calendar.getInstance();
        calendarGunbatimi = Calendar.getInstance();
        calendarGece = Calendar.getInstance();
        calendarSabah.set(Calendar.HOUR_OF_DAY, 8);
        calendarOgle.set(Calendar.HOUR_OF_DAY, 12);
        calendarikindi.set(Calendar.HOUR_OF_DAY, 16);
        calendarGunbatimi.set(Calendar.HOUR_OF_DAY, 19);
        calendarAksam.set(Calendar.HOUR_OF_DAY, 21);
        calendarGece.set(Calendar.HOUR_OF_DAY, 24);

        aksam = findViewById(R.id.aksamalarm);
        geritusu = findViewById(R.id.geritusu);
        sabah = findViewById(R.id.sabahalarm);
        ogle = findViewById(R.id.oglealarm);
        ikindi = findViewById(R.id.ikindialarm);
        gunbatimi = findViewById(R.id.gunbatimialarm);
        gece = findViewById(R.id.gecealarm);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        sabahOnOff = sharedPref.getInt("sabah", 0);
        ogleOnOff = sharedPref.getInt("ogle", 0);
        ikindiOnOff = sharedPref.getInt("ikindi", 0);
        gunbatimiOnOff = sharedPref.getInt("gunbatimi", 0);
        aksamOnOff = sharedPref.getInt("aksam", 0);
        geceOnOff = sharedPref.getInt("gece", 0);

        if (sabahOnOff == 1) {
            sabah.setImageResource(R.drawable.sabahafter);
            calendarArrayList.add(calendarSabah);
            pendingIntentIdArray.add(1);
        }else{
            cancelPendingIdArrayList.add(1);
        }
        if (ogleOnOff == 1) {
            ogle.setImageResource(R.drawable.ogleafter);
            calendarArrayList.add(calendarOgle);
            pendingIntentIdArray.add(2);
        }else{
            cancelPendingIdArrayList.add(2);
        }
        if (ikindiOnOff == 1) {
            ikindi.setImageResource(R.drawable.ikindiafter);
            calendarArrayList.add(calendarikindi);
            pendingIntentIdArray.add(3);
        }else{
            cancelPendingIdArrayList.add(3);
        }
        if (gunbatimiOnOff == 1) {
            gunbatimi.setImageResource(R.drawable.duskafter);
            calendarArrayList.add(calendarGunbatimi);
            pendingIntentIdArray.add(4);
        }else{
            cancelPendingIdArrayList.add(4);
        }
        if (aksamOnOff == 1) {
            aksam.setImageResource(R.drawable.aksamafter);
            calendarArrayList.add(calendarAksam);
            pendingIntentIdArray.add(5);
        }else{
            cancelPendingIdArrayList.add(5);
        }
        if (geceOnOff == 1) {
            gece.setImageResource(R.drawable.geceafter);
            calendarArrayList.add(calendarGece);
            pendingIntentIdArray.add(6);
        }else{
            cancelPendingIdArrayList.add(6);
        }
        geritusu.setOnClickListener(v -> returnMainPage());

        sabah.setOnClickListener(v -> {
            //setFirstver();

            Animation animation1 = AnimationUtils.loadAnimation(ReminderScreen.this, R.anim.fadein);
            sabah.startAnimation(animation1);
            if (sabahOnOff == 0) {
                sabah.setImageResource(R.drawable.sabahafter);

                calendarArrayList.add(calendarSabah);
                sabahOnOff = 1;
                pendingIntentIdArray.add(1);
                cancelPendingIdArrayList.remove(cancelPendingIdArrayList.indexOf(1));
                editor.putInt("sabah", sabahOnOff);
                editor.apply();

            } else if (sabahOnOff == 1) {
                sabah.setImageResource(R.drawable.sabahbefore);
                sabahOnOff = 0;
                calendarArrayList.remove(calendarSabah);
                cancelPendingIdArrayList.add(1);
                pendingIntentIdArray.remove(pendingIntentIdArray.indexOf(1));
                editor.putInt("sabah", sabahOnOff);
                editor.apply();
                //when user click save you get the zeroed buttons and get their alarms closed()
            } else {

            }

        });


        ogle.setOnClickListener(v -> {
            //setFirstver();

            Animation animation1 = AnimationUtils.loadAnimation(ReminderScreen.this, R.anim.fadein);
            ogle.startAnimation(animation1);

            if (ogleOnOff == 0) {
                ogle.setImageResource(R.drawable.ogleafter);
                cancelPendingIdArrayList.remove(cancelPendingIdArrayList.indexOf(2));
                calendarArrayList.add(calendarOgle);
                ogleOnOff = 1;
                pendingIntentIdArray.add(2);
                editor.putInt("ogle", ogleOnOff);
                editor.apply();
            } else if (ogleOnOff == 1) {
                ogle.setImageResource(R.drawable.oglebefore);
                ogleOnOff = 0;
                calendarArrayList.remove(calendarOgle);
                cancelPendingIdArrayList.add(2);
                pendingIntentIdArray.remove(pendingIntentIdArray.indexOf(2));
                editor.putInt("ogle", ogleOnOff);
                editor.apply();
                //when user click save you get the zeroed buttons and get their alarms closed()
            } else {

            }
        });


        ikindi.setOnClickListener(v -> {
            //setFirstver();

            Animation animation1 = AnimationUtils.loadAnimation(ReminderScreen.this, R.anim.fadein);
            ikindi.startAnimation(animation1);

            if (ikindiOnOff == 0) {
                ikindi.setImageResource(R.drawable.ikindiafter);
                cancelPendingIdArrayList.remove(cancelPendingIdArrayList.indexOf(3));
                calendarArrayList.add(calendarikindi);
                ikindiOnOff = 1;
                pendingIntentIdArray.add(3);
                editor.putInt("ikindi", ikindiOnOff);
                editor.apply();
            } else if (ikindiOnOff == 1) {
                ikindi.setImageResource(R.drawable.ikindibefore);
                ikindiOnOff = 0;
                calendarArrayList.remove(calendarikindi);
                cancelPendingIdArrayList.add(3);
                pendingIntentIdArray.remove(pendingIntentIdArray.indexOf(3));
                editor.putInt("ikindi", ikindiOnOff);
                editor.apply();
                //when user click save you get the zeroed buttons and get their alarms closed()
            } else {

            }
        });


        gunbatimi.setOnClickListener(v -> {
            //setFirstver();

            Animation animation1 = AnimationUtils.loadAnimation(ReminderScreen.this, R.anim.fadein);
            gunbatimi.startAnimation(animation1);


            if (gunbatimiOnOff == 0) {
                gunbatimi.setImageResource(R.drawable.duskafter);
                cancelPendingIdArrayList.remove(cancelPendingIdArrayList.indexOf(4));
                calendarArrayList.add(calendarGunbatimi);
                gunbatimiOnOff = 1;
                pendingIntentIdArray.add(4);
                editor.putInt("gunbatimi", gunbatimiOnOff);
                editor.apply();
            } else if (gunbatimiOnOff == 1) {
                gunbatimi.setImageResource(R.drawable.duskbefore);
                gunbatimiOnOff = 0;
                calendarArrayList.remove(calendarGunbatimi);
                cancelPendingIdArrayList.add(4);
                pendingIntentIdArray.remove(pendingIntentIdArray.indexOf(4));
                editor.putInt("gunbatimi", gunbatimiOnOff);
                editor.apply();
                //when user click save you get the zeroed buttons and get their alarms closed()
            } else {

            }
        });


        aksam.setOnClickListener(v -> {
            //setFirstver();

            Animation animation1 = AnimationUtils.loadAnimation(ReminderScreen.this, R.anim.fadein);
            aksam.startAnimation(animation1);
            if (aksamOnOff == 0) {
                aksam.setImageResource(R.drawable.aksamafter);
                cancelPendingIdArrayList.remove(cancelPendingIdArrayList.indexOf(5));
                calendarArrayList.add(calendarAksam);
                aksamOnOff = 1;
                pendingIntentIdArray.add(5);
                editor.putInt("aksam", aksamOnOff);
                editor.apply();
            } else if (aksamOnOff == 1) {
                aksam.setImageResource(R.drawable.aksambefore);
                aksamOnOff = 0;
                calendarArrayList.remove(calendarAksam);
                cancelPendingIdArrayList.add(5);
                pendingIntentIdArray.remove(pendingIntentIdArray.indexOf(5));
                editor.putInt("aksam", aksamOnOff);
                editor.apply();
                //when user click save you get the zeroed buttons and get their alarms closed()
            } else {

            }
        });


        gece.setOnClickListener(v -> {
            //setFirstver();

            Animation animation1 = AnimationUtils.loadAnimation(ReminderScreen.this, R.anim.fadein);
            gece.startAnimation(animation1);
            if (geceOnOff == 0) {
                gece.setImageResource(R.drawable.geceafter);
                cancelPendingIdArrayList.remove(cancelPendingIdArrayList.indexOf(6));
                calendarArrayList.add(calendarGece);
                geceOnOff = 1;
                pendingIntentIdArray.add(6);
                editor.putInt("gece", geceOnOff);
                editor.apply();
            } else if (geceOnOff == 1) {
                gece.setImageResource(R.drawable.gecebefore);
                geceOnOff = 0;
                calendarArrayList.remove(calendarGece);
                cancelPendingIdArrayList.add(6);
                pendingIntentIdArray.remove(pendingIntentIdArray.indexOf(6));
                editor.putInt("gece", geceOnOff);
                editor.apply();
                //when user click save you get the zeroed buttons and get their alarms closed()
            } else {

            }
        });
    }

    private void scheduleNotification(Notification notification, long hour, int pendingIntentID) {
        Intent notificationIntent = new Intent(this, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, pendingIntentID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, hour, AlarmManager.INTERVAL_DAY, pendingIntent);
    }
    private void cancelNotification(Notification notification, int pendingIntentID) {
        Intent notificationIntent = new Intent(this, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, pendingIntentID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.cancel(pendingIntent);
    }

    private Notification getNotification(String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, default_notification_channel_id);
        builder.setContentTitle("Hatırlatıcı");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setAutoCancel(true);
        builder.setChannelId(Constants.NOTIFICATION_CHANNEL_ID);
        return builder.build();
    }

    private void updateLabel() {
        for (int i = 0; i < pendingIntentIdArray.size(); i++) {
            scheduleNotification(getNotification("Su içmeyi unutmayın .)"), calendarArrayList.get(i).getTimeInMillis(), pendingIntentIdArray.get(i));
        }
        for(int i=0;i < cancelPendingIdArrayList.size();i++){
            cancelNotification(getNotification("Su içmeyi unutmayın .)"),cancelPendingIdArrayList.get(i));
        }
    }

    public void onSave(View view) {
        updateLabel();
        Toast.makeText(this,getResources().getString(R.string.saveToast),Toast.LENGTH_LONG).show();
        returnMainPage();
        finish();
    }

  /*  public void setFirstver(){
        sabah.setImageResource(R.drawable.sabahbefore);
        ogle.setImageResource(R.drawable.oglebefore);
        ikindi.setImageResource(R.drawable.ikindibefore);
        aksam.setImageResource(R.drawable.aksambefore);
        gece.setImageResource(R.drawable.gecebefore);
        gunbatimi.setImageResource(R.drawable.duskbefore);
    }
   */
}