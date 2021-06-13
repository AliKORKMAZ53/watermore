package com.example.watermore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.watermore.utils.Constants;
import com.example.watermore.utils.MyNotificationPublisher;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ReminderScreen extends AppCompatActivity {
    private final static String default_notification_channel_id = "default";
    public void returnMainPage(){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }
    ImageView geritusu,sabah,ogle,ikindi,gunbatimi,aksam,gece;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_screen);
        AndroidThreeTen.init(getApplication());
        calendar=Calendar.getInstance();
        aksam=findViewById(R.id.aksamalarm);
        geritusu=findViewById(R.id.geritusu);
        sabah = findViewById(R.id.sabahalarm);
        ogle=findViewById(R.id.oglealarm);
        ikindi=findViewById(R.id.ikindialarm);
        gunbatimi=findViewById(R.id.gunbatimialarm);
        gece=findViewById(R.id.gecealarm);

        geritusu.setOnClickListener(v -> returnMainPage());



        sabah.setOnClickListener(v -> {
            setFirstver();
            sabah.setImageResource(R.drawable.sabahafter);
            Animation animation1 = AnimationUtils.loadAnimation(ReminderScreen.this,R.anim.fadein);
            sabah.startAnimation(animation1);
            calendar.set(Calendar.HOUR_OF_DAY,8);

        });



        ogle.setOnClickListener(v -> {
            setFirstver();
            ogle.setImageResource(R.drawable.ogleafter);
            Animation animation1 = AnimationUtils.loadAnimation(ReminderScreen.this,R.anim.fadein);
            ogle.startAnimation(animation1);
            calendar.set(Calendar.HOUR_OF_DAY,12);
        });


        ikindi.setOnClickListener(v -> {
            setFirstver();
            ikindi.setImageResource(R.drawable.ikindiafter);
            Animation animation1 = AnimationUtils.loadAnimation(ReminderScreen.this,R.anim.fadein);
            ikindi.startAnimation(animation1);
            calendar.set(Calendar.HOUR_OF_DAY,16);
            });


        gunbatimi.setOnClickListener(v -> {
            setFirstver();
            gunbatimi.setImageResource(R.drawable.duskafter);
            Animation animation1 = AnimationUtils.loadAnimation(ReminderScreen.this,R.anim.fadein);
            gunbatimi.startAnimation(animation1);
            calendar.set(Calendar.HOUR_OF_DAY,19);
        });


        aksam.setOnClickListener(v -> {
            setFirstver();
            aksam.setImageResource(R.drawable.aksamafter);
            Animation animation1 = AnimationUtils.loadAnimation(ReminderScreen.this,R.anim.fadein);
            aksam.startAnimation(animation1);
            calendar.set(Calendar.HOUR_OF_DAY,21);
        });


        gece.setOnClickListener(v -> {
            setFirstver();
            gece.setImageResource(R.drawable.geceafter);
            Animation animation1 = AnimationUtils.loadAnimation(ReminderScreen.this,R.anim.fadein);
            gece.startAnimation(animation1);
            calendar.set(Calendar.HOUR_OF_DAY,24);
        });
    }
    private void scheduleNotification (Notification notification , long hour) {
        Intent notificationIntent = new Intent( this, MyNotificationPublisher. class ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION_ID , 1 ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION , notification) ;
        PendingIntent pendingIntent = PendingIntent. getBroadcast ( this, 0 , notificationIntent , PendingIntent. FLAG_UPDATE_CURRENT ) ;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context. ALARM_SERVICE ) ;
        assert alarmManager != null;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, hour,AlarmManager.INTERVAL_DAY,pendingIntent);
        }
    private Notification getNotification (String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, default_notification_channel_id ) ;
        builder.setContentTitle( "Hatırlatıcı" ) ;
        builder.setContentText(content) ;
        builder.setSmallIcon(R.drawable. ic_launcher_foreground ) ;
        builder.setAutoCancel( true ) ;
        builder.setChannelId(Constants.NOTIFICATION_CHANNEL_ID ) ;
        return builder.build() ;
    }
    private void updateLabel () {
        //String myFormat = "dd/MM/yy" ; //In which you need put here
        //SimpleDateFormat sdf = new SimpleDateFormat(myFormat , Locale. getDefault ()) ;
        //OffsetDateTime date = OffsetDateTime.now();

        //DateTimeFormatter formatter=DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss OOOO yyyy", Locale.ROOT);
        scheduleNotification(getNotification( "Su içmeyi unutmayın .)") , calendar.getTimeInMillis() ) ;
    }

    public void onSave(View view) {
        updateLabel();
    }
    public void setFirstver(){
        sabah.setImageResource(R.drawable.sabahbefore);
        ogle.setImageResource(R.drawable.oglebefore);
        ikindi.setImageResource(R.drawable.ikindibefore);
        aksam.setImageResource(R.drawable.aksambefore);
        gece.setImageResource(R.drawable.gecebefore);
        gunbatimi.setImageResource(R.drawable.duskbefore);
    }
}