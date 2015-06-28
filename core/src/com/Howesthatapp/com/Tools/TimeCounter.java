package com.Howesthatapp.com.Tools;

/**
 * Created by Mike on 4/11/2014.
 *
 */


public class TimeCounter {

    private static long[] startTime = new long[10];
    private static long currentTime = 0;
    private static float timeLeft = 0;
    private static long[] startPause = new long[10], pauseTime = new long[10];

    public static void RestartTimer(int timer){
        startTime[timer] = System.nanoTime();
        pauseTime[timer] = 0;  // Reset Pause Time
//        System.out.println("Start Time: " + startTime[timer]);
    }

    public static float updateTimer(int timer){
        for (int i = 0; i < 60; i++) {
            if (i == 0){
                currentTime = System.nanoTime() - startTime[timer] - pauseTime[timer];
            }
        }

        timeLeft = currentTime/1000000000;
        return timeLeft;
    }

    public static void pauseTimer(int timer){
        startPause[timer] = System.nanoTime();
    }

    public static void resumeTimer(int timer){
        pauseTime[timer] += System.nanoTime() - startPause[timer];
    }

}
