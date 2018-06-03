package com.okurrr.android.czasomierz.Basisis;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //TODO: zrobienie podgladu na zywo danych godzin, zapewne na thredach, ktore beda trzymane na liscie i utrzymymwane tylko wtedy
    //TODO: kiedy dany timer bedzie wlaczony i bedzie dzial, do sprawdzenia czy tez wtedy kiedy bedzie dziala appka w tle czy tylko na pierwszym planie


    //nie wiem czy tak sie robi czy jest jakis inny sposob na logike za przyciskami i polami w activitach czy moze to sie robi w ogole w inny sposob
    TextView oneT, twoT, threeT, fourT, fiveT, sixT, sevenT, eightT, nineT;
    Button buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine, buttonRefresh;
    Manager manager;
    Chronometer simpleChronometer;
    boolean oneStarted, firstOne;
    private long lastPause;

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
        buttonRefresh = (Button) findViewById(R.id.buttonRefresh);
        buttonRefresh.setOnClickListener(this);

        simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer); // initiate a chronometer
        //oneT = (TextView) findViewById(R.id.editText);
        //oneT.setKeyListener(null);
        twoT = (TextView) findViewById(R.id.editText2);
        twoT.setKeyListener(null);
        threeT = (TextView) findViewById(R.id.editText3);
        threeT.setKeyListener(null);
        fourT = (TextView) findViewById(R.id.editText4);
        fourT.setKeyListener(null);
        fiveT = (TextView) findViewById(R.id.editText5);
        fiveT.setKeyListener(null);
        sixT = (TextView) findViewById(R.id.editText6);
        sixT.setKeyListener(null);
        sevenT = (TextView) findViewById(R.id.editText7);
        sevenT.setKeyListener(null);
        eightT = (TextView) findViewById(R.id.editText8);
        eightT.setKeyListener(null);
        nineT = (TextView) findViewById(R.id.editText9);
        nineT.setKeyListener(null);

        manager = new Manager();
        lastPause = 0;
        //simpleChronometer.setBase(SystemClock.elapsedRealtime());
        oneStarted = false;
    }

    //zanim zdolalem to spakowac w jedna funkcje to troche sie nawymyslalem jak mozna uproscic i skrocic zarzadzanie cala logika appki
    public void managerOfTimerClick(Timer timer, TextView textView, Button button){
        if(timer.getStarted() == false){
            timer.startTimer();
            refreshTime(timer, textView);
            button.setText("Stop");
            timer.setStarted(true);
        }else {
            timer.stopTimer();
            refreshTime(timer, textView);
            button.setText("Start!");
            timer.setStarted(false);
        }
    }

    public void refreshTime(Timer timer, TextView textView){
        textView.setText(timer.getTime());
    }

    public void buttonHandler(){
        if(oneStarted == false){
            //simpleChronometer.setBase(simpleChronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
            simpleChronometer.setBase(SystemClock.elapsedRealtime() + lastPause);
            simpleChronometer.start();
            //simpleChronometer.start();
            System.out.println("Started");
            //refreshTime(timer, textView);
            buttonOne.setText("Stop");
            oneStarted = true;
        }else if(oneStarted == true){
            simpleChronometer.stop();
            lastPause = simpleChronometer.getBase() - SystemClock.elapsedRealtime();
            System.out.println("Stopped");
            //refreshTime(timer, textView);
            buttonOne.setText("Start!");
            oneStarted = false;
        }
    }

    //podobno nie stosuje sie juz tutaj switcha, wiec doradzono mi bazowanie na ifach
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button ) {
            if(firstOne == false){
                simpleChronometer.setBase(SystemClock.elapsedRealtime() + lastPause);
                simpleChronometer.start();
                System.out.println("Started for the first time");
                //refreshTime(timer, textView);
                buttonOne.setText("Stop");
                oneStarted = true;
                firstOne = true;
            }else{
                buttonHandler();
            }

            //managerOfTimerClick(manager.firstTimer, oneT, buttonOne);
        } else if (i == R.id.button2) {
            managerOfTimerClick(manager.secondTimer, twoT, buttonTwo);
        } else if (i == R.id.button3) {
            managerOfTimerClick(manager.thirdTimer, threeT, buttonThree);
        } else if (i == R.id.button4) {
            managerOfTimerClick(manager.fourthTimer, fourT, buttonFour);
        } else if (i == R.id.button5) {
            managerOfTimerClick(manager.fifthTimer, fiveT, buttonFive);
        } else if (i == R.id.button6) {
            managerOfTimerClick(manager.sixthTimer, sixT, buttonSix);
        } else if (i == R.id.button7) {
            managerOfTimerClick(manager.seventhTimer, sevenT, buttonSeven);
        } else if (i == R.id.button8) {
            managerOfTimerClick(manager.eighthTimer, eightT, buttonEight);
        } else if (i == R.id.button9) {
            managerOfTimerClick(manager.ninthTimer, nineT, buttonNine);
        } else if (i == R.id.buttonRefresh) {
            //najswiezszy dodatek, musze go jeszcze dostosowac
            //refreshTime(manager.firstTimer, oneT);
            refreshTime(manager.secondTimer, twoT);
            refreshTime(manager.thirdTimer, threeT);
            refreshTime(manager.fourthTimer, fourT);
            refreshTime(manager.fifthTimer, fiveT);
            refreshTime(manager.sixthTimer, sixT);
            refreshTime(manager.seventhTimer, sevenT);
            refreshTime(manager.eighthTimer, eightT);
            refreshTime(manager.ninthTimer, nineT);

        }
    }
}
