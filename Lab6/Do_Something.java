package com.dypko;

public class Do_Something extends Thread {

    long timeLimit;

    public Do_Something(long limit)
    {
        this.timeLimit=limit;
    }
    public void run()
    {
        long start = System.currentTimeMillis();
        while(timeLimit>System.currentTimeMillis()-start) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Error");
            }
        }
        System.out.println("Time is over.");
        System.exit(0);
    }

}