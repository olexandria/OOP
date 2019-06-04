package com.dypko;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class MyCallable implements Callable {

    public int length;

    public MyCallable(int length)
    {
        this.length=length;
    }

    public double[] call(){
        Scanner in=new Scanner(System.in);
        long start_time = System.currentTimeMillis();
        List myList = new List(length);
        double []arr=new double[5];
        myList.random();
        arr[0]=myList.max();
        arr[1]=myList.min();
        arr[2]=myList.average_value();
        arr[3]=System.currentTimeMillis() - start_time;
        arr[4]=myList.sum();

        return arr;
    }
}