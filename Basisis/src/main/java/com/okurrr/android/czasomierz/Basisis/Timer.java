package com.okurrr.android.czasomierz.Basisis;

import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;

public class Timer {

    private Thread thread;
    private boolean started;

    Timer(){
        started = false;
    }

    public boolean getStarted(){
        return started;
    }

    public void setStarted(boolean toSet){
        started = toSet;
    }

}
