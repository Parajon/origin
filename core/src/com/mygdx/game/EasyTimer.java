package com.mygdx.game;

public class EasyTimer {
    private double startTime;
    private double passedTime;
    private boolean stop = false;

    public static final double SECOND = 1000000000;
    public void startTimer() {
        stop = false;
        startTime = System.nanoTime() / SECOND;
    }


    public boolean timerDelay(double second) {
        if(!stop) passedTime = System.nanoTime() / SECOND - startTime;
        return passedTime > second;
    }


    public void stopTimer(){
        stop = true;
    }


    public double getPassedTime() {
        return passedTime;
    }
}