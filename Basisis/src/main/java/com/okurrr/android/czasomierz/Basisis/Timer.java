package com.okurrr.android.czasomierz.Basisis;

import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;

public class Timer {

    private Stopwatch time;
    private Thread thread;
    private boolean started;

    Timer(){
        started = false;
        time = Stopwatch.createUnstarted(
                new Ticker() {
                    public long read() {
                        return android.os.SystemClock.elapsedRealtimeNanos();
                    }
                }
         );
    }

    //poczatki zabawy z threadami
    public void createThread() {
        thread = new Thread() {
/*
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateTextView();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        thread.start();
        */
        };
    }

    public void startTimer(){
        time.start();
    }

    public void stopTimer(){
        time.stop();
    }

    public String getTime(){
        return time.toString();
    }

    public boolean getStarted(){
        return started;
    }

    public void setStarted(boolean toSet){
        started = toSet;
    }

}
