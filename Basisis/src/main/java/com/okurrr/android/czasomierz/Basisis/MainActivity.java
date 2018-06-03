package com.okurrr.android.czasomierz.Basisis;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //nie wiem czy tak sie robi czy jest jakis inny sposob na logike za przyciskami i polami w activitach czy moze to sie robi w ogole w inny sposob
    public Button buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine, buttonRefresh;
    Manager manager;
    public Chronometer firstChrono, secondChrono, thirdChrono, fourthChrono, fifthChrono, sixthChrono, seventhChrono, eightChrono, ninthChrono;
    public boolean firstStarted, secondStarted, thirdStarted, fourthStarted, fifthStarted, sixthStarted, seventhStarted, eightStarted, ninthStarted;
    public long firstPause, secondPause, thirdPause, fourthPause, fifthPause, sixthPause, seventhPause, eightPause, ninthPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonOne = (Button) findViewById(R.id.button);
        buttonOne.setOnClickListener(this);
        buttonTwo = (Button) findViewById(R.id.button2);
        buttonTwo.setOnClickListener(this);
        buttonThree = (Button) findViewById(R.id.button3);
        buttonThree.setOnClickListener(this);
        buttonFour = (Button) findViewById(R.id.button4);
        buttonFour.setOnClickListener(this);
        buttonFive = (Button) findViewById(R.id.button5);
        buttonFive.setOnClickListener(this);
        buttonSix = (Button) findViewById(R.id.button6);
        buttonSix.setOnClickListener(this);
        buttonSeven = (Button) findViewById(R.id.button7);
        buttonSeven.setOnClickListener(this);
        buttonEight = (Button) findViewById(R.id.button8);
        buttonEight.setOnClickListener(this);
        buttonNine = (Button) findViewById(R.id.button9);
        buttonNine.setOnClickListener(this);

        firstChrono = (Chronometer) findViewById(R.id.chronometer1);
        secondChrono = (Chronometer) findViewById(R.id.chronometer2);
        thirdChrono = (Chronometer) findViewById(R.id.chronometer3);
        fourthChrono = (Chronometer) findViewById(R.id.chronometer4);
        fifthChrono = (Chronometer) findViewById(R.id.chronometer5);
        sixthChrono = (Chronometer) findViewById(R.id.chronometer6);
        seventhChrono = (Chronometer) findViewById(R.id.chronometer7);
        eightChrono = (Chronometer) findViewById(R.id.chronometer8);
        ninthChrono = (Chronometer) findViewById(R.id.chronometer9);
        firstChrono.setBase(SystemClock.elapsedRealtime());

        manager = new Manager();
        firstPause = 0; secondPause = 0; thirdPause = 0; fourthPause = 0; fifthPause = 0; sixthPause = 0; seventhPause = 0; eightPause = 0; ninthPause = 0;
        firstStarted = false; secondStarted = false; thirdStarted = false; fourthStarted = false; fifthStarted = false; sixthStarted = false; seventhStarted = false; eightStarted = false; ninthStarted = false;
    }

    public void buttonHandler(Chronometer chronometer, boolean started, long lastPause, Button button, Timer timer){
        if(timer.getStarted() == false){
            chronometer.setBase(SystemClock.elapsedRealtime() + lastPause);
            chronometer.start();
            makeAToast("Starting a timer :D !");
            button.setText("Refresh");
            timer.setStarted(true);
        }else if(timer.getStarted() == true){
            chronometer.stop();
            lastPause = chronometer.getBase() - SystemClock.elapsedRealtime();
            button.setText("Start!");
            timer.setStarted(false);
        }
    }

    public void makeANotification(){
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default",
                    "YOUR_CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DISCRIPTION");
            mNotificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this, "default")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Czasomierz LO-Hłan!")
                .setContentText("Już połowa czasu!")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Minęły już 4 godziny Twojego siedzenia w biurze Henny!"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, 0);

        mBuilder.setContentIntent(pendingIntent);
        mNotificationManager.notify(0, mBuilder.build());
    }

    public void makeAToast(String toastText){
        Context context = getApplicationContext();
        CharSequence text = toastText;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    //podobno nie stosuje sie juz tutaj switcha, wiec doradzono mi bazowanie na ifach
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button ) {
            buttonHandler(firstChrono, firstStarted, firstPause, buttonOne, manager.firstTimer);
        } else if (i == R.id.button2) {
            buttonHandler(secondChrono, secondStarted, secondPause, buttonTwo, manager.secondTimer);
        } else if (i == R.id.button3) {
            buttonHandler(thirdChrono, thirdStarted, thirdPause, buttonThree, manager.thirdTimer);
        } else if (i == R.id.button4) {
            buttonHandler(fourthChrono, fourthStarted, fourthPause, buttonFour, manager.fourthTimer);
        } else if (i == R.id.button5) {
            buttonHandler(fifthChrono, fifthStarted, fifthPause, buttonFive, manager.fifthTimer);
        } else if (i == R.id.button6) {
            buttonHandler(sixthChrono, sixthStarted, sixthPause, buttonSix, manager.sixthTimer);
            makeANotification();
        } else if (i == R.id.button7) {
            buttonHandler(seventhChrono, seventhStarted, seventhPause, buttonSeven, manager.seventhTimer);
        } else if (i == R.id.button8) {
            buttonHandler(eightChrono, eightStarted, eightPause, buttonEight, manager.eighthTimer);
        } else if (i == R.id.button9) {
            buttonHandler(ninthChrono, ninthStarted, ninthPause, buttonNine, manager.ninthTimer);
        }
    }
}

