package com.okurrr.android.czasomierz.Basisis;

import java.util.LinkedList;
import java.util.List;

public class Manager {

    public List<Timer> timers;
    public Timer firstTimer, secondTimer, thirdTimer, fourthTimer, fifthTimer, sixthTimer, seventhTimer, eighthTimer, ninthTimer;

    Manager(){
        timers = new LinkedList<Timer>();
        firstTimer = new Timer();
        secondTimer = new Timer();
        thirdTimer = new Timer();
        fourthTimer = new Timer();
        fifthTimer = new Timer();
        sixthTimer = new Timer();
        seventhTimer = new Timer();
        eighthTimer = new Timer();
        ninthTimer = new Timer();
        timers.add(firstTimer);
        timers.add(secondTimer);
        timers.add(thirdTimer);
        timers.add(fourthTimer);
        timers.add(fifthTimer);
        timers.add(sixthTimer);
        timers.add(seventhTimer);
        timers.add(eighthTimer);
        timers.add(ninthTimer);
    }

}
